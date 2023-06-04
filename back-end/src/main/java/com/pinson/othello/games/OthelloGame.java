package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.Game;
import com.pinson.othello.commons.entities.games.exceptions.GameOverException;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidMoveException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.games.exceptions.CannotPassTurnException;
import com.pinson.othello.games.exceptions.UnknownGamePlayerException;
import com.pinson.othello.grids.IOthelloGrid;
import com.pinson.othello.moves.IOthelloMove;
import com.pinson.othello.moves.OthelloMove;
import com.pinson.othello.positions.IOthelloPosition;
import com.pinson.othello.tiles.IOthelloTile;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class OthelloGame extends Game<IOthelloTile, IOthelloGrid, IOthelloDisk> implements IOthelloGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int gridWidth;
    private int gridHeight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OthelloGameStatus status = OthelloGameStatus.IN_PROGRESS;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OthelloGamePlayer> gamePlayers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "othello_game_winners",
        joinColumns = @JoinColumn(name = "othello_game_id"),
        inverseJoinColumns = @JoinColumn(name = "othello_game_player_id")
    )
    private List<OthelloGamePlayer> winners;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "othello_game_losers",
        joinColumns = @JoinColumn(name = "othello_game_id"),
        inverseJoinColumns = @JoinColumn(name = "othello_game_player_id")
    )
    private List<OthelloGamePlayer> losers;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OthelloMove> moves = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected OthelloGame() {
        super();
    }

    public OthelloGame(List<OthelloGamePlayer> gamePlayers, int gridWidth, int gridHeight) throws InvalidNumberOfPlayersException, GridSizeException {
        super();

        if (gamePlayers.size() < 2)
            throw new InvalidNumberOfPlayersException("There must be at least 2 players.");

        if (gamePlayers.size() % 2 != 0)
            throw new InvalidNumberOfPlayersException("There must be an even number of players.");

        this.setGamePlayers(gamePlayers);

        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        this.postLoad();
    }

    @PostLoad
    private void postLoad() throws GridSizeException {
        // Set the status back to in progress, this will be updated by the replay of the moves.
        this.status = OthelloGameStatus.IN_PROGRESS;

        // Generate the grid
        this.setupGrid(gridWidth, gridHeight);

        // Place the initial disks on the grid.
        this.placeInitialDisks();

        // Play all the moves of the game.
        List<OthelloMove> moves = this.getMoves();

        if (moves == null || moves.size() == 0 )
            return;

        for (OthelloMove move : moves) {
            try {
                this.playMove(move, false);
            } catch (Exception e) {
                // This should never happen.
                e.printStackTrace();
            }
        }
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public IOthelloGame setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public OthelloGameStatus getStatus() {
        return this.status;
    }

    @Override
    public IOthelloGame setStatus(OthelloGameStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public List<OthelloGamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }

    @Override
    public IOthelloGame setGamePlayers(List<OthelloGamePlayer> gamePlayers) {
        this.gamePlayers = new ArrayList<>();

        // set the game of each game player.
        for (OthelloGamePlayer gamePlayer : gamePlayers) {
            gamePlayer.setGame(this);
            this.gamePlayers.add(gamePlayer);
        }

        return this;
    }

    @Override
    public List<OthelloGamePlayer> getWinners() {
        return this.winners;
    }

    protected IOthelloGame setWinners(List<OthelloGamePlayer> winners) {
        this.winners = winners;
        return this;
    }

    @Override
    public List<OthelloGamePlayer> getLosers() {
        return this.losers;
    }

    protected IOthelloGame setLosers(List<OthelloGamePlayer> losers) {
        this.losers = losers;
        return this;
    }


    @Override
    public List<OthelloMove> getMoves() {
        return this.moves;
    }

    @Override
    public IOthelloGame setMoves(List<OthelloMove> moves) {
        this.moves = moves;

        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public IOthelloGame setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;

        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public IOthelloGame setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;

        return this;
    }

    // Implemented methods.

    @Override
    public IOthelloGamePlayer getCurrentTurnPlayer() {
        List<OthelloMove> moves = this.getMoves();
        List<OthelloGamePlayer> gamePlayers = this.getGamePlayers();

        int numberOfMovesPlayed = moves.size();
        int numberOfPlayers = gamePlayers.size();

        if (numberOfMovesPlayed == 0)
            return this.getGamePlayers().get(0);

        // calculate the current turn player based on the number of moves played.
        int currentPlayerIndex = numberOfMovesPlayed % numberOfPlayers;

        return gamePlayers.get(currentPlayerIndex);
    }

    @Override
    public List<IOthelloMove> getValidMoves() {
        return this.getValidMoves(this.getCurrentTurnPlayer());
    }

    @Override
    public List<IOthelloMove> getValidMoves(IOthelloGamePlayer gamePlayer) {
        List<IOthelloMove> validMoves = new ArrayList<>();
        // Set to handle duplicate positions and moves, dynamic programming.
        Set<IOthelloPosition> visitedTiles = new HashSet<>();
        Set<IOthelloPosition> visitedMoves = new HashSet<>();

        // Search around every disk that are not owned by the given player.
        List<IOthelloDisk> disks = this.getAllDisks();

        OthelloGamePlayerColor playerColor = gamePlayer.getPlayerColor();
        IOthelloGrid grid = this.getGrid();

        for (IOthelloDisk disk : disks) {
            // Skip if the disk is owned by the current player.
            if (disk.getGamePlayer().getPlayerColor() == playerColor)
                continue;

            // get the neighbouring tiles of the disk.
            List<List<IOthelloTile>> neighbouringTiles = new ArrayList<>();
            try {
                neighbouringTiles = grid.getAdjacentNeighbours(disk.getTile());
            } catch (Exception e) {
                // this should never happen.
                e.printStackTrace();
            }

            // check if the neighbouring tiles are valid moves.
            for (List<IOthelloTile> neighbouringTileLine : neighbouringTiles) {
                for (IOthelloTile neighbouringTile : neighbouringTileLine) {
                    IOthelloPosition position = neighbouringTile.getPosition();

                    // check if the tile has already been visited.
                    if (visitedTiles.contains(position))
                        continue;

                    visitedTiles.add(position);

                    // check if the tile is empty.
                    if (neighbouringTile.getPiece() != null)
                        continue;

                    // check if the tile is a valid move.
                    IOthelloMove move = IOthelloMove.create().setGamePlayer(gamePlayer).setPosition(position);
                    // Don't check already visited moves.
                    if (visitedMoves.contains(position))
                        continue;

                    if (this.isMoveValid(move))
                        validMoves.add(move);

                    visitedMoves.add(position);
                }
            }
        }

        return validMoves;
    }

    @Override
    public IOthelloMove getRandomValidMove() {
        List<IOthelloMove> validMoves = this.getValidMoves();
        if (validMoves.size() == 0)
            return null;

        Collections.shuffle(validMoves);
        return validMoves.get(0);
    }

    @Override
    public boolean isMoveValid(IOthelloMove move) {
        try {
            IOthelloTile tile = this.getTileAt(move.getRow(), move.getColumn());

            // check if the tile is empty.
            if (tile.getPiece() != null)
                return false;

            OthelloGamePlayerColor currentPlayerColor = move.getGamePlayer().getPlayerColor();
            List<List<IOthelloTile>> adjacentTiles = this.getGrid().getAdjacentNeighbours(tile);

            for (List<IOthelloTile> adjacentTileRow : adjacentTiles) {
                // check if the row is empty.
                if (adjacentTileRow.size() == 0)
                    continue;

                // check if the adjacent tile has a piece of the opposite color.
                IOthelloDisk firstAdjacentDisk = adjacentTileRow.get(0).getPiece();
                if (firstAdjacentDisk == null || firstAdjacentDisk.getGamePlayer().getPlayerColor() == currentPlayerColor)
                    continue;

                // Look for a piece of the same color on the other side of the adjacent tile.
                for (IOthelloTile adjacentTile : adjacentTileRow) {
                    // When we find a piece of the same color, the move is valid.
                    IOthelloDisk disk = adjacentTile.getPiece();

                    // If we find an empty tile, the move is invalid.
                    if (disk == null)
                        break;

                    if (disk.getGamePlayer().getPlayerColor() == currentPlayerColor)
                        return true;
                }
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public IOthelloGame playMove(IOthelloMove move) throws InvalidMoveException, GameOverException, CannotPassTurnException, UnknownGamePlayerException {
        // Check if the game is still in progress.
        if (this.isGameOver())
            throw new GameOverException();

        IOthelloGamePlayer moveGamePlayer = move.getGamePlayer();
        if (!this.gamePlayers.contains((OthelloGamePlayer) moveGamePlayer))
            throw new UnknownGamePlayerException("The GamePlayer set in the Move does not exist in this game scope.");

        // Check if the player is allowed to play.
        IOthelloGamePlayer currentPlayer = this.getCurrentTurnPlayer();

        if (!Objects.equals(currentPlayer.getId(), moveGamePlayer.getId()))
            throw new InvalidMoveException("The player is not allowed to play.");

        move.setGame(this);

        // check if there's available moves, if there's not, skip the turn.
        List<IOthelloMove> validMoves = this.getValidMoves(moveGamePlayer);
        if (validMoves.size() == 0)
            move.setPassed(true);
        else if (move.isPassed()) {
            // if there's possible moves, and the given move was passed, we return an error.
            throw new CannotPassTurnException("The move of the player cannot be passed because there's possible moves available.");
        }

        return this.playMove(move, true);
    }

    protected IOthelloGame playMove(IOthelloMove move, boolean addToMoves) throws InvalidMoveException {
        IOthelloGamePlayer moveGamePlayer = move.getGamePlayer();

        // check if the move is a pass move.
        if (move.isPassed()) {
            if (addToMoves) this.getMoves().add((OthelloMove) move);

            return this;
        }

        // Check if the move is valid.
        if (!this.isMoveValid(move))
            throw new InvalidMoveException("The position of the move is not valid.");

        // Place the disk on the tile.
        IOthelloDisk disk = IOthelloDisk.create(move.getGamePlayer());
        int row = move.getRow();
        int column = move.getColumn();

        try {
            this.setDiskAt(row, column, disk);

            // Flip the aligned disks.
            OthelloGamePlayerColor currentPlayerColor = moveGamePlayer.getPlayerColor();
            List<List<IOthelloTile>> adjacentTiles = this.getGrid().getAdjacentNeighbours(row, column);

            for (List<IOthelloTile> adjacentTileRow : adjacentTiles) {
                // Check if there is at least one neighbor in the list.
                if (adjacentTileRow.size() == 0)
                    continue;

                // check if the adjacent tile has a piece of the opposite color.
                IOthelloDisk firstAdjacentDisk = adjacentTileRow.get(0).getPiece();
                if (firstAdjacentDisk == null || firstAdjacentDisk.getGamePlayer().getPlayerColor() == currentPlayerColor)
                    continue;

                // look for the first disk of the same color as the placed disk.
                int lastAdjacentDiskIndex = adjacentTileRow.size();
                int firstFoundDiskIndex = -1;
                boolean foundEmptyTile = false;

                for (int i = 1; i < lastAdjacentDiskIndex && firstFoundDiskIndex == -1 && !foundEmptyTile; i++) {
                    IOthelloDisk adjacentDisk = adjacentTileRow.get(i).getPiece();
                    // If an empty tile is found, stop looking for disks of the same color.

                    if (adjacentDisk == null)
                        foundEmptyTile = true;
                    else if (adjacentDisk.getGamePlayer().getPlayerColor() == currentPlayerColor)
                        firstFoundDiskIndex = i;
                }

                // If no disk of the same color was found, continue to the next adjacent tile row.
                if (firstFoundDiskIndex < 0)
                    continue;

                // Flip the disks between the placed disk and the first disk of the same color.
                for (int i = 0; i < firstFoundDiskIndex; i++) {
                    IOthelloDisk adjacentDisk = adjacentTileRow.get(i).getPiece();

                    if (adjacentDisk != null)
                        adjacentDisk.setGamePlayer(moveGamePlayer);
                }
            }
        } catch (MatrixIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        if (addToMoves)
            this.getMoves().add((OthelloMove) move);

        if (this.isGameOver())
            this.endGame();

        return this;
    }

    @Override
    public IOthelloGame skipMove() throws GameOverException, InvalidMoveException, CannotPassTurnException, UnknownGamePlayerException {
        IOthelloGamePlayer currentPlayer = this.getCurrentTurnPlayer();
        IOthelloMove skipMove = IOthelloMove.create().setGamePlayer(currentPlayer).setPassed(true);

        return this.playMove(skipMove);
    }

    protected int calculateMiddleSquareSize() {
        return this.gamePlayers.size();
    }

    private void setupGrid(int width, int height) throws GridSizeException {
        // Handle the grid size.
        int middleSquareSize = this.calculateMiddleSquareSize();

        if (width < middleSquareSize + 2)
            throw new GridSizeException("The width must be superior by 2 to the middle square size.");

        if (height < middleSquareSize + 2)
            throw new GridSizeException("The height must be superior by 2 to the middle square size.");

        this.setGrid(IOthelloGrid.create(width, height));
    }

    private void placeInitialDisks() {
        IOthelloGrid grid = this.getGrid();
        List<OthelloGamePlayer> gamePlayers = this.getGamePlayers();
        int middleSquareSize = this.calculateMiddleSquareSize();

        int gridWidth = grid.getRows();
        int gridHeight = grid.getColumns();

        int middleSquareStartX = (gridWidth / 2) - (middleSquareSize / 2);
        int middleSquareStartY = (gridHeight / 2) - (middleSquareSize / 2);
        int middleSquareEndX = middleSquareStartX + middleSquareSize - 1;

        // place the disks in the middle square
        for (int i = 0; i < middleSquareSize; i++) {
            IOthelloGamePlayer gamePlayer = gamePlayers.get(i);

            // Place one disk per row
            for (int y = 0; y < middleSquareSize; y++) {
                // when the row is odd place the disk on the left side
                // when the row is even place the disk on the right side
                int x = (y%2 != 0)
                        ? middleSquareStartX + i
                        : middleSquareEndX - i;

                IOthelloDisk disk = IOthelloDisk.create(gamePlayer);

                try {
                    this.setDiskAt(y + middleSquareStartY, x, disk);
                } catch (MatrixIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public IOthelloGame setDiskAt(int row, int column, IOthelloDisk disk) throws MatrixIndexOutOfBoundsException {
        this.getTileAt(row, column).setPiece(disk);

        return this;
    }

    @Override
    public IOthelloDisk getDiskAt(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getPieceAt(row, column);
    }

    @Override
    public List<IOthelloDisk> getAllDisks() {
        return super.getAllPieces();
    }

    @Override
    public IOthelloGrid getGrid() {
        return super.getGrid();
    }

    @Override
    public boolean isGameOver() {
        return (
          this.getStatus() == OthelloGameStatus.FINISHED
          ||
          !this.canAnyPlayerPlay()
        );
    }

    protected boolean canAnyPlayerPlay() {
        List<OthelloGamePlayer> gamePlayers = this.getGamePlayers();

        for (IOthelloGamePlayer gamePlayer : gamePlayers) {
            if (this.canPlayerPlay(gamePlayer))
                return true;
        }

        return false;
    }

    protected boolean canPlayerPlay(IOthelloGamePlayer gamePlayer) {
        List<IOthelloMove> possibleMoves = this.getValidMoves(gamePlayer);
        return (possibleMoves.size() > 0);
    }

    protected int countDisksOfColor(OthelloGamePlayerColor color) {
        int count = 0;

        for (IOthelloDisk disk : this.getAllDisks()) {
            if (disk.getGamePlayer().getPlayerColor() == color)
                count++;
        }

        return count;
    }

    private void endGame() {
        this.setStatus(OthelloGameStatus.FINISHED);

        // calculate winners, losers and their scores
        List<OthelloGamePlayer> gamePlayers = this.getGamePlayers();
        Set<OthelloGamePlayerColor> teams = new HashSet<>();

        for (OthelloGamePlayer gamePlayer : gamePlayers) {
            teams.add(gamePlayer.getPlayerColor());
        }

        // Set the score for all players of the same team
        for (OthelloGamePlayerColor team : teams) {
            int teamScore = this.countDisksOfColor(team);

            for (OthelloGamePlayer gamePlayer : gamePlayers) {
                if (gamePlayer.getPlayerColor() == team)
                    gamePlayer.setScore(teamScore);
            }
        }

        // Set the winner(s) and loser(s)
        int maxScore = -1;
        List<OthelloGamePlayer> winners = new ArrayList<>();
        List<OthelloGamePlayer> losers = new ArrayList<>();

        for (OthelloGamePlayer gamePlayer : gamePlayers) {
            int playerScore = gamePlayer.getScore();

            if (playerScore > maxScore) {
                maxScore = playerScore;
                losers.addAll(winners);
                winners.clear();
                winners.add(gamePlayer);
            } else if (playerScore == maxScore) {
                winners.add(gamePlayer);
            } else {
                losers.add(gamePlayer);
            }
        }

        this.setWinners(winners);
        this.setLosers(losers);
    }

}

