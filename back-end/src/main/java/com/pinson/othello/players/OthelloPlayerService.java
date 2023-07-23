package com.pinson.othello.players;

import com.pinson.othello.players.exceptions.DuplicateUsernameException;
import com.pinson.othello.players.exceptions.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OthelloPlayerService implements UserDetailsService {

    private final OthelloPlayerFactory othelloPlayerFactory;

    private final OthelloPlayerRepository othelloPlayerRepository;

    @Autowired
    public OthelloPlayerService(
        OthelloPlayerRepository othelloPlayerRepository,
        OthelloPlayerFactory othelloPlayerFactory
    ) {
        this.othelloPlayerRepository = othelloPlayerRepository;
        this.othelloPlayerFactory = othelloPlayerFactory;
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

    public OthelloPlayer create(String username, String password) throws DuplicateUsernameException {
        // Check if the username is already used.
        OthelloPlayer dbPlayer = this.othelloPlayerRepository.findByUsername(username).orElse(null);

        if (dbPlayer != null)
            throw new DuplicateUsernameException(username);

        OthelloPlayer newPlayer = this.othelloPlayerFactory.create(username, password);

        return this.othelloPlayerRepository.save(newPlayer);
    }

    public OthelloPlayer updatePassword(Long id, String newPassword) throws PlayerNotFoundException {
        OthelloPlayer player = this.getPlayerById(id);
        player.setPassword(newPassword);

        return this.othelloPlayerRepository.save(player);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OthelloPlayer player = this.othelloPlayerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return this.othelloPlayerFactory.create(player);
    }
}
