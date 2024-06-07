package fr.projet.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.projet.model.Notes;
import fr.projet.service.NotesService;

@RestController
@RequestMapping("/api/notes")
public class NotesControllerApi {
    
    @Autowired
    private NotesService noteService;



    //@GetMapping
   // public List<Note> getAllNotes( authentication) {
       // User currentUser = userService.findUserByUsername(authentication.getName());
       // return noteService.getNotesByUserId(currentUser.getId());
   // }

    @GetMapping("/{id}")
    public List<Notes> getNoteById(@PathVariable Integer id ) {
       List<Notes> currentNote = noteService.getNotesByUtilisateurId(id);

        if (currentNote != null ) {

            return currentNote;
        }
        return null; // Or throw an exception
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Notes createNote(@RequestBody Notes note) {
        note.setDateAjout(LocalDateTime.now());
        note.setDateModification(note.getDateAjout());
        return noteService.saveNote(note);
    }

    @PutMapping("/{id}")
	public Notes update(@RequestBody Notes note, @PathVariable("id") Integer id) {
		if (id != note.getId() || !this.noteService.existsNoteById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Note inexistant");
		}
       
        note.setDateModification(LocalDateTime.now());
		return this.noteService.saveNote(note);
	}
    // delete
    @DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		if (!this.noteService.existsNoteById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note inexistante");
		}

		this.noteService.deleteNoteById(id);
	}



}