package com.mezcladito.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mezcladito.app.mapper.GameMapper;
import com.mezcladito.app.model.DTO.response.GameResponse;
import com.mezcladito.app.model.entity.Game;
import com.mezcladito.app.service.GameService;

import lombok.RequiredArgsConstructor;

import static com.mezcladito.app.api.ApiConstants.GAME_URI;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(GAME_URI)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class GameController {
    
    private final GameService gameService;

    private final GameMapper gameMapper;

    @GetMapping()
    public ResponseEntity<GameResponse> createGame() {
        
        Game game = gameService.startMixed();
        GameResponse response= gameMapper.gameToGameResponse(game);
        
        return ResponseEntity.ok(response);
    }
    


    
}
