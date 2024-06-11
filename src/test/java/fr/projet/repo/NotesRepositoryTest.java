package fr.projet.repo;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import fr.projet.model.Notes;

//@SpringBootTest

@DataJpaTest
@Sql(scripts = "/notes.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/clear-all.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class NotesRepositoryTest {

@Autowired
    private NotesRepository repository;

    @Test
    void shouldFindAllFindsTwo() {
        // given

        // when
        List<Notes> notes = this.repository.findAll();

        // then
        Assertions.assertEquals(4, notes.size());

        // when
        notes = this.repository.findByIdUtilisateur(200);

        // then
        Assertions.assertEquals(3, notes.size());

        // when
        notes = this.repository.findByIdUtilisateur(300);

        // then
        Assertions.assertEquals(1, notes.size());

        // when
        notes = this.repository.findByIdUtilisateur(400);

        // then
        Assertions.assertEquals(0, notes.size());
    }
}
