package com.mezcladito.app.service;


import org.springframework.data.domain.PageRequest;

import com.mezcladito.app.model.entity.Player;
import com.mezcladito.app.model.entity.PlayerList;



public interface PlayerService{
    PlayerList getList(PageRequest pageRequest);

    Player getByIdIfExists(Long id);

    Long createEntity(Player player);

    void updateEntityIfExists(Long id, Player player);

    void deleteById(Long id);

    Player activateSwitch(Long id);
}
