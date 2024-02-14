package com.mezcladito.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mezcladito.app.exception.NotFoundException;
import com.mezcladito.app.model.entity.Player;
import com.mezcladito.app.model.entity.PlayerList;
import com.mezcladito.app.repository.PlayerRepository;
import com.mezcladito.app.service.PlayerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public PlayerList getList(PageRequest pageRequest) {
        Page<Player> page = playerRepository.findAll(pageRequest);
        return new PlayerList(page.getContent(), pageRequest, page.getTotalElements());
    }

    @Override
    @Transactional
    public Long createEntity(Player player) {
        return playerRepository.save(player).getId();
    }

    @Override
    public Player getByIdIfExists(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @Transactional
    public void updateEntityIfExists(Long id, Player player) {
        playerRepository.findById(id)
                .map(playerJpa -> {
                    Optional.ofNullable(player.getName()).ifPresent(playerJpa::setName);
                    Optional.ofNullable(player.getAttack()).ifPresent(playerJpa::setAttack);
                    Optional.ofNullable(player.getDefense()).ifPresent(playerJpa::setDefense);
                    Optional.ofNullable(player.getSpeed()).ifPresent(playerJpa::setSpeed);
                    Optional.ofNullable(player.getGoalKeeper()).ifPresent(playerJpa::setGoalKeeper);

                    return playerRepository.save(playerJpa);
                }).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Player player = getByIdIfExists(id);
        playerRepository.delete(player);
    }

    @Override
    @Transactional
    public Player activateSwitch(Long id) {
        Player player = getByIdIfExists(id);
        if (player.getActive()) {
            player.setActive(false);
        }else{
            player.setActive(true);
        }
        return playerRepository.save(player);
    }

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> getActivePlayers() {
        return playerRepository.findAll()
                               .stream()
                               .filter(player -> player.getActive())
                               .collect(Collectors.toList());
    }

}
