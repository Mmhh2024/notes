package fr.projet.model;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notes")
public class Notes {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@Column(name = "idutilisateur", nullable = false)
private Integer idUtilisateur;
@Column(name = "nom",nullable = false)
private String nom;
@Column(nullable = false)
private String contenu;
@Column(nullable = false)
private String description;
@Column(name = "dateajout")
private LocalDateTime dateAjout;
@Column(name = "datemodification")
private LocalDateTime dateModification;
public Integer getId() {
    return id;
}
public void setId(Integer id) {
    this.id = id;
}
public Integer getIdUtilisateur() {
    return idUtilisateur;
}
public void setIdUtilisateur(Integer idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
}
public String getNom() {
    return nom;
}
public void setNom(String nom) {
    this.nom = nom;
}
public String getContenu() {
    return contenu;
}
public void setContenu(String contenu) {
    this.contenu = contenu;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public LocalDateTime getDateAjout() {
    return dateAjout;
}
public void setDateAjout(LocalDateTime dateAjout) {
    this.dateAjout = dateAjout;
}
public LocalDateTime getDateModification() {
    return dateModification;
}
public void setDateModification(LocalDateTime dateModification) {
    this.dateModification = dateModification;
}


}
