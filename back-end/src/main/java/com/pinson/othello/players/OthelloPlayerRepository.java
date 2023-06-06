package com.pinson.othello.players;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OthelloPlayerRepository extends JpaRepository<OthelloPlayer, Long> {
    Optional<OthelloPlayer> findByUsername(
        @Param("username") String username
    );
}
