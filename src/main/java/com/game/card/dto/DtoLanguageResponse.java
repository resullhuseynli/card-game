package com.game.card.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoLanguageResponse {

    private String name;

    private List<DtoCard> cards;

}
