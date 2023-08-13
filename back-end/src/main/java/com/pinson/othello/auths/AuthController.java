package com.pinson.othello.auths;

import com.pinson.othello.auths.dtos.requests.SignUpRequest;
import com.pinson.othello.auths.dtos.requests.LoginRequest;
import com.pinson.othello.auths.dtos.responses.AuthResponseFactory;
import com.pinson.othello.auths.dtos.responses.AuthenticationResponse;
import com.pinson.othello.auths.jwts.JwtService;
import com.pinson.othello.errors.ErrorResponseFactory;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerService;
import com.pinson.othello.players.dtos.responses.OthelloPlayerResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final OthelloPlayerService playerService;
    private final JwtService jwtService;
    private final AuthResponseFactory authenticationResponseFactory;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final OthelloPlayerResponseFactory playerResponseFactory;
    private final ErrorResponseFactory errorResponseFactory;

    @Autowired
    public AuthController(
        final OthelloPlayerService playerService,
        final JwtService jwtService,
        final AuthResponseFactory authenticationResponseFactory,
        final AuthenticationManager authenticationManager,
        final PasswordEncoder passwordEncoder,
        final OthelloPlayerResponseFactory playerResponseFactory,
        final ErrorResponseFactory errorResponseFactory
    ) {
        this.playerService = playerService;
        this.jwtService = jwtService;
        this.authenticationResponseFactory = authenticationResponseFactory;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.playerResponseFactory = playerResponseFactory;
        this.errorResponseFactory = errorResponseFactory;
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getMe() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            OthelloPlayer player = this.playerService.getPlayerByUsername(username);

            return ResponseEntity.ok(
                this.playerResponseFactory.create(player)
            );
        } catch (final Exception e) {
            return this.errorResponseFactory.createResponse(HttpStatus.UNAUTHORIZED, "You are not logged in.");
        }
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
