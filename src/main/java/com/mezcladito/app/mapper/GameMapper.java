package com.mezcladito.app.mapper;

import com.mezcladito.app.model.DTO.response.GameResponse;
import com.mezcladito.app.model.entity.Game;

public interface GameMapper {

    GameResponse gameToGameResponse(Game game);

}
