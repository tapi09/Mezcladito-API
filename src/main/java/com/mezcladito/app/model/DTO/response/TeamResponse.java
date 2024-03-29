package com.mezcladito.app.model.DTO.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("players")
    private Set<PlayerResponse> players;
}
