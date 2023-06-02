package com.pinson.othello.games;

import com.pinson.othello.games.exceptions.GameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OthelloGameService {
    private final OthelloGameRepository gameRepository;

    @Autowired
    public OthelloGameService(OthelloGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<OthelloGame> getAllGames() {
        return this.gameRepository.findAll();
    }

    public OthelloGame getGameById(Long id) throws GameNotFoundException {
        return this.gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
    }

}
