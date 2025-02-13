package com.game.card.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class dtoCardRequest {

    private String word;

    private String languageName;

    private String translation;

}
