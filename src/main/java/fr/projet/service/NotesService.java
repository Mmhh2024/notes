package fr.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.model.Notes;
import fr.projet.repo.NotesRepository;

@Service
public class NotesService {

    @Autowired
    private NotesRepository noteRepository;


    public List<Notes> getNotesByUtilisateurId(Integer utilisateurId) {

            return noteRepository.findByIdUtilisateur(utilisateurId );

     }
        public Notes getNoteById(Integer noteId) {
            return noteRepository.findById(noteId).orElse(null);
        }

        public Notes saveNote(Notes note) {


            if(note.getId()==null) {
                throw new RuntimeException("Besoin d'un id pour faire une mise à jour.");
        }
            return noteRepository.save(note);

        }
        public void deleteNoteById(Integer noteId) {
            noteRepository.deleteById(noteId);

    }

}
