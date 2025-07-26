package com.bersamed.test.controller;

import com.bersamed.test.model.LoginRequest;
import com.bersamed.test.model.LoginResponse;
import com.bersamed.test.model.LoggedInUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // Validación básica
        if ("admin".equals(request.getUsername()) && "123456".equals(request.getPassword())) {
            // Aquí puedes generar un JWT real, por ahora es un token falso para pruebas
            String fakeToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.fake.payload";

            LoggedInUser user = new LoggedInUser("1", "Administrador");
            LoginResponse response = new LoginResponse(fakeToken, user);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
