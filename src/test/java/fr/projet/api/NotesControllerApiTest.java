package fr.projet.api;

import java.util.List;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.projet.model.Notes;
import fr.projet.repo.NotesRepository;
import fr.projet.response.CreateNotesRequest;

@ExtendWith(MockitoExtension.class)
public class NotesControllerApiTest {
	private final static String ENDPOINT = "/api/notes";

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Mock
    private NotesRepository repository;

    @InjectMocks
    private NotesControllerApi ctrl;

    @BeforeEach
    public void beforeEach() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.ctrl).build();
        this.mapper = new ObjectMapper();
    }

    @Test
    void shouldFindAllStatusOk() throws Exception {
        // given

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldFindAllCallsRepository() throws Exception {
        // given
        Mockito.when(this.repository.findAll()).thenReturn(List.of(new Notes(), new Notes(), new Notes(), new Notes()));

        // when
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT));

        // then
        result.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        result.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)));

        Mockito.verify(this.repository).findAll();
    }

    @Test
    void shouldCreateStatusCreated() throws Exception {
        // given
        Notes note = new Notes();
        CreateNotesRequest request = new CreateNotesRequest();

        request.setIdUtilisateur(200);
        request.setNom("Nom test created");
        request.setContenu("contenu un created");
        request.setDescription("Description test created");


        Mockito.when(this.repository.save(Mockito.any())).thenReturn(note);

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT)
                .content(this.mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").hasJsonPath());

        Mockito.verify(this.repository).save(Mockito.any());
    }


    @ParameterizedTest
    @MethodSource("provideCreateNotesRequestsKo")
    void shouldCreateStatusBadRequest(Integer idUtilisateur, String nom, String contenu, String description
                                       ) throws Exception {
        // given
        CreateNotesRequest request = new CreateNotesRequest();
      
       

        request.setIdUtilisateur(idUtilisateur);
        request.setNom(nom);
        request.setContenu(contenu);
        request.setDescription(description);
        //request.setDateAjout( LocalDateTime.now());


        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(ENDPOINT)
                .content(this.mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

	private static Stream<Arguments> provideCreateNotesRequests() {
        return Stream.of(

            Arguments.of(200,"notes 1 de 1","contenu note une de un ", "description 1 de 1"),
            Arguments.of(200, "notes 2 de 1","contenu note deux de un ", "description 2 de 1"),
            Arguments.of( 200, "notes 3 de 1","contenu note deuroisx de un ", "description 3 de 1"),
			Arguments.of( 100,"notes 1 de 2","contenu note une de deux ", "description 1 de 2" )
        );
    }

    private static Stream<Arguments> provideCreateNotesRequestsKo() {
        return Stream.of(

            Arguments.of(null, "notes 1 de 1","contenu note une de un ", "description 1 de 1"),
            Arguments.of("",  "notes 2 de 1","contenu note une de un ", "description 1 de 1")

        );
    }






    


}
