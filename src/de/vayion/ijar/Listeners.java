package de.vayion.ijar;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {
	
	Main main;
	
	public Listeners(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		main.getReferenceManager().addPlayer(event.getPlayer());
	}

	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		main.getReferenceManager().removePlayer(event.getPlayer());
	}
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		main.getReferenceManager().removePlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerMoveEvent event) {
		if(event.getPlayer().getLocation().getBlock().getType().equals(Material.PORTAL)) {
			main.getReferenceManager().startJaR(event.getPlayer());
		}
	}

	@EventHandler
	public void onEnterPortal(PlayerPortalEvent event) {
		event.setCancelled(true);
		main.getReferenceManager().startJaR(event.getPlayer());
	}
	

}
