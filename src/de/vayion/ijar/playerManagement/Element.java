package de.vayion.ijar.playerManagement;

import org.bukkit.entity.Player;

public abstract class Element {
	
	public Element() {}
	public abstract Element addPlayer(Player player);
	public abstract Element removePlayer(Player player);
	
	public abstract void startJaR(Player player);
	public abstract void deleteAllJaR();
	
	
}
