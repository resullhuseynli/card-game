package com.game.card.service.Impl;

import com.game.card.dto.dtoRequest.DtoLanguageRequest;
import com.game.card.dto.dtoResponse.DtoLanguageResponse;
import com.game.card.mapper.LanguageMapper;
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

    private final LanguageMapper languageMapper;


    public LanguageServiceImpl(LanguageRepository languageRepository, CardRepository cardRepository, LanguageMapper languageMapper) {

        this.languageRepository = languageRepository;
        this.cardRepository = cardRepository;
        this.languageMapper = languageMapper;

    }


    @Override
    public DtoLanguageResponse addNewLanguage(DtoLanguageRequest dtoLanguageRequest) {

        Language language = new Language();
        language.setName(stringConverter(dtoLanguageRequest.getLanguageName()));

        if (languageRepository.findByLanguageName(language.getName()).isPresent()) {

            throw new IllegalArgumentException("Language already exists");

        } else {
            languageRepository.save(language);

            return languageMapper.toDtoLanguageResponse(language);
        }


    }

    @Override
    public DtoLanguageResponse getLanguageByName(String nameRequest) {

        Optional<Language> language = languageRepository.findByLanguageName(stringConverter(nameRequest));

        if (language.isEmpty()) {

            throw new EntityNotFoundException("Language not found");

        } else {

            return languageMapper.toDtoLanguageResponse(language.get());

        }

    }

    @Transactional
    @Override
    public void deleteLanguageByName(String nameRequest) {

        Optional<Language> language = languageRepository.findByLanguageName(stringConverter(nameRequest));

        if (language.isEmpty()) {

            throw new EntityNotFoundException("Language not found");

        }

        cardRepository.deleteByLanguage(language.get().getId());
        languageRepository.deleteByLanguageName(stringConverter(nameRequest));

    }

    @Override
    public List<DtoLanguageResponse> getAllLanguages() {

        List<Language> languageList = languageRepository.findAll();
        List<DtoLanguageResponse> response = new ArrayList<>();

        languageList.forEach(language -> {

            DtoLanguageResponse dtoLanguageResponse = languageMapper.toDtoLanguageResponse(language);
            response.add(dtoLanguageResponse);

        });

        return response;

    }

    @Override
    public DtoLanguageResponse updateLanguage(String nameRequest, DtoLanguageRequest dtoLanguageRequest) {

        Optional<Language> languageOptional = languageRepository.findByLanguageName(stringConverter(nameRequest));
        if (languageOptional.isEmpty()) {
            throw new EntityNotFoundException("Language not found");
        } else {
            Language language = languageOptional.get();
            String updatedName = stringConverter(dtoLanguageRequest.getLanguageName());
            language.setName(updatedName);
            languageRepository.save(language);

            return languageMapper.toDtoLanguageResponse(language);

        }

    }


    private String stringConverter(String name) {
        return name.toUpperCase().charAt(0) + name.substring(1).toLowerCase().trim();
    }

}
