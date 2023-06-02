package com.pinson.othello.games;

import com.pinson.othello.ApplicationTestConfiguration;
import com.pinson.othello.commons.entities.games.exceptions.GameOverException;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidMoveException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.moves.IOthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { ApplicationTestConfiguration.class })
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OthelloGameServiceTest {
    @Autowired
    private OthelloGameService gameService;

    @Autowired
    private OthelloGameRepository gameRepository;

    private List<OthelloGame> games = new ArrayList<>();
    private List<OthelloGamePlayer> gamePlayers = new ArrayList<>();
    private OthelloGame finishedGame;

    @BeforeEach
    void setUp() throws GridSizeException, InvalidNumberOfPlayersException {
        // Generate players
        for (long i = 0L; i < 30; i++) {
            IOthelloPlayer player = IOthelloPlayer.create().setId(i).setUsername("player"+i);
            OthelloGamePlayerColor color = (i%2 == 0) ? OthelloGamePlayerColor.BLACK : OthelloGamePlayerColor.WHITE;
            this.gamePlayers.add((OthelloGamePlayer) IOthelloGamePlayer.create(player, color));
        }

        // Generate games at different states
        // game of 2 players, typical 8x8, just started.
        OthelloGame game = (OthelloGame) IOthelloGame.create(gamePlayers.subList(0, 2), 8, 8);
        this.games.add(game);

        // game of 4 players, 10x10, two turned passed, have 8 moves in.
        OthelloGame game2 = (OthelloGame) IOthelloGame.create(gamePlayers.subList(2, 6), 10, 10);
        // moves :
        this.games.add(game2);

        // game of 6 players, 8x8, finished.
        this.finishedGame = (OthelloGame) IOthelloGame.create(gamePlayers.subList(24, 30), 8, 8);
        // moves :
        this.games.add(this.finishedGame);



        List<IOthelloMove> lowSkipList = new ArrayList<>();
        int lowest = 9999;

        for (int i = 0; i < 20000; i++) {
            System.out.println(i);
            OthelloGame gamefake = (OthelloGame) IOthelloGame.create(this.gamePlayers.subList(0, 4), 10, 10);
            List<IOthelloMove> moves = new ArrayList<>();

            for (int j = 0; j < 8; j++) {
                try {
                    IOthelloMove randomMove = gamefake.getRandomValidMove();
                    moves.add(randomMove);
                } catch(Exception e) {
                    break;
                }
            }

            if (gamefake.)

            while(true) {
                if (lowest <= moves.size())
                    break;

                List<IOthelloMove> playable = gamefake.getValidMoves();
                if (playable.size() == 0 && gamefake.canAnyPlayerPlay()) {
                    System.out.println("Found better !");
                    lowest = moves.size();
                    lowSkipList = moves;
                    break;
                }

                IOthelloMove randomMove = gamefake.getRandomValidMove();
                moves.add(randomMove);
                try {
                    gamefake.playMove(randomMove);
                } catch (Exception e) {
                    break;
                }
            }
        }

        System.out.println("Lowest : " + lowest);
        for (IOthelloMove move : lowSkipList) {
            System.out.println(move.getPosition().getStandardNotation());
        }

        this.games = this.gameRepository.saveAll(this.games);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllGames() {

    }

    @Test
    void getGameById() {

    }

}




    // current lowest finished.
        /*
        *   Lowest : 8
E6
F6
C4 // ou D3
E7
E8
D8
G6 // ou G7
F8
*
* another
*
* C4
C3
E6
B4
A4
A5
C2
A3
*
        *
        *
        *
        *
        *
        *
        *
        * */

    // ------------------------------------

 /* smalest finished game
    List<IOthelloMove> lowSkipList = new ArrayList<>();

    int lowest = 9999;

        for (int i = 0; i < 20000; i++) {
        System.out.println(i);
        OthelloGame gamefake = (OthelloGame) IOthelloGame.create(this.gamePlayers.subList(0, 2), 8, 8);
        List<IOthelloMove> moves = new ArrayList<>();

        while(true) {
        if (lowest <= moves.size())
        break;

        List<IOthelloMove> playable = gamefake.getValidMoves();
        if (gamefake.isGameOver()) {
        System.out.println("Found better !");
        lowest = moves.size();
        lowSkipList = moves;
        break;
        }

        IOthelloMove randomMove = gamefake.getRandomValidMove();
        moves.add(randomMove);
        try {
        gamefake.playMove(randomMove);
        } catch (Exception e) {
        break;
        }
        }
        }

        System.out.println("Lowest : " + lowest);
        for (IOthelloMove move : lowSkipList) {
        System.out.println(move.getPosition().getStandardNotation());
        }


// early skip best : 8
            /*List<IOthelloMove> lowSkipList = new ArrayList<>();
            int lowest = 9999;

            for (int i = 0; i < 20000; i++) {
                System.out.println(i);
                OthelloGame gamefake = (OthelloGame) IOthelloGame.create(this.gamePlayers.subList(0, 2), 8, 8);
                List<IOthelloMove> moves = new ArrayList<>();

                while(true) {
                    if (lowest <= moves.size())
                        break;

                    List<IOthelloMove> playable = gamefake.getValidMoves();
                    if (playable.size() == 0 && gamefake.canAnyPlayerPlay()) {
                        System.out.println("Found better !");
                        lowest = moves.size();
                        lowSkipList = moves;
                        break;
                    }

                    IOthelloMove randomMove = gamefake.getRandomValidMove();
                    moves.add(randomMove);
                    try {
                        gamefake.playMove(randomMove);
                    } catch (Exception e) {
                        break;
                    }
                }
            }

            System.out.println("Lowest : " + lowest);
            for (IOthelloMove move : lowSkipList) {
                System.out.println(move.getPosition().getStandardNotation());
            }*/

// ------------------------------------