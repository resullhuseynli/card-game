package com.game.card.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "language")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Language {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Column(name = "language_name")
    private String name;

    @OneToMany(mappedBy = "language")
    private List<Card> cards;

}
