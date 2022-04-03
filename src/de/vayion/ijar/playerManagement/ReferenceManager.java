package de.vayion.ijar.playerManagement;

import org.bukkit.entity.Player;

import de.vayion.ijar.Main;
import de.vayion.ijar.commands.JumpCmd;

public class ReferenceManager {
	
	private Element first;
	private Main main;	
	
	
	public ReferenceManager(Main main) {
		this.main = main;
		first = new End(main);
		main.getCommand("jump").setExecutor(new JumpCmd(this));
		
	}
	
	public void addPlayer(Player player) {
		first = first.addPlayer(player);
	}
	
	public void removePlayer(Player player) {
		first = first.removePlayer(player);
	}
	
	public void startJaR(Player player) {
		first.startJaR(player);
	}
	
	
	/**
	 * kills all orphans I mean Jump and Runs
	 */
	public void killAllJaR() {
		first.deleteAllJaR();
	}
	
	
	
}
