package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.Game;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.grids.IOthelloGrid;
import com.pinson.othello.moves.OthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.tiles.IOthelloTile;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OthelloGame extends Game<IOthelloTile, IOthelloGrid, IOthelloDisk> implements IOthelloGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OthelloGameStatus status = OthelloGameStatus.IN_PROGRESS;

    @ManyToOne
    @JoinColumn(name = "othello_player_id")
    private OthelloPlayer winner = null;

    @OneToMany(mappedBy = "game")
    private List<OthelloGamePlayer> gamePlayers;

    @OneToMany(mappedBy = "game")
    private List<OthelloMove> moves;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public OthelloGame() {
        super();
    }

    public OthelloGame(List<OthelloGamePlayer> gamePlayers, int gridWidth, int gridHeight) throws InvalidNumberOfPlayersException, GridSizeException {
        super();

        if (gamePlayers.size() < 2)
            throw new InvalidNumberOfPlayersException("There must be at least 2 players.");

        if (gamePlayers.size() % 2 != 0)
            throw new InvalidNumberOfPlayersException("There must be an even number of players.");

        // set the game of each game player to this game
        for (OthelloGamePlayer gamePlayer : gamePlayers) {
            gamePlayer.setGame(this);
        }

        this.setGamePlayers(gamePlayers);

        this.setupGrid(gridWidth, gridHeight);

        this.postLoad();
    }

    @PostLoad
    private void postLoad() {
        // Place the initial disks on the grid.
        this.placeInitialDisks();

        // Play all the moves of the game.
        List<OthelloMove> moves = this.getMoves();

        if (moves == null || moves.size() == 0 )
            return;

        /*for (OthelloMove move : moves) {
            try {
                this.playMove(move);
            } catch (MatrixIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }*/
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
    public IOthelloPlayer getWinner() {
        return this.winner;
    }

    @Override
    public IOthelloGame setWinner(IOthelloPlayer winner) {
        this.winner = (OthelloPlayer) winner;

        return this;
    }

    @Override
    public List<OthelloGamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }

    @Override
    public IOthelloGame setGamePlayers(List<OthelloGamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;

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
    public void playMove(int x, int y, IOthelloDisk piece) throws MatrixIndexOutOfBoundsException {
        ArrayList<ArrayList<IOthelloTile>> adjacentTiles = this.getGrid().getAdjacentNeighbours(y, x);

        /*for (ArrayList<IOthelloTile> adjacentTileRow : adjacentTiles) {
            for (IOthelloTile adjacentTile : adjacentTileRow) {
                if (adjacentTile.getPiece() != null && adjacentTile.getPiece().getOwner() != piece.getOwner()) {

                }
            }
        }*/
    }

    @Override
    public void playMove(IMatrixPosition<Integer> position, IOthelloDisk piece) throws MatrixIndexOutOfBoundsException {
        this.playMove(position.getX(), position.getY(), piece);
    }

    @Override
    public void skipMove() {

    }

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
    public ArrayList<IOthelloTile> getValidMoves(IOthelloPlayer player) {
        IOthelloGamePlayer currentPlayer = this.getCurrentTurnPlayer();
        OthelloGamePlayerColor currentPlayerColor = currentPlayer.getPlayerColor();

        return null;
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
                // when the row is even place the disk on the left side
                // when the row is odd place the disk on the right side
                int x = (y%2 == 0)
                        ? middleSquareStartX + i
                        : middleSquareEndX - i;

                IOthelloDisk disk = IOthelloDisk.create(gamePlayer);

                try {
                    grid.setDiskAt(y + middleSquareStartY, x, disk);
                } catch (MatrixIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }

        }

    }

        // place the disks in the middle square





        // deal with any grid size
        // When the width of the grid is even place two disks of each color in the center of the grid
        // When the height of the grid is even place two disks of each color in the center of the grid
        // When the width of the grid is odd place three disks of each color in the center of the grid
        // When the height of the grid is odd place three disks of each color in the center of the grid

        // gather a set of all player colors
        // for each player color



        /*int gridWidth = grid.getWidth();
        int gridHeight = grid.getHeight();

        int halfGridWidth = gridWidth / 2;
        int halfGridHeight = gridHeight / 2;

        IOthelloDisk disk1 = IOthelloDisk.create(OthelloGamePlayerColor.BLACK);
        IOthelloDisk disk2 = IOthelloDisk.create(OthelloGamePlayerColor.WHITE);

        try {
            IOthelloTile tile1 = grid.getTileAt(halfGridWidth - 1, halfGridHeight - 1);
            IOthelloTile tile2 = grid.getTileAt(halfGridWidth, halfGridHeight - 1);
            IOthelloTile tile3 = grid.getTileAt(halfGridWidth - 1, halfGridHeight);
            IOthelloTile tile4 = grid.getTileAt(halfGridWidth, halfGridHeight);

            tile1.setPiece(disk1);
            tile2.setPiece(disk2);
            tile3.setPiece(disk2);
            tile4.setPiece(disk1);
        } catch (MatrixIndexOutOfBoundsException e) {
            e.printStackTrace();
        }*/
}

