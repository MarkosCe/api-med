package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DatosAuthenticationUser;
import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.infra.security.DatosJWTToken;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> authenticationUser(@RequestBody @Valid DatosAuthenticationUser datosAuthenticationUser){
        Authentication authToken =  new UsernamePasswordAuthenticationToken(
                datosAuthenticationUser.login(), datosAuthenticationUser.clave());
        var usuarioAuntenicado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((Usuario) usuarioAuntenicado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
