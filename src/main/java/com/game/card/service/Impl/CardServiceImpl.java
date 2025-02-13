package com.game.card.service.Impl;


import com.game.card.model.Card;
import com.game.card.repository.CardRepository;
import com.game.card.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card addNewCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Card with id " + id + " not found"));
    }

    @Override
    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

}
