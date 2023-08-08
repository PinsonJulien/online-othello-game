package com.pinson.othello.disks.dtos;

import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.disks.dtos.responses.OthelloDiskResponse;
import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerLightResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OthelloDiskDTOMapper {

    @Autowired
    public OthelloDiskDTOMapper() {
        //
    }

    public OthelloDiskResponse toResponse(
        final IOthelloDisk disk,
        final OthelloGamePlayerLightResponse gamePlayer
    ) {
        return new OthelloDiskResponse(
            gamePlayer
        );
    }
}
