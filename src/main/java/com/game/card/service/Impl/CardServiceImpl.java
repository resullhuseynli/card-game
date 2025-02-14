package com.game.card.service.Impl;


import com.game.card.dto.DtoCard;
import com.game.card.dto.dtoRequest.DtoUpdateCard;
import com.game.card.mapper.CardMapper;
import com.game.card.model.Card;
import com.game.card.model.Language;
import com.game.card.repository.CardRepository;
import com.game.card.repository.LanguageRepository;
import com.game.card.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    private final LanguageRepository languageRepository;

    private final CardMapper cardMapper;


    public CardServiceImpl(CardRepository cardRepository, LanguageRepository languageRepository, CardMapper cardMapper) {

        this.cardRepository = cardRepository;
        this.languageRepository = languageRepository;
        this.cardMapper = cardMapper;

    }


    @Override
    public DtoCard addNewCard(DtoCard dtoCard) {

        Card card = cardMapper.toCard(dtoCard);
        Optional<Language> language = languageRepository.findByLanguageName(dtoCard.getLanguage());

        if (language.isEmpty()) {

            throw new EntityNotFoundException("Language not found");

        }

        card.setLanguage(language.get());
        cardRepository.save(card);

        return dtoCard;

    }

    @Override
    public DtoCard getCardById(Long id) {

        Card card = cardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Card not found"));
        DtoCard dtoCard = cardMapper.toDtoCard(card);
        dtoCard.setLanguage(card.getLanguage().getName());

        return dtoCard;

    }

    @Override
    public void deleteCardById(Long id) {

        Optional<Card> cardOptional = cardRepository.findById(id);

        if (cardOptional.isEmpty()) {

            throw new EntityNotFoundException("Card not found");

        } else {

            cardRepository.deleteById(id);

        }

    }

    @Override
    public List<DtoCard> getAllCards() {

        List<Card> cardList= cardRepository.findAll();
        List<DtoCard> response = new ArrayList<>();

        for (Card card : cardList) {

            DtoCard dtoCard = cardMapper.toDtoCard(card);
            dtoCard.setLanguage(card.getLanguage().getName());
            response.add(dtoCard);

        }

        return response;

    }

    @Override
    public List<DtoCard> getCardsByLanguage(String language) {

        List<Card> cardList = cardRepository.findCardByLanguage(language);
        List<DtoCard> response = new ArrayList<>();

        cardList.forEach(card -> {

            DtoCard dtoCard = cardMapper.toDtoCard(card);
            dtoCard.setLanguage(card.getLanguage().getName());
            response.add(dtoCard);

        });

        return response;

    }

    @Override
    public DtoCard updateCard(Long id, DtoUpdateCard dtoCardUpdate) {

        Optional<Card> cardOptional = cardRepository.findById(id);

        if (cardOptional.isEmpty()) {

            throw new EntityNotFoundException("Card not found");

        } else {

            Card cardToUpdate = cardOptional.get();

            cardToUpdate.setTranslation(dtoCardUpdate.getTranslation());
            cardToUpdate.setWord(dtoCardUpdate.getWord());

            cardRepository.save(cardToUpdate);

            DtoCard dtoCard = cardMapper.toDtoCard(cardToUpdate);
            dtoCard.setLanguage(cardToUpdate.getLanguage().getName());

            return dtoCard;
        }


    }

}
