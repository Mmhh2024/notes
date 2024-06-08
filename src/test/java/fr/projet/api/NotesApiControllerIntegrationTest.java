package fr.projet.api;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.projet.model.Notes;
import fr.projet.repo.NotesRepository;
import fr.projet.response.CreateNotesRequest;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/products.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/clear-all.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)

public class NotesApiControllerIntegrationTest {
private final static String ENDPOINT = "/api/product";

    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @SpyBean
    private NotesRepository repository;

    @BeforeEach
    public void beforeEach() {
        this.mapper = new ObjectMapper();
    }

    @Test
    void shouldFindAllStatusUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    //@WithMockUser(roles = "USER")
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))); // Pour vérifier son id en sortie
    }

    @Test
    void shouldCreateStatusCreatedUnauthorized() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result.andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    //@WithMockUser(roles = "USER")
    void shouldCreateStatusCreated() throws Exception {
        // given
        ArgumentCaptor<Notes> notesCaptor = ArgumentCaptor.forClass(Notes.class);
        CreateNotesRequest request = new CreateNotesRequest();

        request.setNom("Nom test");
        //request.setPrice(new BigDecimal("57.98"));

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT)
                .content(this.mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        Mockito.verify(repository).save(notesCaptor.capture()); // On capture le produit passé en tant qu'argument de la méthode save

        Notes note = notesCaptor.getValue();

        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(note.getId())); // Pour vérifier son id en sortie
    }


}
