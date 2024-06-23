package fr.projet.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.projet.model.Notes;
import fr.projet.repo.NotesRepository;
import fr.projet.response.CreateNotesRequest;

@Service
public class NotesService {

    @Autowired
    private NotesRepository noteRepository;

    
    public List<Notes> getNotesByUtilisateurId(Integer utilisateurId) {
    	// Return the user's notes
        return noteRepository.findByIdUtilisateur(utilisateurId );

    }
   
    public Notes getNoteById(Integer noteId) {
    	// Return the note with id = noteId
        return noteRepository.findById(noteId).orElse(null);
    }
    
    public boolean notesExistsById(Integer noteId) {
    	//Verify the existance of noteId
    	return noteRepository.existsById(noteId);
    }
   
    
    public Notes createNote(CreateNotesRequest noteRequest) {
    	//Creation note
        Notes note = new Notes();
       	BeanUtils.copyProperties(noteRequest, note);
       	note.setDateAjout(LocalDateTime.now());
        return noteRepository.save(note);
    }
  
    public Notes saveNote(Notes note) {
    	// update Note
        if(note.getId()==null) {
                throw new RuntimeException("Besoin d'un id pour faire une mise à jour.");
        }
         Optional<Notes> noteopt  = noteRepository.findById(note.getId());
        if(noteopt.isEmpty()) 
		{
			return null;
		}else {
        note.setDateAjout(noteopt.get().getDateAjout());
		}
        note.setDateModification(LocalDateTime.now());
        
        return noteRepository.save(note);

    }
    
    public void deleteNoteById(Integer noteId) {
    	//Delete the noteId note    
    	noteRepository.deleteById(noteId);

    }
    
    public boolean  existsNoteById(Integer noteId){
    	//Return true if the noteId exist    
    	if(noteRepository.existsById(noteId) ){
           return true;
        }else{
           return false;
        }

     }
    
    
    @Transactional
    public void deleteNoteByIdUtilisateur(Integer userId) {
        
    	//Delete notes by UserId
    	noteRepository.deleteByIdUtilisateur(userId);

    }

}
