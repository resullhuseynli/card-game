package com.game.card.service.impl;

import com.game.card.dao.CardRepository;
import com.game.card.enums.Language;
import com.game.card.model.Card;
import com.game.card.service.CardService;

import java.util.List;

public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card getCard(long id) {
        return cardRepository.getCardsById(id);
    }

    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    public void addCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void deleteCard(long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public void updateCard(long id, Card card) {
        Card c = cardRepository.getCardsById(id);
        c.setWord(card.getWord());
        c.setLanguage(card.getLanguage());
        c.setTranslation(card.getTranslation());
        cardRepository.save(c);
    }

    @Override
    public List<Card> getCardsByLanguage(Language language) {
        return cardRepository.getCardsByLanguage(language);
    }
}
