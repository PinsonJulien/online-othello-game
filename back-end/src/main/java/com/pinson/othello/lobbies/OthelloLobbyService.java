package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
import com.pinson.othello.players.IOthelloPlayer;
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

    public void deleteLobbyById(Long id) throws LobbyNotFoundException {
        this.othelloLobbyRepository.deleteById(id);
    }

    public OthelloLobby addPlayerToRandomLobby(IOthelloPlayer player, int maxPlayers) {

        //return this.othelloLobbyRepository.findAll()


        return this.othelloLobbyRepository.findLobbiesByMaxPlayersAndNotFull(maxPlayers).stream()
            .findFirst()
            .orElseGet(() -> {
                OthelloLobby lobby = null;
                try {
                    lobby = (OthelloLobby) IOthelloLobby.create(maxPlayers, player);
                } catch (NonPositiveValueException | NonEvenNumberException e) {
                    throw new RuntimeException(e);
                }

                return this.othelloLobbyRepository.save(lobby);
            });
    }

}
