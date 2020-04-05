package ru.otus.ovodkov.homework6.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.ovodkov.homework6.domain.Book;
import ru.otus.ovodkov.homework6.domain.Comment;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO для работы с хранилищем книг")
@DataJpaTest
@Import(BookDaoJpa.class)
public class BookDaoJpaTest {

    @Autowired
    private BookDaoJpa repositoryJpa;

    @Autowired
    private TestEntityManager em;

    private static final int EXPECTED_COUNT_BOOKS = 5;
    private static final long ID_BOOK = 1L;

    @DisplayName("возвращает количество книг в хранилище")
    @Test
    void shouldReturnExpectedCountBooks() {
        long actual = repositoryJpa.count();

        assertEquals(EXPECTED_COUNT_BOOKS, actual);
    }

    @DisplayName("возвращает книгу по указаному идентификатору")
    @Test
    void shouldReturnExpectedBook() {
        Optional<Book> optionalActualBook = repositoryJpa.getByBookId(ID_BOOK);
        Book expectedBook = em.find(Book.class, ID_BOOK);

        assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }

    @DisplayName("удаляем указаную книгу")
    @Test
    void shouldDeleteBook() {
        repositoryJpa.delete(ID_BOOK);
        long actual = repositoryJpa.count();

        assertEquals(EXPECTED_COUNT_BOOKS - 1, actual);
    }

    @DisplayName("должен загружать список всех книг с полной информацией по ним.")
    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        List<Book> books = repositoryJpa.getAllBooks();

        assertThat(books).isNotNull().hasSize(EXPECTED_COUNT_BOOKS)
                .allMatch(x -> !x.getTitleBook().equals(""))
                .allMatch(x -> x.getEdition() != 0)
                .allMatch(x -> x.getYearPublishing() != 0)
                .allMatch(x -> x.getComments() != null && x.getComments().size() > 0)
                .allMatch(x -> x.getAuthors() != null && x.getAuthors().size() > 0)
                .allMatch(x -> x.getGenres() != null && x.getGenres().size() > 0);
    }

    @DisplayName("должен добавлять комментарии к книги")
    @Test
    void shouldExpectedAddingComment() {
        Comment comment = new Comment();
        comment.setCommentBook("Тестовый комментарий");
        Optional<Book> bookOptional = repositoryJpa.addComment(ID_BOOK, comment);

        Book expectedBook = em.find(Book.class, ID_BOOK);

        assertThat(bookOptional).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);
    }
}
