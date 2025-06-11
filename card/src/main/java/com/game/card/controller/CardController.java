package com.game.card.controller;

import com.game.card.enums.Language;
import com.game.card.model.Card;
import com.game.card.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getCards() {
        List<Card> cards = cardService.getCards();
        return cards.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(cards);
    }

    @GetMapping
    public ResponseEntity<List<Card>> getCardsByLanguage(Language language) {
        List<Card> cards = cardService.getCardsByLanguage(language);
        return cards.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(cards);
    }

    @PostMapping
    public ResponseEntity<Void> addCard(@RequestBody Card card) {
        cardService.addCard(card);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateCard(@RequestBody Card card, @PathVariable long id) {
        cardService.updateCard(id, card);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

}
