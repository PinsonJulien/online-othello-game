package com.pinson.othello.lobbies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OthelloLobbyRepository extends JpaRepository<OthelloLobby, Long> {
    @Query("""
        SELECT l
        FROM OthelloLobby l
        WHERE
            (l.maxPlayers = :maxPlayers)
            AND l.maxPlayers > size(l.players)
    """)
    List<OthelloLobby> findLobbiesByMaxPlayersAndNotFull(
        @Param("maxPlayers") Integer maxPlayers
    );

    List<OthelloLobby> findAllByMaxPlayers(@Param("maxPlayers") Integer maxPlayers);

}
