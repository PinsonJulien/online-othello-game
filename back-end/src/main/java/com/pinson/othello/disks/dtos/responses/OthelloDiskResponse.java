package com.pinson.othello.disks.dtos.responses;

import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerLightResponse;

public record OthelloDiskResponse(
    OthelloGamePlayerLightResponse gamePlayer
) {

}
