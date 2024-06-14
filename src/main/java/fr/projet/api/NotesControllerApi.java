package fr.projet.api;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.projet.model.Notes;
import fr.projet.repo.NotesRepository;
import fr.projet.request.EntityCreatedResponse;
import fr.projet.response.CreateNotesRequest;
import fr.projet.response.ModifyNotesRequest;
import fr.projet.service.NotesService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin("*")
public class NotesControllerApi {
    
    @Autowired 
    private NotesService noteService;

    @Autowired
    private NotesRepository repo;

    Logger logger = LoggerFactory.getLogger(NotesControllerApi.class);
    

    @GetMapping
    public List<Notes> getAllNotes( ) {
       return this.repo.findAll();
    }

    @GetMapping("/{idUtilsateur}")
    public List<Notes> getNoteById(@PathVariable int idUtilsateur ) {
       List<Notes> currentNote = noteService.getNotesByUtilisateurId(idUtilsateur);

        if (currentNote != null ) {

            return currentNote;
        }
        return null; //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucune Note pour cet utilisateur");
    }

   
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedResponse create(@Valid @RequestBody CreateNotesRequest request) {
        
        Notes tmpNote = this.noteService.createNote(request);
       
        logger.debug("Note {} created!", tmpNote.getId());

        return new EntityCreatedResponse(tmpNote.getId(), tmpNote.getIdUtilisateur());
    }

    // maj de note
    @PutMapping  // ("/{id}")
	//public String update(@RequestBody Notes note, @PathVariable("id") Integer id) {
    public Notes update(@Valid @RequestBody ModifyNotesRequest request   , @RequestParam("id") Integer id) {
        
        Notes note = new Notes();
        Notes tmpnote = new Notes();

        tmpnote = this.noteService.getNoteById(id);
        
        BeanUtils.copyProperties(request, note);
        

		if (id != note.getId() || !this.noteService.existsNoteById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Note inexistant");
		}
        note.setDateAjout(tmpnote.getDateAjout());
       //String result;
       note.setDateModification(LocalDateTime.now());
       tmpnote =  this.noteService.saveNote(note);
       logger.debug("Note {} modified!", tmpnote.getId());
       return tmpnote;
	}
    // delete de note
    @DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		if (!this.noteService.existsNoteById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note inexistante");
		}

		this.noteService.deleteNoteById(id);
		 logger.debug("Note deleted!") ;
	}



}