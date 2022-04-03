package de.vayion.ijar.playerManagement;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.vayion.ijar.ActiveJaR;
import de.vayion.ijar.AllowedSpace;
import de.vayion.ijar.Main;

public class Reference extends Element {
	
	private Player player;
	private Element next; 
	private ActiveJaR activeJaR;
	private AllowedSpace allowedSpace;
	private Main main;
	
	public Reference(Player player, Main main) {
		super();
		this.player = player;
		next = new End(main);
		activeJaR = null;
		this.main = main;
	}
	
	public Element removePlayer(Player player) {
		if(this.player.equals(player)) {
			return next;
		}
		next = next.removePlayer(player);
		return this;
	}
	
	public Element addPlayer(Player player) {
		if(this.player.equals(player)) {
			Bukkit.getLogger().log(Level.FINE, "Attempted to register an already registered User.");
			return this;
		}
		else {
			next = next.addPlayer(player);
			return this;
		}
	}
	
	public void deleteAllJaR() {
		next.deleteAllJaR();
		if(activeJaR!=null) {
			activeJaR.playerFails();
		}
	}
	
	public void startJaR(Player player) {
		if(this.player.equals(player)) {
			if(activeJaR == null) {
				activeJaR = new ActiveJaR(main, player, this);
			}
		} else {
			next.startJaR(player);
		}
	}
	
	public void clearJaR() {
		activeJaR = null;
	}
	
}
