package com.pinson.othello.lobbies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lobbies")
public class OthelloLobbyController {
    private final OthelloLobbyService othelloLobbyService;

    @Autowired
    public OthelloLobbyController(
        OthelloLobbyService othelloLobbyService
    ) {
        this.othelloLobbyService = othelloLobbyService;
    }

    /*
    @PostMapping("/classic/join")
    public ResponseEntity<> joinClassic(
    ) {

    }

    @PostMapping("/leave/{id}")
    public ResponseEntity<> leave(

    ) {

    }
     */

}
