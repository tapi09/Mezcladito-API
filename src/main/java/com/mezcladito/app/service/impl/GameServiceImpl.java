package com.mezcladito.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mezcladito.app.model.entity.Game;
import com.mezcladito.app.repository.PlayerRepository;
import com.mezcladito.app.service.GameService;
import com.mezcladito.app.model.entity.Player;
import com.mezcladito.app.model.entity.Team;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{
    
    private PlayerRepository playerRepository;

    @Override
    public Game startMixed() {
        Game game = new Game();
        List<Player> players = playerRepository.findAll().stream().filter(player -> true == player.getActive()).collect(Collectors.toList());
        Team teamA= new Team();
        Team teamB= new Team();
        int count=0;
        for (Player player : players) {
            
            if (count % 2 == 0){
                teamA.getPlayers().add(player);
            }else{
                teamB.getPlayers().add(player);
            }
            count ++;
        }
        game.setTeamA(teamA);
        
        game.setTeamB(teamB);
        return game;
    }

    
}
