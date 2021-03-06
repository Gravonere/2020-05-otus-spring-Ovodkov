package ru.otus.ovodkov.homework10.service;

import ru.otus.ovodkov.homework10.entity.Author;
import ru.otus.ovodkov.homework10.entity.Book;
import ru.otus.ovodkov.homework10.entity.Comment;
import ru.otus.ovodkov.homework10.entity.Genre;

import java.util.List;
import java.util.Set;

/**
 * Сервис работы с книгами
 *
 * @author Ovodkov Sergey
 * created on 11.08.2020
 */
public interface BookService {

    /**
     * Сохранить книгу
     *
     * @param book Сохраняемая книга
     * @return Сохраненная книга
     */
    Book saveBook(Book book);

    /**
     * Получить книгу по идентификатору
     *
     * @param idBook Идентификатор книги
     * @return Искомая книга
     */
    Book getBookById(long idBook);

    /**
     * Получить список книг
     *
     * @return Список всех книг
     */
    List<Book> getBooks();

    /**
     * Удалить книгу по идентификатору
     *
     * @param idBook Идентификатор книги
     */
    void delete(long idBook);

    /**
     * Получить список авторов указанной книги
     *
     * @param idBook Идентификатор книги
     * @return Списк авторов книги
     */
    Set<Author> getAuthorsBook(long idBook);

    /**
     * Получить список жанров указанной книги
     *
     * @param idBook Идентификатор книги
     * @return Список жанров книги
     */
    Set<Genre> getGenresBook(long idBook);

    /**
     * Получить комментарии указанной книги
     *
     * @param idBook Идентификатор книги
     * @return Список всех комментариев к книге
     */
    Set<Comment> getCommentsBook(long idBook);
}
