package com.pinson.othello.lobbies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OthelloLobbyService {
    private final OthelloLobbyRepository othelloLobbyRepository;

    @Autowired
    public OthelloLobbyService(OthelloLobbyRepository othelloLobbyRepository) {
        this.othelloLobbyRepository = othelloLobbyRepository;
    }

    public List<OthelloLobby> getAllLobbies() {
        return this.othelloLobbyRepository.findAll();
    }

}
