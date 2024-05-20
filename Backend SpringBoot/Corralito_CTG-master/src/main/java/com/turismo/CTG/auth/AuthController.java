package com.turismo.CTG.auth;


import com.turismo.CTG.model.entity.Usuario;
import com.turismo.CTG.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

            // -BUSCAR EL USUARIO FUNCION PRINCIPAL-
            Usuario usuario = usuarioService.findByNombreUsuario(loginRequest.getUsername());
            if (usuario != null && usuario.getContrasena().equals(loginRequest.getPassword())) {
                String perfil = usuario.getPerfil().getNombrePerfil();
                if (perfil.equalsIgnoreCase("Admin") || perfil.equalsIgnoreCase("Ente")) {
                    String token = generateJwtToken(loginRequest.getUsername());
                    return ResponseEntity.ok(new JwtAuthenticationResponse(token, perfil));
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
      //  }
    }

    private String generateJwtToken(String username) {
        return "token_jwt_generado";
    }
}
