package com.bersamed.test.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bersamed.test.data.model.LoggedInUser;
import com.bersamed.test.data.model.LoginResponse;

public class LoginRepository {

    private static volatile LoginRepository instance;
    private final LoginDataSource dataSource;
    private LoggedInUser user = null;
    private final SharedPreferences prefs;

    // Constructor privado para patrón Singleton
    private LoginRepository(LoginDataSource dataSource, Context context) {
        this.dataSource = dataSource;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context);
        loadLoggedInUser();
    }

    // Obtener la instancia Singleton del repositorio
    public static LoginRepository getInstance(LoginDataSource dataSource, Context context) {
        if (instance == null) {
            instance = new LoginRepository(dataSource, context);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return prefs.contains("jwt_token");
    }

    public void logout() {
        user = null;
        dataSource.logout();

        // Limpiar datos del token y usuario en SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("jwt_token");
        editor.remove("user_id");
        editor.remove("user_name");
        editor.apply();
    }

    private void setLoggedInUser(LoggedInUser user, String jwtToken) {
        this.user = user;

        // Guardar sesión localmente
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("jwt_token", jwtToken);
        editor.putString("user_id", user.getUserId());
        editor.putString("user_name", user.getDisplayName());
        editor.apply();
    }

    private void loadLoggedInUser() {
        String userId = prefs.getString("user_id", null);
        String userName = prefs.getString("user_name", null);
        if (userId != null && userName != null) {
            user = new LoggedInUser(userId, userName);
        }
    }


    // Método modificado para usar el loginAsync con callback y sin retorno
    public void login(String username, String password, LoginDataSource.LoginCallback callback) {
        dataSource.loginAsync(username, password, new LoginDataSource.LoginCallback() {
            @Override
            public void onSuccess(LoggedInUser user) {
                // Guardar usuario y token en prefs
                setLoggedInUser(user, prefs.getString("jwt_token", ""));
                // Notificar éxito
                callback.onSuccess(user);
            }

            @Override
            public void onError(String errorMessage) {
                // Notificar error
                callback.onError(errorMessage);
            }
        });
    }

    // ✅ Este método ahora está dentro de la clase
    public String getToken() {
        return prefs.getString("jwt_token", null);
    }
}
