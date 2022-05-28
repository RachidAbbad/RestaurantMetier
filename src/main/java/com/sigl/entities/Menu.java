package com.sigl.entities;

import java.util.List;
import javax.persistence.*;

import java.io.Serializable;
@Entity
public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id; 
	

	@ManyToOne(fetch = FetchType.EAGER)
	private Categorie categorie;
	
	private String titre;
	private double prix;
	private String image;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie c) {
		this.categorie = c;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Menu(String titre,Categorie c,  double prix, String image, String description) {
		super();
		this.titre = titre;
		this.prix = prix;
		this.image = image;
		this.description = description;
		this.categorie=c;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
}
