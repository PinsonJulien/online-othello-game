package com.pinson.othello.lobbies;

import com.pinson.othello.lobbies.OthelloLobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OthelloLobbyRepository extends JpaRepository<OthelloLobby, Long> {

}
