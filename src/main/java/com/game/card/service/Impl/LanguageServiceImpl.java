package com.game.card.service.Impl;

import com.game.card.dto.DtoCard;
import com.game.card.dto.DtoLanguageRequest;
import com.game.card.dto.DtoLanguageResponse;
import com.game.card.model.Card;
import com.game.card.model.Language;
import com.game.card.repository.CardRepository;
import com.game.card.repository.LanguageRepository;
import com.game.card.service.LanguageService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService {


    private final LanguageRepository languageRepository;

    private final CardRepository cardRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository, CardRepository cardRepository) {
        this.languageRepository = languageRepository;
        this.cardRepository = cardRepository;
    }


    @Override
    public DtoLanguageResponse addNewLanguage(DtoLanguageRequest dtoLanguageRequest) {

        DtoLanguageResponse dtoLanguageResponse = new DtoLanguageResponse();
        Language language = new Language();
        String dtoName = dtoLanguageRequest.getLanguageName();
        dtoName = dtoName.toUpperCase().charAt(0) + dtoName.substring(1).toLowerCase().trim();
        language.setName(dtoName);
        if (languageRepository.findByLanguageName(language.getName()).isPresent()) {
            throw new IllegalArgumentException("Language already exists");
        } else {
            languageRepository.save(language);
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

    @Override
    public DtoLanguageResponse getLanguageByName(String nameRequest) {

        String name = nameRequest.toUpperCase().charAt(0) + nameRequest.substring(1).toLowerCase().trim();
        Optional<Language> language = languageRepository.findByLanguageName(name);
        if (language.isEmpty()) {
            throw new EntityNotFoundException("Language not found");
        } else {
        DtoLanguageResponse dtoLanguageResponse = new DtoLanguageResponse();
        dtoLanguageResponse.setName(language.get().getName());
        List<Card> cards = cardRepository.findCardByLanguage(language.get().getName());
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

    @Transactional
    @Override
    public void deleteLanguageByName(String nameRequest) {

        String name = nameRequest.toUpperCase().charAt(0) + nameRequest.substring(1).toLowerCase().trim();
        Optional<Language> language = languageRepository.findByLanguageName(name);
        if (language.isEmpty()) {
            throw new EntityNotFoundException("Language not found");
        }
        cardRepository.deleteByLanguage(language.get().getId());
        languageRepository.deleteByLanguageName(name);

    }

    @Override
    public List<DtoLanguageResponse> getAllLanguages() {

        List<Language> languageList = languageRepository.findAll();
        List<DtoLanguageResponse> response = new ArrayList<>();
        languageList.forEach(language -> {
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

            response.add(dtoLanguageResponse);
        });

        return response;

    }

    @Override
    public DtoLanguageResponse updateLanguage(String nameRequest, DtoLanguageRequest dtoLanguageRequest) {

        String name = nameRequest.toUpperCase().charAt(0) + nameRequest.substring(1).toLowerCase().trim();
        Optional<Language> languageOptional = languageRepository.findByLanguageName(name);
        if (languageOptional.isEmpty()) {
            throw new EntityNotFoundException("Language not found");
        } else {
            Language language = languageOptional.get();
            String updatedName = (dtoLanguageRequest.getLanguageName());
            updatedName = updatedName.toUpperCase().charAt(0) + updatedName.substring(1).toLowerCase().trim();
            language.setName(updatedName);
            languageRepository.save(language);
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


}
