package com.pinson.othello.players.responses;

public record OthelloPlayerResponse(
    Long id,
    String username,
    String createdAt,
    String updatedAt,

    List<OthelloGamePlayerLightResponse> gamePlayers,
    OthelloLobbyLightResponse lobby
) {

}