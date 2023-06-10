package com.pinson.othello.players;

import com.pinson.othello.players.exceptions.DuplicateUsernameException;
import com.pinson.othello.players.exceptions.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OthelloPlayerService {

    private final OthelloPlayerRepository othelloPlayerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OthelloPlayerService(OthelloPlayerRepository othelloPlayerRepository, PasswordEncoder passwordEncoder) {
        this.othelloPlayerRepository = othelloPlayerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<OthelloPlayer> getAllPlayers() {
        return this.othelloPlayerRepository.findAll();
    }

    public OthelloPlayer getPlayerById(Long id) throws PlayerNotFoundException {
        return this.othelloPlayerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }

    public OthelloPlayer getPlayerByUsername(String username) throws PlayerNotFoundException {
        return this.othelloPlayerRepository.findByUsername(username).orElseThrow(() -> new PlayerNotFoundException(username));
    }

    public OthelloPlayer create(String username, String password) {
        // Check if the username is already used.
        OthelloPlayer dbPlayer = this.othelloPlayerRepository.findByUsername(username).orElse(null);

        if (dbPlayer != null)
            throw new DuplicateUsernameException(username);

        // Encode the password
        String encodedPassword = this.passwordEncoder.encode(password);

        OthelloPlayer newPlayer = (OthelloPlayer) IOthelloPlayer.create()
                .setUsername(username)
                .setPassword(encodedPassword);

        return this.othelloPlayerRepository.save(newPlayer);
    }

}
