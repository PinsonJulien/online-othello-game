package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerNotFoundInLobbyException;
import com.pinson.othello.players.OthelloPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OthelloLobbyService {
    private final OthelloLobbyRepository othelloLobbyRepository;

    @Autowired
    public OthelloLobbyService(OthelloLobbyRepository othelloLobbyRepository) {
        this.othelloLobbyRepository = othelloLobbyRepository;
    }

    public List<OthelloLobby> getAllLobbies() {
        return this.othelloLobbyRepository.findAll();
    }

    public OthelloLobby getLobbyById(Long id) throws LobbyNotFoundException {
        return this.othelloLobbyRepository.findById(id).orElseThrow(() -> new LobbyNotFoundException(id));
    }

    public void deleteLobbyById(Long id) {
        this.othelloLobbyRepository.deleteById(id);
    }

    public OthelloLobby addPlayerToRandomLobby(OthelloPlayer player, int maxPlayers) throws NonPositiveValueException, NonEvenNumberException {
        if (maxPlayers <= 0)
            throw new NonPositiveValueException("Max players must be positive");
        if (maxPlayers % 2 != 0)
            throw new NonEvenNumberException("Max players must be even");

        // Try to find a lobby that is not full, if not found, create a new one.
        // Note : The filter could be removed if there was a working option using the repository, alas none seems to work accurately.
        OthelloLobby lobby = this.getAllLobbiesByMaxPlayers(maxPlayers)
            .stream()
            .filter((OthelloLobby l) -> l.getPlayers().size() < maxPlayers)
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

        try {
            lobby.addPlayer(player);
        } catch (PlayerAlreadyInLobbyException | FullLobbyException e) {
            // This should never happen.
            throw new RuntimeException(e);
        }

        return othelloLobbyRepository.save(lobby);
    }

    public OthelloLobby addPlayerToRandomClassicLobby(OthelloPlayer player) {
        try {
            return this.addPlayerToRandomLobby(player, 2);
        } catch (NonPositiveValueException | NonEvenNumberException e) {
            // This should never happen.
        }

        return null;
    }

    public List<OthelloLobby> getAllLobbiesByMaxPlayers(Integer maxPlayers) {
        return this.othelloLobbyRepository.findAllByMaxPlayers(maxPlayers);
    }

    public OthelloLobby removePlayerFromLobby(Long id, OthelloPlayer player) throws LobbyNotFoundException {
        OthelloLobby lobby = this.getLobbyById(id);

        try {
            lobby.removePlayer(player);
        } catch (PlayerNotFoundInLobbyException e) {
            return lobby;
        }

        return this.othelloLobbyRepository.save(lobby);
    }
}
