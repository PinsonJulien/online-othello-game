package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.games.OthelloGameService;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyResponse;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyResponseFactory;
import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/v1/lobbies")
public class OthelloLobbyController {
    private final OthelloLobbyService othelloLobbyService;
    private final OthelloGameService othelloGameService;
    private final OthelloPlayerService othelloPlayerService;
    private final OthelloLobbyResponseFactory othelloLobbyResponseFactory;

    private final OthelloLobbyServerSentEventService othelloLobbyServerSentEventService;

    @Autowired
    public OthelloLobbyController(
        OthelloLobbyService othelloLobbyService,
        OthelloGameService othelloGameService,
        OthelloPlayerService othelloPlayerService,
        OthelloLobbyResponseFactory othelloLobbyResponseFactory,
        OthelloLobbyServerSentEventService othelloLobbyServerSentEventService
    ) {
        this.othelloLobbyService = othelloLobbyService;
        this.othelloGameService = othelloGameService;
        this.othelloPlayerService = othelloPlayerService;
        this.othelloLobbyResponseFactory = othelloLobbyResponseFactory;
        this.othelloLobbyServerSentEventService = othelloLobbyServerSentEventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OthelloLobbyResponse> getLobbyById(@PathVariable Long id) {
        try {
            OthelloLobby lobby = this.othelloLobbyService.getLobbyById(id);
            return ResponseEntity.ok(
                this.othelloLobbyResponseFactory.create(lobby)
            );
        } catch (LobbyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/sse")
    public SseEmitter streamLobbyIdEvents(@PathVariable Long id) {
        // -1L is for infinite timeout.
        SseEmitter emitter = new SseEmitter(-1L);
        this.othelloLobbyServerSentEventService.add(emitter, id);

        return emitter;
    }

    @PostMapping("/join/classic")
    public ResponseEntity<OthelloLobbyResponse> joinClassic() {
        OthelloPlayer player = (OthelloPlayer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        player = this.othelloPlayerService.getPlayerById(player.getId());

        OthelloLobby lobby = this.othelloLobbyService.addPlayerToRandomClassicLobby(player);

        if (lobby.isFull()) {
            // start game
            try {
                OthelloGame game = this.othelloGameService.startClassicGame(lobby);
                lobby = game.getLobby();
            } catch (InvalidNumberOfPlayersException e) {
                // this should never happen.
            }
        }

        OthelloLobbyResponse response = this.othelloLobbyResponseFactory.create(lobby);

        // send response to all players in lobby
        this.othelloLobbyServerSentEventService.send(response, lobby.getId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/leave")
    public ResponseEntity<Void> leave(@PathVariable Long id) {
        OthelloPlayer player = (OthelloPlayer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        player = this.othelloPlayerService.getPlayerById(player.getId());

        OthelloLobby lobby = this.othelloLobbyService.removePlayerFromLobby(id, player);

        OthelloLobbyResponse response = this.othelloLobbyResponseFactory.create(lobby);

        // send response to all players in lobby
        this.othelloLobbyServerSentEventService.send(response, lobby.getId());

        return ResponseEntity.ok().build();
    }

}
