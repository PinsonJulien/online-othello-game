package com.pinson.othello.grids.dtos;

import com.pinson.othello.grids.dtos.responses.GridSizeResponse;
import com.pinson.othello.grids.dtos.responses.OthelloGridResponse;
import com.pinson.othello.tiles.dtos.responses.OthelloTileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OthelloGridDTOMapper {

    @Autowired
    public OthelloGridDTOMapper() {
        //
    }

    public OthelloGridResponse toResponse(
        final List<OthelloTileResponse> tiles,
        final GridSizeResponse size
    ) {
        return new OthelloGridResponse(tiles, size);
    }
}
