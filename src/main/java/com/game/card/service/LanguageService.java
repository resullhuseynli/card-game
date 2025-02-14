package com.game.card.service;

import com.game.card.dto.DtoLanguageRequest;
import com.game.card.dto.DtoLanguageResponse;

import java.util.List;

public interface LanguageService {

    DtoLanguageResponse addNewLanguage(DtoLanguageRequest dtoLanguageRequest);

    DtoLanguageResponse getLanguageByName(String name );

    void deleteLanguageByName(String name);

    List<DtoLanguageResponse> getAllLanguages();

    DtoLanguageResponse updateLanguage(String name, DtoLanguageRequest dtoLanguageRequest);
}
