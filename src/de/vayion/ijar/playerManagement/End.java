package de.vayion.ijar.playerManagement;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.vayion.ijar.Main;

public class End extends Element{
	
	Main main;
	
	public End(Main main) {
		this.main = main;
	}
	
	public Element addPlayer(Player player) {
		return new Reference(player, main);
	}
	public Element removePlayer(Player player) {
		Bukkit.getLogger().log(Level.FINE, "Attempted to remove a player not registered in the system");
		return this;
	}
	public void startJaR(Player player) {
		//TODO: Log
	}

	public void deleteAllJaR() {}
}
