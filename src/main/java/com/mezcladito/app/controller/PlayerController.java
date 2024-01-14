package com.mezcladito.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mezcladito.app.api.ApiConstants;
import com.mezcladito.app.mapper.PlayerMapper;
import com.mezcladito.app.model.DTO.request.CreatePlayerRequest;
import com.mezcladito.app.model.DTO.request.UpdatePlayerRequest;
import com.mezcladito.app.model.DTO.response.PlayerResponse;
import com.mezcladito.app.model.DTO.response.PlayerResponseList;
import com.mezcladito.app.model.entity.Player;
import com.mezcladito.app.model.entity.PlayerList;
import com.mezcladito.app.service.PlayerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import static com.mezcladito.app.api.ApiConstants.ACTIVE_SWITCH;
import static com.mezcladito.app.api.ApiConstants.PLAYER_URI;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(PLAYER_URI)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class PlayerController {
 
    private final PlayerMapper playerMapper;
    
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<Void> createPlayer(@Valid @RequestBody CreatePlayerRequest createPlayerRequest) {

        Player player = playerMapper.createPlayerRequestToPlayer(createPlayerRequest);

        final long id = playerService.createEntity(player);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }
     @GetMapping("/all")
    public ResponseEntity<List<Player>> getPlayers() {
    return ResponseEntity.ok(playerService.getAll());
                                                
    }


    @GetMapping
    public ResponseEntity<PlayerResponseList> getPlayersPage(@RequestParam Optional<Integer> page,
                                                           @RequestParam Optional<Integer> size) {

        final int pageNumber = page.filter(p -> p > 0).orElse(ApiConstants.DEFAULT_PAGE);
        final int pageSize = size.filter(s -> s > 0).orElse(ApiConstants.DEFAULT_PAGE_SIZE);

        PlayerList list = playerService.getList(PageRequest.of(pageNumber, pageSize));

        PlayerResponseList response;
        {
            response = new PlayerResponseList();
            List<PlayerResponse> content = playerMapper.playerListToPlayerResponseList(list.getContent());
            response.setContent(content);

            final int nextPage = list.getPageable().next().getPageNumber();
            response.setNextUri(ApiConstants.uriByPageAsString.apply(nextPage));

            final int previousPage = list.getPageable().previousOrFirst().getPageNumber();
            response.setPreviousUri(ApiConstants.uriByPageAsString.apply(previousPage));

            response.setTotalPages(list.getTotalPages());
            response.setTotalElements(list.getTotalElements());
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getPlayer(@Valid @NotNull @PathVariable Long id) {
        Player player = playerService.getByIdIfExists(id);
        PlayerResponse response = playerMapper.playerToPlayerResponse(player);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePlayer(@Valid @NotNull @PathVariable Long id, @Valid @RequestBody UpdatePlayerRequest updatePlayerRequest) {
    
        Player player = playerMapper.updatePlayerRequestToPlayer(updatePlayerRequest);
        
        playerService.updateEntityIfExists(id, player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@Valid @NotNull @PathVariable Long id){
        playerService.deleteById(id);
    }
    @GetMapping(ACTIVE_SWITCH+"/{id}")
    ResponseEntity<PlayerResponse> activateSwitch(@Valid @NotNull @PathVariable Long id){
        Player player=playerService.activateSwitch(id);
        PlayerResponse playerResponse = playerMapper.playerToPlayerResponse(player);
        return ResponseEntity.ok(playerResponse);
    }

}
