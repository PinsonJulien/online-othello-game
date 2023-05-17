package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.Game;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
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
import java.util.Set;

@Entity
public class OthelloGame extends Game<IOthelloTile, IOthelloGrid, IOthelloDisk> implements IOthelloGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OthelloGameStatus status;

    @ManyToOne
    @JoinColumn(name = "othello_player_id")
    private OthelloPlayer winner;

    @OneToMany(mappedBy = "game")
    private Set<OthelloGamePlayer> gamePlayers;

    @OneToMany(mappedBy = "game")
    private Set<OthelloMove> moves;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public OthelloGame() {
        super(IOthelloGrid.create());
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
    public Set<OthelloGamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }

    @Override
    public IOthelloGame setGamePlayers(Set<OthelloGamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;

        return this;
    }

    @Override
    public Set<OthelloMove> getMoves() {
        return this.moves;
    }

    @Override
    public IOthelloGame setMoves(Set<OthelloMove> moves) {
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

        for (ArrayList<IOthelloTile> adjacentTileRow : adjacentTiles) {
            for (IOthelloTile adjacentTile : adjacentTileRow) {
                if (adjacentTile.getPiece() != null && adjacentTile.getPiece().getOwner() != piece.getOwner()) {

                }
            }
        }
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

        // calculate the current turn player based on the number of moves played.
        int numberOfMovesPlayed = this.getMoves().size();
        int numberOfPlayers = this.getGamePlayers().size();

        if (numberOfMovesPlayed == 0)
            return this.getGamePlayers().stream().findFirst().orElse(null);

        if (numberOfMovesPlayed % numberOfPlayers == 0)
            return this.getGamePlayers().stream().findFirst().orElse(null);
        

        return null;
    }

    @Override
    public ArrayList<IOthelloTile> getValidMoves(IOthelloPlayer player) {
        IOthelloPlayer currentPlayer = this.getCurrentTurnPlayer();
        OthelloGamePlayerColor currentPlayerColor = currentPlayer.getColor();



        return null;
    }

}
