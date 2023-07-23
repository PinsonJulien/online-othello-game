package com.pinson.othello.auths;

import com.pinson.othello.auths.dtos.requests.SignUpRequest;
import com.pinson.othello.auths.dtos.requests.LoginRequest;
import com.pinson.othello.auths.dtos.responses.AuthResponseFactory;
import com.pinson.othello.auths.dtos.responses.AuthenticationResponse;
import com.pinson.othello.auths.jwts.JwtService;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final OthelloPlayerService playerService;
    private final JwtService jwtService;
    private final AuthResponseFactory authenticationResponseFactory;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(
        final OthelloPlayerService playerService,
        final JwtService jwtService,
        final AuthResponseFactory authenticationResponseFactory,
        final AuthenticationManager authenticationManager,
        final PasswordEncoder passwordEncoder
    ) {
        this.playerService = playerService;
        this.jwtService = jwtService;
        this.authenticationResponseFactory = authenticationResponseFactory;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(
        @RequestBody final SignUpRequest signUpRequest
    ) {
        try {
            OthelloPlayer player = this.playerService.create(
                signUpRequest.username(),
                this.passwordEncoder.encode(signUpRequest.password())
            );

            String jwt = this.jwtService.generateToken(player);

            return ResponseEntity.ok(
                this.authenticationResponseFactory.create(jwt)
            );
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody final LoginRequest loginRequest
    ) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.username(),
                    loginRequest.password()
                )
            );
        } catch (final BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            OthelloPlayer player = this.playerService.getPlayerByUsername(loginRequest.username());
            String jwt = this.jwtService.generateToken(player);

            return ResponseEntity.ok(
                this.authenticationResponseFactory.create(jwt)
            );
        } catch (final BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
