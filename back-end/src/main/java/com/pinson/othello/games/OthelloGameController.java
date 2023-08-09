package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.exceptions.GameOverException;
import com.pinson.othello.commons.exceptions.InvalidMoveException;
import com.pinson.othello.errors.ErrorResponseFactory;
import com.pinson.othello.games.dtos.requests.OthelloGamePlayMoveRequest;
import com.pinson.othello.games.dtos.responses.OthelloGameResponseFactory;
import com.pinson.othello.games.exceptions.GameNotFoundException;
import com.pinson.othello.games.exceptions.UnknownGamePlayerException;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerService;
import com.pinson.othello.positions.OthelloPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/v1/games")
public class OthelloGameController {

    private final OthelloGameService gameService;
    private final OthelloPlayerService playerService;
    private final OthelloGameResponseFactory gameResponseFactory;
    private final OthelloGameServerSentEventService gameServerSentEventService;
    private final ErrorResponseFactory errorResponseFactory;

    @Autowired
    public OthelloGameController(
        final OthelloGameService gameService,
        final OthelloPlayerService playerService,
        final OthelloGameResponseFactory gameResponseFactory,
        final OthelloGameServerSentEventService gameServerSentEventService,
        final ErrorResponseFactory errorResponseFactory
    ) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.gameResponseFactory = gameResponseFactory;
        this.gameServerSentEventService = gameServerSentEventService;
        this.errorResponseFactory = errorResponseFactory;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGameById(@PathVariable Long id) {
        try {
            OthelloGame game = this.gameService.getGameById(id);
            return ResponseEntity.ok(
                this.gameResponseFactory.create(game)
            );
        } catch (GameNotFoundException e) {
            return this.errorResponseFactory.createResponse(HttpStatus.NOT_FOUND, "The requested game doesn't exist.");
        }
    }

    @GetMapping("/{id}/sse")
    public SseEmitter streamGameIdEvents(@PathVariable Long id) {
        // -1L for infinite timeout.

        SseEmitter emitter = new SseEmitter(-1L);

        this.gameServerSentEventService.add(emitter, id);
        return emitter;
    }

    @PostMapping("/{id}/playMove")
    public ResponseEntity<Object> playMove(
        @PathVariable Long id,
        @RequestBody OthelloGamePlayMoveRequest playMoveRequest
    ) {
        OthelloPlayer player = (OthelloPlayer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        player = this.playerService.getPlayerById(player.getId());

        OthelloGame game = null;

        try {
            game = this.gameService.getGameById(id);
        } catch (GameNotFoundException e) {
            return this.errorResponseFactory.createResponse(HttpStatus.NOT_FOUND, "The requested game doesn't exist.");
        }

        OthelloPosition position;

        try {
            position = new OthelloPosition(playMoveRequest.row(), playMoveRequest.column());
        } catch (Exception e) {
            return this.errorResponseFactory.createResponse(HttpStatus.BAD_REQUEST, "The provided position is invalid.");
        }

        try {
            game = this.gameService.playMove(game, player, position);
        } catch (UnknownGamePlayerException e) {
            return this.errorResponseFactory.createResponse(HttpStatus.BAD_REQUEST, "The player isn't part of the game.");
        } catch (InvalidMoveException e) {
            return this.errorResponseFactory.createResponse(HttpStatus.BAD_REQUEST, "Either the move is invalid or it is not the player's turn.");
        } catch (GameOverException e) {
            return this.errorResponseFactory.createResponse(HttpStatus.BAD_REQUEST, "The game is over.");
        }

        return ResponseEntity.ok(
            this.gameResponseFactory.create(game)
        );
    }
}
