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
    
    private final PlayerRepository playerRepository;

    @Override
    public Game startMixed() {
        Game game = new Game();
        List<Player> activePlayers = playerRepository.findAll()
                                    .stream()
                                    .filter(player -> player.getActive())
                                    .collect(Collectors.toList());
        Team teamA= new Team();
        Team teamB= new Team();
        int count=0;
        for (Player player : activePlayers) {
            //desarrollar la lógica para mezclar jugadores de forma pareja segun condicion
            //condiciones: si hay 1 solo arquero el equipo contrario debe tener el mejor delantero
            //si hay 2 arqueros 1 para cada lado
            //revisar los atributos de Player para--- 
            //---aplicar distintas lógicas de análisis de jugadores agregando o quitando atributos
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
