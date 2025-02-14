package com.game.card.controller;


import com.game.card.dto.dtoRequest.DtoLanguageRequest;
import com.game.card.dto.dtoResponse.DtoLanguageResponse;
import com.game.card.service.LanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/language")
@Tag(name = "Language", description = "Language API")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    @Operation(summary = "Get all languages", description = "Get all languages")
    public ResponseEntity<List<DtoLanguageResponse>> getLanguages() {

        return ResponseEntity.ok(languageService.getAllLanguages());

    }

    @PostMapping
    @Operation(summary = "Add language", description = "Add language")
    public ResponseEntity<DtoLanguageResponse> addLanguage(@RequestBody DtoLanguageRequest dtoLanguageRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(languageService.addNewLanguage(dtoLanguageRequest));

    }

    @GetMapping("/{name}")
    @Operation(summary = "Get language by name", description = "Get language by name")
    public ResponseEntity<DtoLanguageResponse> getLanguageById(@PathVariable String name) {

        return ResponseEntity.ok(languageService.getLanguageByName(name));

    }

    @DeleteMapping("{name}")
    @Operation(summary = "Delete language", description = "Delete language")
    public ResponseEntity<Void> deleteLanguage(@PathVariable String name) {

        languageService.deleteLanguageByName(name);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{name}")
    @Operation(summary = "Update language", description = "Update language")
    public ResponseEntity<DtoLanguageResponse> updateLanguage(@PathVariable String name, @RequestBody DtoLanguageRequest dtoLanguageRequest) {

        return ResponseEntity.ok(languageService.updateLanguage(name, dtoLanguageRequest));

    }

}
