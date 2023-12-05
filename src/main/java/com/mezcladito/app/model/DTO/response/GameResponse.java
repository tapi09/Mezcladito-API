package com.mezcladito.app.model.DTO.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("team_a")
    private TeamResponse teamA;
    
    @JsonProperty("team_b")
    private TeamResponse teamB;
    
}
