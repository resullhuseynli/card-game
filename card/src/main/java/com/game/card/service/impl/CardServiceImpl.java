package com.game.card.service.impl;

import com.game.card.model.Card;
import com.game.card.service.CardService;

import java.util.List;

public class CardServiceImpl implements CardService {
    @Override
    public Card getCard(long id) {
        return null;
    }

    @Override
    public List<Card> getCards() {
        return List.of();
    }

    @Override
    public void addCard(Card card) {

    }

    @Override
    public void deleteCard(long id) {

    }

    @Override
    public void updateCard(Card card) {

    }

    @Override
    public List<Card> getCardsByLanguage(String name) {
        return List.of();
    }
}
