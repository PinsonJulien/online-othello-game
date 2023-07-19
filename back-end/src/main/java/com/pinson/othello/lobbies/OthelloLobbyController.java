package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.games.OthelloGameService;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyResponse;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyResponseFactory;
import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
import com.pinson.othello.players.OthelloPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lobbies")
public class OthelloLobbyController {
    private final OthelloLobbyService othelloLobbyService;
    private final OthelloGameService othelloGameService;
    private final OthelloLobbyResponseFactory othelloLobbyResponseFactory;

    @Autowired
    public OthelloLobbyController(
        OthelloLobbyService othelloLobbyService,
        OthelloGameService othelloGameService,
        OthelloLobbyResponseFactory othelloLobbyResponseFactory
    ) {
        this.othelloLobbyService = othelloLobbyService;
        this.othelloGameService = othelloGameService;
        this.othelloLobbyResponseFactory = othelloLobbyResponseFactory;
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

    @PostMapping("/classic/join")
    public ResponseEntity<OthelloLobbyResponse> joinClassic() {
        // must be tested :
        OthelloPlayer player = (OthelloPlayer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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

        return ResponseEntity.ok(
            this.othelloLobbyResponseFactory.create(lobby)
        );
    }

    @PostMapping("/leave/{id}")
    public ResponseEntity<Void> leave(@PathVariable Long id) {
        // get user
        OthelloPlayer player = null;
        this.othelloLobbyService.removePlayerFromLobby(id, player);

        return ResponseEntity.ok().build();
    }

}
