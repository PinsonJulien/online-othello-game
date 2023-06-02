package com.pinson.othello.games;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OthelloGameRepository extends JpaRepository<OthelloGame, Long> {

}
