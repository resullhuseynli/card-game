package com.game.card.repository;

import com.game.card.model.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {


    @Query("SELECT c FROM Card c WHERE c.language.name = :language")
    List<Card> findCardByLanguage(String language);


    @Transactional
    @Modifying
    @Query("DELETE FROM Card c WHERE c.language.id = :id")
    void deleteByLanguage(Long id);
}
