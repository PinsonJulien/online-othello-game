package com.pinson.othello.lobbies;

import com.pinson.othello.commons.services.ServerSentEventService;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyResponse;
import org.springframework.stereotype.Service;

@Service
public class OthelloLobbyServerSentEventService extends ServerSentEventService<OthelloLobbyResponse> {
    public OthelloLobbyServerSentEventService() {
        super();
    }
}
