package com.pinson.othello.players;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OthelloPlayerRepository extends JpaRepository<OthelloPlayer, Long> {
    OthelloPlayer findByUsername(
        @Param("username") String username
    );
}
