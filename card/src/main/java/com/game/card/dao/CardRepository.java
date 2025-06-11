package com.game.card.dao;

import com.game.card.enums.Language;
import com.game.card.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select c from Card c where c.language= :language")
    List<Card> getCardsByLanguage(Language language);

}
