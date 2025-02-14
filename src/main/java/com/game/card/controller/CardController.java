package com.game.card.controller;


import com.game.card.dto.DtoCard;
import com.game.card.dto.DtoUpdateCard;
import com.game.card.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@Tag(name = "Card", description = "Card API")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping
    @Operation(summary = "Get all cards", description = "Get all cards from the database")
    public ResponseEntity<List<DtoCard>> getAllCards() {

        return ResponseEntity.ok(cardService.getAllCards());

    }

    @PostMapping
    @Operation(summary = "Create a new card", description = "Create a new card in the database")
    public ResponseEntity<DtoCard> createCard(DtoCard dtoCard) {

        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.addNewCard(dtoCard));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a card by id", description = "Get a card by id from the database")
    public ResponseEntity<DtoCard> getCardById(@PathVariable Long id) {

        return ResponseEntity.ok(cardService.getCardById(id));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a card by id", description = "Delete a card by id from the database")
    public ResponseEntity<Void> deleteCardById(@PathVariable Long id) {

        cardService.deleteCardById(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/by-language")
    @Operation(summary = "Get cards by language", description = "Get cards by language from the database")
    public ResponseEntity<List<DtoCard>> getCardsByLanguage(@RequestParam String language) {

        return ResponseEntity.ok(cardService.getCardsByLanguage(language));

    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a card by id", description = "Update a card by id in the database")
    public ResponseEntity<DtoCard> updateCard(@PathVariable Long id, @RequestBody DtoUpdateCard dtoCard) {

        return ResponseEntity.ok(cardService.updateCard(id, dtoCard));

    }

}
