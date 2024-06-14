package fr.projet.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModifyNotesRequest {
    @NotNull
    private Integer id;
    @NotNull
    private Integer idUtilisateur;
    @NotBlank
    private String nom;
    @NotBlank
    private String contenu;

    private String description;

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

}
