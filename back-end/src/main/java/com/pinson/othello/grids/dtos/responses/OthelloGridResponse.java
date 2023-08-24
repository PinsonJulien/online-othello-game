package com.pinson.othello.grids.dtos.responses;

import com.pinson.othello.tiles.dtos.responses.OthelloTileResponse;

import java.util.List;

public record OthelloGridResponse(
    List<OthelloTileResponse> tiles,
    GridSizeResponse size
) {

}
