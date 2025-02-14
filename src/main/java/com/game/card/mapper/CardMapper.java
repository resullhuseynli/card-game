package com.game.card.mapper;

import com.game.card.dto.DtoCard;
import com.game.card.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {


    public Card toCard(DtoCard dtoCard) {

        Card card = new Card();
        card.setWord(dtoCard.getWord().trim().toUpperCase().charAt(0) + dtoCard.getWord().trim().substring(1));
        card.setTranslation(dtoCard.getTranslation().trim().toUpperCase().charAt(0) + dtoCard.getTranslation().trim().substring(1));
        return card;

    }

    public DtoCard toDtoCard(Card card) {

        DtoCard dtoCard = new DtoCard();
        dtoCard.setWord(card.getWord());
        dtoCard.setTranslation(card.getTranslation());
        return dtoCard;

    }

}


