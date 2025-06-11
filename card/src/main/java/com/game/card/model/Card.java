package com.game.card.model;

import com.game.card.enums.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String word;
    private String translation;
    private Language language;

}
