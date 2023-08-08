package com.pinson.othello.games;

import com.pinson.othello.commons.services.ServerSentEventService;
import com.pinson.othello.games.dtos.responses.OthelloGameResponse;
import org.springframework.stereotype.Service;

@Service
public class OthelloGameServerSentEventService extends ServerSentEventService<OthelloGameResponse> {
    public OthelloGameServerSentEventService() {
        super();
    }
}
