package com.mezcladito.app.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import com.mezcladito.app.model.DTO.request.CreatePlayerRequest;
import com.mezcladito.app.model.DTO.request.UpdatePlayerRequest;
import com.mezcladito.app.model.DTO.response.PlayerResponse;
import com.mezcladito.app.model.entity.Player;

@Mapper
public interface PlayerMapper {
    @IterableMapping(qualifiedByName = "playerToPlayerResponse")
    List<PlayerResponse> playerListToPlayerResponseList(List<Player> players);

    @Named("playerToPlayerResponse")
    PlayerResponse playerToPlayerResponse(Player player);
    
    Player createPlayerRequestToPlayer(CreatePlayerRequest createPlayerRequest);  
    
    Player updatePlayerRequestToPlayer(UpdatePlayerRequest updatePlayerRequest);
    
    
}

