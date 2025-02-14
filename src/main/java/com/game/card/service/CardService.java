package com.game.card.service;

import com.game.card.dto.DtoCard;
import com.game.card.dto.dtoRequest.DtoUpdateCard;

import java.util.List;

public interface CardService {

    DtoCard addNewCard(DtoCard card);

    DtoCard getCardById(Long id);

    void deleteCardById(Long id);

    List<DtoCard> getAllCards();

    List<DtoCard> getCardsByLanguage(String language);

    DtoCard updateCard(Long id, DtoUpdateCard cardUpdate);
}
