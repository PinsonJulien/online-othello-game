package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerNotFoundException;
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

    public OthelloLobby addPlayerToRandomLobby(OthelloPlayer player, int maxPlayers) throws FullLobbyException, PlayerAlreadyInLobbyException, NonPositiveValueException, NonEvenNumberException {
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

        lobby.addPlayer(player);

        return othelloLobbyRepository.save(lobby);
    }

    public List<OthelloLobby> getAllLobbiesByMaxPlayers(Integer maxPlayers) {
        return this.othelloLobbyRepository.findAllByMaxPlayers(maxPlayers);
    }

    public OthelloLobby removePlayerFromLobby(OthelloPlayer player) throws LobbyNotFoundException {
        IOthelloLobby playerLobby = player.getLobby();
        if (playerLobby == null)
            return null;

        Long lobbyId = playerLobby.getId();

        OthelloLobby lobby = this.getLobbyById(lobbyId);

        try {
            lobby.removePlayer(player);
        } catch (PlayerNotFoundException e) {
            // This should never happen because the player helped to find that lobby.
            e.printStackTrace();
        }

        return this.othelloLobbyRepository.save(lobby);
    }
}
