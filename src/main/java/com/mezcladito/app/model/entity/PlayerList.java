package com.mezcladito.app.model.entity;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PlayerList extends PageImpl<Player>{
    public PlayerList(List<Player> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
    
}
