package de.vayion.ijar;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import de.vayion.ijar.playerManagement.Reference;

public class ActiveJaR {
	
	private Main main;
	private Player player;
	private Reference reference;
	private Location activeLoc;
	private Location targetLoc;
	private int colorID;
	private int currentY;
	
	
	public ActiveJaR(Main main, Player player, Reference reference) {
		colorID = new Random().nextInt(13)+1;
		this.main = main;
		this.player = player;
		this.reference = reference;
		activeLoc = main.getAllowedSpace().getRandomStarterLocation();
		while(!activeLoc.getBlock().getType().equals(Material.AIR)) {
			activeLoc = main.getAllowedSpace().getRandomStarterLocation();
		}
		currentY = activeLoc.getBlockY();
		activeLoc.getBlock().setType(Material.STAINED_CLAY);
		activeLoc.getBlock().setData((byte) colorID);
		player.teleport(activeLoc.clone().add(0.5,1,0.5));
		getNextJump();
		running();
	}
	
	public void running() {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
		     public void run() {
		    	if(player.getLocation().getBlockY()<currentY) {
		    		playerFails();
		    		return;
		    	} else
		    	if(
		    			(player.getLocation().getBlockX()==targetLoc.getBlockX())&&
		    			(player.getLocation().getBlockY()==targetLoc.getBlockY()+1)&&
		    			(player.getLocation().getBlockZ()==targetLoc.getBlockZ())
		    			) {
		    		finished();
		    	}
		    	running();
		    	 
		    	 
		     }
		}, (4));
	}
	

	public void getNextJump() {
		//int i = 0;

		targetLoc = activeLoc.clone();
		targetLoc=getLocation();
		
		targetLoc.getBlock().setType(Material.STAINED_GLASS);
		targetLoc.getBlock().setData((byte) colorID);
	}
	
	public Location getLocation() {
		int a = 0;
		int y = 0;
		int b = 0;
		
		int i = new Random().nextInt(6);
		switch(i) {
		default:
			a = 4;
			b = 1;
			y = 0;
			break;
			
		case 0:
			a = 4;
			b = 0;
			y = 1;
			break;
			
		case 1:
			a = 3;
			b = 3;
			y = 0;
			break;
			
		case 2:
			a = 3;
			b = 2;
			y = 1;
			break;
			
		case 3:
			a = 4;
			b = 0;
			y = 0;
			break;
			
		case 4:
			a = 3;
			b = 2;
			y = 0;
			break;
			
		case 5:
			a = 3;
			b = 3;
			y = 1;
			break;
		}
		
		int e = new Random().nextInt(4);
		Location tempLoc = targetLoc.clone();
		
		switch(e) {
		case 0:
			tempLoc.add(a,y,b);
			break;
			
		case 1:
			tempLoc.add(-a,y,-b);
			break;
			
		case 2:
			tempLoc.add(b,y,a);
			break;
			
		case 3:
			tempLoc.add(-b,y,-a);
			break;
		}
		if(main.getAllowedSpace().isAllowed(tempLoc)&&tempLoc.getBlock().getType().equals(Material.AIR)) {
			return tempLoc;
		}
		return getLocation();
	}
	
	/**
	 * called when player fails the Jump and Run
	 */
	public void playerFails() {
		player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
		activeLoc.getBlock().setType(Material.AIR);
		targetLoc.getBlock().setType(Material.AIR);
		reference.clearJaR();
	}
	
	/**
	 * called when the plugin is shut down to prevent remaining Blocks to stay in the air
	 * this doesnt stop the repeated task of checking the players location. 
	 * As this is only getting called when the server is stopped this is not an issue right now
	 * In case this might happen in any other situation a "running" boolean has to be checked in the 
	 * repeated task. Due to performance this wasnt added.
	 */
	public void abort() {
		activeLoc.getBlock().setType(Material.AIR);
		targetLoc.getBlock().setType(Material.AIR);
		reference.clearJaR();
	}

	public void finished() {
		player.playSound(player.getLocation(), Sound.BLOCK_METAL_BREAK, 1, 1);
		activeLoc.getBlock().setType(Material.AIR);
		activeLoc = targetLoc.clone();
		activeLoc.getBlock().setType(Material.STAINED_CLAY);
		activeLoc.getBlock().setData((byte) colorID);
		targetLoc = null;
		getNextJump();
	}
	

}
