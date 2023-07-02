package com.pinson.othello.lobbies.responses;

import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.lobbies.IOthelloLobby;

public class OthelloLobbyResponseFactory {
    public OthelloLobbyResponseFactory(

    ) {

    }

    public OthelloLobbyResponse create(IOthelloLobby lobby) {
        OthelloLobbyResponse response = new OthelloLobbyResponse();

        return response
            .setId(lobby.getId())
            .setMaxPlayers(lobby.getMaxPlayers())
            .setCreatedAt(lobby.getCreatedAt())
            .setGame(null);
    }

    public OthelloLobbyResponse create(IOthelloLobby lobby, IOthelloGame game) {
        return this.create(lobby).setGame(game);
    }

}
