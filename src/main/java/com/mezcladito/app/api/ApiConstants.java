package com.mezcladito.app.api;

import java.util.function.Function;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public interface ApiConstants {
    String PLAYER_URI = "/v1/player";
    String ACTIVE_SWITCH= "/switch";
    String TEAM_URI = "/v1/team";


    int DEFAULT_PAGE = 0;
    int DEFAULT_PAGE_SIZE = 10;

    Function<Integer, String> uriByPageAsString = (page) ->
            ServletUriComponentsBuilder.fromCurrentRequest()
                    .replaceQueryParam("page", page).toUriString();
    
}
