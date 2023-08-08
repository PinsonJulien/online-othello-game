package com.pinson.othello.positions.dtos;

import com.pinson.othello.positions.IOthelloPosition;
import com.pinson.othello.positions.dtos.responses.OthelloPositionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OthelloPositionDTOMapper {

    @Autowired
    public OthelloPositionDTOMapper() {
        //
    }

    public OthelloPositionResponse toResponse(
        IOthelloPosition position
    ) {
        return new OthelloPositionResponse(
            position.getRow(),
            position.getColumn()
        );
    }

}
