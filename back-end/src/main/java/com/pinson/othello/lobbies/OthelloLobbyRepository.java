package com.pinson.othello.lobbies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OthelloLobbyRepository extends JpaRepository<OthelloLobby, Long> {
    List<OthelloLobby> findAllByMaxPlayers(@Param("maxPlayers") Integer maxPlayers);
}
