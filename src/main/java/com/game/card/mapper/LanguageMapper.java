package com.game.card.mapper;

import com.game.card.dto.DtoCard;
import com.game.card.dto.dtoResponse.DtoLanguageResponse;
import com.game.card.model.Card;
import com.game.card.model.Language;
import com.game.card.repository.CardRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LanguageMapper {

    private final CardRepository cardRepository;

    public LanguageMapper(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public DtoLanguageResponse toDtoLanguageResponse(Language language) {
        DtoLanguageResponse dtoLanguageResponse = new DtoLanguageResponse();
        dtoLanguageResponse.setName(language.getName());
        List<Card> cards = cardRepository.findCardByLanguage(language.getName());

        List<DtoCard> dtoCards = new ArrayList<>();
        cards.forEach(card -> {
            DtoCard dtoCard = new DtoCard();
            dtoCard.setWord(card.getWord());
            dtoCard.setTranslation(card.getTranslation());
            dtoCard.setLanguage(card.getLanguage().getName());
            dtoCards.add(dtoCard);
        });
        dtoLanguageResponse.setCards(dtoCards);

        return dtoLanguageResponse;
    }
}