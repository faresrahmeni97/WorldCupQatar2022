package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
public class Joueur implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String clubjoueur;
    private Long numposte;
    private String poste;
    private boolean titulaire;
    @Column(nullable = true, length = 64)
    private String photoJoueur;

    @ManyToOne
    @JoinColumn(name = "equipeId", nullable = false)
    private Equipe equipe;

    //@ManyToMany(mappedBy="joueurs",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore

    //@JsonManagedReference//@JsonBackReference
    public void setIdequip(Equipe equipeId) {
        this.equipe = equipeId;
    }

    public Equipe getIdequip() {
        return equipe;
    }



    public Joueur() {
        super();
    }

    public Equipe getEquipe() {
        return equipe;
    }
    public Long getId() {
        return id;
    }




    public Joueur(Long id, String clubjoueur, Long numposte, String poste,String photos) {
        super();
        this.id = id;
        this.clubjoueur = clubjoueur;
        this.numposte = numposte;
        this.poste = poste;
        this.photoJoueur=photos;

    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getclubjoueur() {
        return clubjoueur;
    }
    public void setClubjoueur(String clubjoueur) {
        this.clubjoueur = clubjoueur;
    }

    public Long getnumposte() {
        return numposte;
    }
    public void setNumposte(Long numposte) {
        this.numposte = numposte;
    }

    public String getposte() { return poste; }
    public void setPoste(String poste) {
        this.poste = poste;
    }

    public boolean isTitulaire() {
        return titulaire;
    }

    public void setTitulaire(boolean titulaire) {
        this.titulaire = titulaire;
    }

    public void setPhotos(String photos) {this.photoJoueur = photos;}
    public String getPhotos() {
        if (photoJoueur == null || id == null) return null;

        return "/joueur-photos/" + id + "/" + photoJoueur;
    }
    @Override
    public String toString() {
        return "Joueur [id=" + id + ", clubjoueur=" + clubjoueur + ", numposte=" + numposte + ", poste=" + poste +", photos=" + photoJoueur + "]";
    }


}
