package com.pinson.othello.lobbies.dtos;

import com.pinson.othello.games.dtos.responses.OthelloGameLightResponse;
import com.pinson.othello.lobbies.IOthelloLobby;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyLightResponse;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyResponse;
import com.pinson.othello.players.dtos.responses.OthelloPlayerLightResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloLobbyDTOMapper {

    public OthelloLobbyDTOMapper() {
        //
    }

    public OthelloLobbyResponse toResponse(
        IOthelloLobby lobby,
        List<OthelloPlayerLightResponse> players,
        OthelloGameLightResponse game
    ) {
        return new OthelloLobbyResponse(
            lobby.getId(),
            lobby.getMaxPlayers(),
            lobby.getCreatedAt(),
            players,
            game
        );
    }

    public OthelloLobbyLightResponse toLightResponse(IOthelloLobby lobby) {
        return new OthelloLobbyLightResponse(
            lobby.getId(),
            lobby.getMaxPlayers(),
            lobby.getCreatedAt()
        );
    }

    public List<OthelloLobbyLightResponse> toLightResponseList(List<? extends IOthelloLobby> lobbies) {
        List<OthelloLobbyLightResponse> response = new ArrayList<>();

        for (IOthelloLobby lobby : lobbies) {
            response.add(this.toLightResponse(lobby));
        }

        return response;
    }

}
