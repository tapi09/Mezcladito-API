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
public class PlayerResponse {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("attack")
    private Integer attack;

    @JsonProperty("defense")
    private Integer defense;

    @JsonProperty("goalKeeper")
    private Boolean goalKeeper;

    @JsonProperty("active")
    private Boolean active;

}
