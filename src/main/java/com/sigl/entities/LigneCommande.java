package com.sigl.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class LigneCommande implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private CommandeKey idkey;
	@ManyToOne
	@MapsId("id_menu")
	@JoinColumn(name = "id_menu")
	private Menu menu;
	@ManyToOne
	@MapsId("id_commande")
	@JoinColumn(name = "id_commande")
	@JsonBackReference
	private Commande commande;
	
	private int quantite;
	public LigneCommande() {
		super();
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public CommandeKey getIdkey() {
		return idkey;
	}

	public void setIdkey(CommandeKey idkey) {
		this.idkey = idkey;
	}
}
