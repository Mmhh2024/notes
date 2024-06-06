package fr.projet.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public Notes createNote(@RequestBody Notes note) {

        return noteService.saveNote(note);
    }
}