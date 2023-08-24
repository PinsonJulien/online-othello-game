package com.pinson.othello.tiles.dtos.responses;

import com.pinson.othello.disks.dtos.responses.OthelloDiskResponse;
import com.pinson.othello.positions.dtos.responses.OthelloPositionResponse;

public record OthelloTileResponse (
  OthelloPositionResponse position,
  OthelloDiskResponse disk,
  boolean playable
) {

}
