package com.pinson.othello.players;

import com.pinson.othello.players.exceptions.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OthelloPlayerService {

    private final OthelloPlayerRepository othelloPlayerRepository;

    @Autowired
    public OthelloPlayerService(OthelloPlayerRepository othelloPlayerRepository) {
        this.othelloPlayerRepository = othelloPlayerRepository;
    }

    public List<OthelloPlayer> getAllPlayers() {
        return this.othelloPlayerRepository.findAll();
    }

    public OthelloPlayer getPlayerById(Long id) throws PlayerNotFoundException {
        return this.othelloPlayerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }


}
