package com.game.card.service;

import com.game.card.enums.Language;
import com.game.card.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {

    Card getCard(long id);
    List<Card> getCards();
    void addCard(Card card);
    void deleteCard(long id);
    void updateCard(long id, Card card);
    List<Card> getCardsByLanguage(Language name);

}
