package com.bersamed.test.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.bersamed.test.data.model.LoggedInUser;
import com.bersamed.test.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginDataSource {

    private final Context context;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public LoginDataSource(Context context) {
        this.context = context;
    }

    public interface LoginCallback {
        void onSuccess(LoggedInUser user);
        void onError(String errorMessage);
    }


    public void loginAsync(String username, String password, LoginCallback callback) {
        executor.execute(() -> {
            try {
                JSONObject credentials = new JSONObject();
                credentials.put("username", username);
                credentials.put("password", password);

                URL url = new URL(context.getString(R.string.autenticationURL));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                os.write(credentials.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();
                Log.d("LoginDataSource", "Response code: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder responseStr = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseStr.append(line);
                    }
                    reader.close();

                    String responseText = responseStr.toString();
                    Log.d("LoginDataSource", "Response JSON: " + responseText);

                    JSONObject responseJson = new JSONObject(responseText);
                    String token = responseJson.getString("token");
                    Log.d("LoginDataSource", "Token: " + token);

                    JSONObject userJson = responseJson.getJSONObject("user");
                    String name = userJson.getString("name");
                    String id = userJson.getString("id");

                    Log.d("LoginDataSource", "User name: " + name + ", id: " + id);

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("jwt_token", token);
                    editor.putString("user_name", name);
                    editor.putString("user_id", id);
                    editor.apply();

                    LoggedInUser user = new LoggedInUser(id, name);

                    mainHandler.post(() -> callback.onSuccess(user));

                } else {
                    Log.e("LoginDataSource", "Login failed, response code: " + responseCode);


                    mainHandler.post(() -> callback.onError("Credenciales inválidas. Código: " + responseCode));
                }

            } catch (Exception e) {
                Log.e("LoginDataSource", "Exception during login", e);
                mainHandler.post(() -> callback.onError("Error en la conexión: " + e.getMessage()));
            }
        });
    }

    public void logout() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String token = prefs.getString("jwt_token", null);

        if (token == null) return false;

        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return false;

            byte[] payloadBytes = android.util.Base64.decode(parts[1], android.util.Base64.URL_SAFE);
            String payloadJson = new String(payloadBytes, StandardCharsets.UTF_8);
            JSONObject payload = new JSONObject(payloadJson);

            if (payload.has("exp")) {
                long exp = payload.getLong("exp");
                long now = System.currentTimeMillis() / 1000;
                return now < exp;
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener perfil de usuario (usando token)
    public JSONObject getUserProfile() throws IOException {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String token = prefs.getString("jwt_token", null);

        if (token == null) {
            throw new IOException("Token no disponible. El usuario debe iniciar sesión.");
        }

        URL url = new URL(context.getString(R.string.autenticationURL));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setRequestProperty("Content-Type", "application/json");

        int responseCode = conn.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStr = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseStr.append(line);
            }
            reader.close();

            try {
                return new JSONObject(responseStr.toString());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IOException("Error al obtener perfil. Código: " + responseCode);
        }
    }
}
