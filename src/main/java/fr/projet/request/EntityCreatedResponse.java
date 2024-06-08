package fr.projet.request;

public class EntityCreatedResponse {

    private Integer id;
    private Integer idUtisateur;

    

    public EntityCreatedResponse(Integer id, Integer idUtisateur) {
        this.id = id;
        this.idUtisateur = idUtisateur;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUtisateur() {
        return idUtisateur;
    }

    public void setIdUtisateur(Integer idUtisateur) {
        this.idUtisateur = idUtisateur;
    }

    public EntityCreatedResponse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;

    }

    
}
