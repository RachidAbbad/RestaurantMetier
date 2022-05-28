package com.sigl.session;

import java.util.List;

import javax.ejb.Remote;

import com.sigl.entities.Categorie;
import com.sigl.entities.Menu;

@Remote
public interface SessionImplRemote {
	public void addMenu(Menu m);
	public void addCategorie(Categorie c);
	public Categorie getCategorie(Long id);
	public Menu getMenu(Long id);
	public void deleteMenu(Long id);
	public List<Menu> searchMenusByTitle(String critere);
	public Menu updateMenu(Long id,Menu m);
	
}
