package fr.projet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.projet.model.Notes;


public interface NotesRepository extends JpaRepository <Notes, Integer> {
 
    public List<Notes> findByIdUtilisateur(Integer idUtilisateur);
    
    public boolean  existsById (Integer id);
    
    public void deleteByIdUtilisateur(Integer idUtilisateur);
} 
