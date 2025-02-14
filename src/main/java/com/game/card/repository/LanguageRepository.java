package com.game.card.repository;

import com.game.card.model.Language;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query("SELECT l FROM Language l WHERE l.name = :name")
    Optional<Language> findByLanguageName(String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Language l WHERE l.name = :name")
    void deleteByLanguageName(String name);
}
