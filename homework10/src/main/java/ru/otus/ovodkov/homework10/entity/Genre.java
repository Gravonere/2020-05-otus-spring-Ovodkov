package ru.otus.ovodkov.homework10.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Модель жанра книг
 *
 * @author Ovodkov Sergey
 * created on 11.08.2020
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Genres")
@NamedEntityGraph(name = "genreEntityGraph", attributeNodes = {@NamedAttributeNode("books")})
public class Genre {
    /**
     * Идентификатор жанра
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGenre")
    private long idGenre;

    /**
     * Название жанра
     */
    @Column(name = "nameGenre")
    private String nameGenre;

    /**
     * Описание жанра
     */
    @Column(name = "descriptionGenre")
    private String descriptionGenre;

    /**
     * Книги данного жанра
     */
    @JsonBackReference
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Book> books;
}
