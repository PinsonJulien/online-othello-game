package com.pinson.othello.games.dtos.responses;

import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.disks.dtos.OthelloDiskDTOMapper;
import com.pinson.othello.disks.dtos.responses.OthelloDiskResponse;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.dtos.OthelloGamePlayerDTOMapper;
import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerLightResponse;
import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerResponse;
import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.dtos.OthelloGameDTOMapper;
import com.pinson.othello.grids.dtos.OthelloGridDTOMapper;
import com.pinson.othello.grids.dtos.responses.GridSizeResponse;
import com.pinson.othello.grids.dtos.responses.OthelloGridResponse;
import com.pinson.othello.moves.IOthelloMove;
import com.pinson.othello.players.dtos.OthelloPlayerDTOMapper;
import com.pinson.othello.positions.dtos.OthelloPositionDTOMapper;
import com.pinson.othello.tiles.dtos.OthelloTileDTOMapper;
import com.pinson.othello.tiles.dtos.responses.OthelloTileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloGameResponseFactory {

    private final OthelloGameDTOMapper othelloGameDTOMapper;
    private final OthelloPlayerDTOMapper othelloPlayerDTOMapper;
    private final OthelloGamePlayerDTOMapper othelloGamePlayerDTOMapper;
    private final OthelloGridDTOMapper othelloGridDTOMapper;
    private final OthelloTileDTOMapper othelloTileDTOMapper;
    private final OthelloPositionDTOMapper othelloPositionDTOMapper;
    private final OthelloDiskDTOMapper othelloDiskDTOMapper;

    @Autowired
    public OthelloGameResponseFactory(
        final OthelloGameDTOMapper othelloGameDTOMapper,
        final OthelloPlayerDTOMapper othelloPlayerDTOMapper,
        final OthelloGamePlayerDTOMapper othelloGamePlayerDTOMapper,
        final OthelloGridDTOMapper othelloGridDTOMapper,
        final OthelloTileDTOMapper othelloTileDTOMapper,
        final OthelloPositionDTOMapper othelloPositionDTOMapper,
        final OthelloDiskDTOMapper othelloDiskDTOMapper
    ) {
        this.othelloGameDTOMapper = othelloGameDTOMapper;
        this.othelloPlayerDTOMapper = othelloPlayerDTOMapper;
        this.othelloGamePlayerDTOMapper = othelloGamePlayerDTOMapper;
        this.othelloGridDTOMapper = othelloGridDTOMapper;
        this.othelloTileDTOMapper = othelloTileDTOMapper;
        this.othelloPositionDTOMapper = othelloPositionDTOMapper;
        this.othelloDiskDTOMapper = othelloDiskDTOMapper;
    }

    public OthelloGameResponse create(IOthelloGame game) {
        List<IOthelloMove> validMoves = game.getValidMoves();

        List<OthelloTileResponse> tiles = new ArrayList<>();

        game.getAllTiles().forEach(tile -> {
            IOthelloDisk disk = tile.getPiece();
            OthelloGamePlayerLightResponse player = (disk == null)
                ? null
                : this.othelloGamePlayerDTOMapper.toLightResponse(disk.getGamePlayer());

            OthelloDiskResponse diskResponse = (disk == null)
                ? null
                : this.othelloDiskDTOMapper.toResponse(disk, player);

            tiles.add(this.othelloTileDTOMapper.toResponse(
                tile,
                this.othelloPositionDTOMapper.toResponse(tile.getPosition()),
                diskResponse,
                // Look if the tile is in the list of valid moves
                validMoves.stream().anyMatch(move -> move.getPosition().equals(tile.getPosition()))
            ));
        });

        OthelloGridResponse gridResponse = this.othelloGridDTOMapper.toResponse(
            tiles,
            new GridSizeResponse(game.getGridWidth(), game.getGridHeight())
        );

        return this.othelloGameDTOMapper.toResponse(
            game,
            this.toOthelloGamePlayerResponseList(game.getGamePlayers()),
            this.toOthelloGamePlayerResponseList(game.getWinners()),
            this.toOthelloGamePlayerResponseList(game.getLosers()),
            gridResponse,
            this.toOthelloGamePlayerResponse(game.getCurrentTurnPlayer())
        );
    }

    public List<OthelloGameResponse> createList(List<? extends IOthelloGame> games) {
        List<OthelloGameResponse> response = new ArrayList<>();

        for (IOthelloGame game : games) {
            response.add(this.create(game));
        }

        return response;
    }

    public OthelloGameLightResponse createLight(IOthelloGame game) {
        return this.othelloGameDTOMapper.toLightResponse(game);
    }

    public List<OthelloGameLightResponse> createLightList(List<? extends IOthelloGame> games) {
        return this.othelloGameDTOMapper.toLightResponseList(games);
    }

    protected OthelloGamePlayerResponse toOthelloGamePlayerResponse(IOthelloGamePlayer gamePlayer) {
        return this.othelloGamePlayerDTOMapper.toResponse(
            gamePlayer,
            this.othelloGameDTOMapper.toLightResponse(gamePlayer.getGame()),
            this.othelloPlayerDTOMapper.toLightResponse(gamePlayer.getPlayer())
        );
    }

    protected List<OthelloGamePlayerResponse> toOthelloGamePlayerResponseList(List<? extends IOthelloGamePlayer> gamePlayers) {
        List<OthelloGamePlayerResponse> responses = new ArrayList<>();

        for (IOthelloGamePlayer gamePlayer : gamePlayers) {
            responses.add(this.toOthelloGamePlayerResponse(gamePlayer));
        }

        return responses;
    }
}
