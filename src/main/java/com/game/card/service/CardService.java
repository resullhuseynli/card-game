package com.game.card.service;

import com.game.card.model.Card;

import java.util.List;

public interface CardService {

    Card addNewCard(Card card);

    Card getCardById(Long id);

    void deleteCardById(Long id);

    List<Card> getAllCards();
}
