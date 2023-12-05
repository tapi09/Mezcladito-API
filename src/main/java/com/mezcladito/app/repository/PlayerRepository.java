package com.mezcladito.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mezcladito.app.model.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

}
