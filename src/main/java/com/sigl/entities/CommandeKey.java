package com.sigl.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class CommandeKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id_commande")
	private Long idcommande;

	@Column(name = "id_menu")
	private Long idmenu;


	public Long getIdcommande() {
		return idcommande;
	}

	public void setIdcommande(Long idcommande) {
		this.idcommande = idcommande;
	}

	public Long getIdmenu() {
		return idmenu;
	}

	public void setIdmenu(Long idmenu) {
		this.idmenu = idmenu;
	}

	public CommandeKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcommande == null) ? 0 : idcommande.hashCode());
		result = prime * result + ((idmenu == null) ? 0 : idmenu.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandeKey other = (CommandeKey) obj;
		if (idcommande == null) {
			if (other.idcommande != null)
				return false;
		} else if (!idcommande.equals(other.idcommande))
			return false;
		if (idmenu == null) {
			if (other.idmenu != null)
				return false;
		} else if (!idmenu.equals(other.idmenu))
			return false;
		return true;
	}


	
	
	
}
