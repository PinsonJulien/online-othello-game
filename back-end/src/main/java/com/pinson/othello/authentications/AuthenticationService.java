package com.pinson.othello.authentications;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinson.othello.authentications.requests.AuthenticationRequest;
import com.pinson.othello.authentications.requests.RegisterRequest;
import com.pinson.othello.authentications.responses.AuthenticationResponse;
import com.pinson.othello.authentications.responses.AuthenticationResponseFactory;
import com.pinson.othello.configurations.JwtService;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerFactory;
import com.pinson.othello.players.OthelloPlayerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pinson.othello.tokens.Token;
import com.pinson.othello.tokens.TokenFactory;
import com.pinson.othello.tokens.TokenRepository;

import java.io.IOException;

@Service
public class AuthenticationService {
    private final OthelloPlayerRepository othelloPlayerRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationResponseFactory authenticationResponseFactory;
    private final TokenFactory tokenFactory;
    private final OthelloPlayerFactory othelloPlayerFactory;


    @Autowired
    public AuthenticationService(
        OthelloPlayerRepository othelloPlayerRepository,
        TokenRepository tokenRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        AuthenticationManager authenticationManager,
        AuthenticationResponseFactory authenticationResponseFactory,
        TokenFactory tokenFactory,
        OthelloPlayerFactory othelloPlayerFactory
    ) {
        this.othelloPlayerRepository = othelloPlayerRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authenticationResponseFactory = authenticationResponseFactory;
        this.tokenFactory = tokenFactory;
        this.othelloPlayerFactory = othelloPlayerFactory;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        OthelloPlayer player = othelloPlayerFactory.create(
            request.getUsername(),
            passwordEncoder.encode(request.getPassword())
        );

        /*var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();*/

        OthelloPlayer savedPlayer = othelloPlayerRepository.save(player);
        String jwtToken = jwtService.generateToken(player);
        String refreshToken = jwtService.generateRefreshToken(player);

        this.saveUserToken(savedPlayer, jwtToken);

        return this.authenticationResponseFactory.create(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        OthelloPlayer player = othelloPlayerRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(player);
        String refreshToken = jwtService.generateRefreshToken(player);
        revokeAllUserTokens(player);
        saveUserToken(player, jwtToken);

        return this.authenticationResponseFactory.create(jwtToken, refreshToken);
    }

    private void saveUserToken(OthelloPlayer player, String jwtToken) {
        Token token = this.tokenFactory.createPlayerBearerToken(player, jwtToken);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(OthelloPlayer player) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(player.getId());
        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            OthelloPlayer player = this.othelloPlayerRepository.findByUsername(username)
                    .orElseThrow();

            if (jwtService.isTokenValid(refreshToken, player)) {
                String accessToken = jwtService.generateToken(player);
                revokeAllUserTokens(player);
                saveUserToken(player, accessToken);
                AuthenticationResponse authResponse = this.authenticationResponseFactory.create(accessToken, refreshToken);

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}