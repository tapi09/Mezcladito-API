package com.mezcladito.app.mapper;

import org.mapstruct.Mapper;
import com.mezcladito.app.model.DTO.response.GameResponse;
import com.mezcladito.app.model.entity.Game;

@Mapper
public interface GameMapper {

    GameResponse gameToGameResponse(Game game);

}
