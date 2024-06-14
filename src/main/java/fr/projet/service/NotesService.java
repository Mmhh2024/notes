package fr.projet.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    //Suppression de notes
    public void deleteNoteById(Integer noteId) {
            
    	noteRepository.deleteById(noteId);

    }
    //Retourne un booleen , lorsque la note existe dans la table notes
    public boolean  existsNoteById(Integer noteId){
            
    	if(noteRepository.existsById(noteId) ){
           return true;
        }else{
           return false;
        }

     }

}
