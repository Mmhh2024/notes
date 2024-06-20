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

    // retourne la liste de note d'un utilisateur
    public List<Notes> getNotesByUtilisateurId(Integer utilisateurId) {

        return noteRepository.findByIdUtilisateur(utilisateurId );

    }
    //Retourne la note de id = noteId
    public Notes getNoteById(Integer noteId) {
        return noteRepository.findById(noteId).orElse(null);
    }
    //Vérifie l'existance de la note id 
    public boolean notesExistsById(Integer noteId) {
    	return noteRepository.existsById(noteId);
    }
   
    //  Creation de note
    public Notes createNote(CreateNotesRequest noteRequest) {
        Notes note = new Notes();
       	BeanUtils.copyProperties(noteRequest, note);
       	note.setDateAjout(LocalDateTime.now());
        return noteRepository.save(note);
    }
  //Mise à jour d'une note
    public Notes saveNote(Notes note) {

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
    //Delete the noteId note
    public void deleteNoteById(Integer noteId) {
            
    	noteRepository.deleteById(noteId);

    }
    //Return true if the noteId exist 
    public boolean  existsNoteById(Integer noteId){
            
    	if(noteRepository.existsById(noteId) ){
           return true;
        }else{
           return false;
        }

     }
    
    //Delete notes by UserId
    @Transactional
    public void deleteNoteByIdUtilisateur(Integer userId) {
        
    	
    	noteRepository.deleteByIdUtilisateur(userId);

    }

}
