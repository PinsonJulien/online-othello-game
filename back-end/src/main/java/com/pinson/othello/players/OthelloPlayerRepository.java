package com.pinson.othello.players;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OthelloPlayerRepository extends JpaRepository<OthelloPlayer, Long> {
    //
}
