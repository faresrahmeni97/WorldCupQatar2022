package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.example.demo.entities.Joueur;

@Entity
public class Equipe implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String Paysequipe;
    private String Systemequipenationnal;
  /*  private List<Joueur> remplacant;*/
    private Long nbcoupe;
    @Column(nullable = true, length = 64)
    private String imageequipe;

    @OneToMany(mappedBy="equipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Joueur> joueurs=new ArrayList<>();


    @JsonIgnore

    //@JsonManagedReference//@JsonBackReference

    public Equipe() {
        super();
    }
    public Long getId() {
        return id;
    }


//Joueur remplacant,
    public Equipe(Long id, String Paysequipe, String Systemequipenationnal, Long nbcoupe,String imageequipe) {
        super();
        this.id = id;
        this.Paysequipe = Paysequipe;
        this.Systemequipenationnal = Systemequipenationnal;
       // this.remplacant = remplacant;
        this.nbcoupe=nbcoupe;
        this.imageequipe=imageequipe;
    }
    public void setId(Long id) {
        this.id = id;
    }
/*
    public List<Joueur> getRemplacant() {
        return remplacant;
    }
-*/
    public String getPaysequipe() {
        return Paysequipe;
    }
    public String getSystemequipenationnal() {
        return Systemequipenationnal;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getNbcoupe() {
        return nbcoupe;
    }


    public void setPaysequipe(String paysequipe) {
        Paysequipe = paysequipe;
    }

    public void setSystemequipenationnal(String systemequipenationnal) {
        Systemequipenationnal = systemequipenationnal;
    }
/*
    public void setRemplacant(List<Joueur> remplacant) {
        this.remplacant = remplacant;
    }
*/
    public void setNbcoupe(Long nbcoupe) {
        this.nbcoupe = nbcoupe;
    }

    public void setImageequipe(String imageequipe) {
        this.imageequipe = imageequipe;
    }

    public void setPhotos(String imageequipe) {this.imageequipe = imageequipe;}
    public String getImageequipe() {
        if (imageequipe == null || id == null) return null;

        return "/equipes-photos/" + id + "/" + imageequipe;
    }
    @Override
    public String toString() {
        return "Equipe [id=" + id + ", Paysequipe=" + Paysequipe + ", Systemequipenationnal=" + Systemequipenationnal  +", nbcoupe=" + nbcoupe + ", imageequipe=" + imageequipe + "]";
    }


}
