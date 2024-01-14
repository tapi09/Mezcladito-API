package com.mezcladito.app.model.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequest {
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("attack")
    private Integer attack;
    
    @JsonProperty("defense")
    private Integer defense;

    @JsonProperty("speed")
    private Integer speed;
    
    @JsonProperty("goalKeeper")
    private Boolean goalKeeper;

}
