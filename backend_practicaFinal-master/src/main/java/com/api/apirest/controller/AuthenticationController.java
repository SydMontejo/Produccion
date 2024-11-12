package com.api.apirest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.api.apirest.configuraciones.JwtUtils;
import com.api.apirest.model.Administrador;
import com.api.apirest.model.JwtRequest;
import com.api.apirest.model.JwtResponse;
import com.api.apirest.service.AdminDetailServiceImpl;
import java.security.Principal;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminDetailServiceImpl adminDetailService; 

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (Exception exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.adminDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }
    
    @GetMapping("/actual-usuario")
    public Administrador obtenerUsuarioActual(Principal principal){
        return (Administrador) this.adminDetailService.loadUserByUsername(principal.getName());
    }
}