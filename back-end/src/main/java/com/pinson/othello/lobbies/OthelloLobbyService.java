package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OthelloLobbyService {
    private final OthelloLobbyRepository othelloLobbyRepository;
    private final OthelloPlayerRepository othelloPlayerRepository;

    @Autowired
    public OthelloLobbyService(OthelloLobbyRepository othelloLobbyRepository, OthelloPlayerRepository othelloPlayerRepository) {
        this.othelloLobbyRepository = othelloLobbyRepository;
        this.othelloPlayerRepository = othelloPlayerRepository;
    }

    public List<OthelloLobby> getAllLobbies() {
        return this.othelloLobbyRepository.findAll();
    }

    public OthelloLobby getLobbyById(Long id) throws LobbyNotFoundException {
        return this.othelloLobbyRepository.findById(id).orElseThrow(() -> new LobbyNotFoundException(id));
    }

    public void deleteLobbyById(Long id) throws LobbyNotFoundException {
        this.othelloLobbyRepository.deleteById(id);
    }

    public OthelloLobby addPlayerToRandomLobby(OthelloPlayer player, int maxPlayers) throws FullLobbyException, PlayerAlreadyInLobbyException, NonPositiveValueException, NonEvenNumberException {
        if (maxPlayers <= 0)
            throw new NonPositiveValueException("Max players must be positive");
        if (maxPlayers % 2 != 0)
            throw new NonEvenNumberException("Max players must be even");

        // Try to find a lobby that is not full, if not found, create a new one.
        OthelloLobby lobby = this.othelloLobbyRepository.findLobbiesByMaxPlayersAndNotFull(maxPlayers)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    OthelloLobby newLobby;

                    try {
                        newLobby = (OthelloLobby) IOthelloLobby.create(maxPlayers);
                    } catch (NonPositiveValueException | NonEvenNumberException e) {
                        throw new RuntimeException(e);
                    }

                    return newLobby;
                });

        lobby.addPlayer(player);
        this.othelloPlayerRepository.save(player);
        return othelloLobbyRepository.save(lobby);
    }
}
