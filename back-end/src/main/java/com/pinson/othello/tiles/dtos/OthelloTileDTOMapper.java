package com.pinson.othello.tiles.dtos;

import com.pinson.othello.disks.dtos.responses.OthelloDiskResponse;
import com.pinson.othello.positions.dtos.responses.OthelloPositionResponse;
import com.pinson.othello.tiles.IOthelloTile;
import com.pinson.othello.tiles.dtos.responses.OthelloTileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OthelloTileDTOMapper {

    @Autowired
    public OthelloTileDTOMapper() {
        //
    }

    public OthelloTileResponse toResponse(
        final IOthelloTile tile,
        final OthelloPositionResponse position,
        final OthelloDiskResponse disk,
        final boolean isPlayable
    ) {
        return new OthelloTileResponse(
            position,
            disk,
            isPlayable
        );
    }

}
