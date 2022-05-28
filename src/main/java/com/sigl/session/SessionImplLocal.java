package com.sigl.session;

import java.util.List;

import javax.ejb.Local;

import com.sigl.entities.*;

@Local
public interface SessionImplLocal {

	//Menu :
	void addMenu(Menu m);
	Menu getMenu(Long id);
	List<Menu> getAllMenus();
	void deleteMenu(Long id);
	List<Menu> searchMenusByTitle(String critere);
	List<Menu> searchMenusByCategorie(long id_categorie);
	Menu updateMenu(Long id,Menu m);

	//Categorie :
	void addCategorie(Categorie c);
	void deleteCategorie(long id_categorie);
	Categorie getCategorie(Long id);
	List<Categorie> getAllCategories();


	List<Commande> getCommandesByIdClient(long id);

	List<Commande> getAllCommandes();

	void addCommande(Commande c);

	Commande getLignesCommande(long id);

	Client getClientById(long id);
}
