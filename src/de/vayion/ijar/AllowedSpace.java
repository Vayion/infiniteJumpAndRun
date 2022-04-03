package de.vayion.ijar;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;

import de.vayion.ijar.commands.IsInsideCmd;
import de.vayion.ijar.commands.PositionCmd;

public class AllowedSpace {

	/**
	 * Border 1 has the lowest, Border 2 the highest coordinates of the allowed space
	 */
	private Location border1, border2;
	
	/**
	 * Positions given by the player. They are kept in case the player wants to edit a specific corner
	 */
	private Location  position1, position2;
	
	private int minY;
	private World activeWorld;
	
	private PositionCmd positionCmd;
	private IsInsideCmd isInsideCmd;

	private boolean ready;
	
	private Main main;
	
	int deltaX, deltaY, deltaZ;
	
	public AllowedSpace(Main main) {
		this.main = main;
		border1 = null;
		border2 = null;
		position1 = null;
		position2 = null;
		minY = 0;
		
		
		positionCmd = new PositionCmd(this);
		isInsideCmd = new IsInsideCmd(this);
		
		main.getCommand("position").setExecutor(positionCmd);
		main.getCommand("isInside").setExecutor(isInsideCmd);
		
	}
	
	
	/**
	 * Used to set the positions given by the player.
	 * @param one True if the location is position1, false if it is position2
	 * @param loc the location that is to be saved
	 */
	public boolean addPosition(boolean one, Location loc) {
		if (activeWorld == null) {
			activeWorld = loc.getWorld();
			position1 = loc.clone();
			position2 = loc.clone();
		}
		else if(!activeWorld.equals(loc.getWorld())) {
			return false;
		}
		
		
		if(one) {position1 = loc.clone();}
		else {position2 = loc.clone();}
		
		border1 = new Location(activeWorld, 0, 0, 0);
		border2 = new Location(activeWorld, 0, 0, 0);
		
		
		if(position1.getBlockX()>position2.getBlockX()) {border1.setX(position2.getBlockX()); border2.setX(position1.getBlockX());}
		else {border1.setX(position1.getBlockX()); border2.setX(position2.getBlockX());}

		if(position1.getBlockY()>position2.getBlockY()) {border1.setY(position2.getBlockY()); border2.setY(position1.getBlockY());}
		else {border1.setY(position1.getBlockY()); border2.setY(position2.getBlockY());}

		if(position1.getBlockZ()>position2.getBlockZ()) {border1.setZ(position2.getBlockZ()); border2.setZ(position1.getBlockZ());}
		else {border1.setZ(position1.getBlockZ()); border2.setZ(position2.getBlockZ());}
		
		
		
		if(border1.equals(border2)) {
			ready = false;
			deltaX=0;
			deltaY=0;
			deltaZ=0;
			minY = 0;
		}else {
			ready = true;
			deltaX = 1 + (border2.getBlockX()-border1.getBlockX());
			deltaY = 1 + (border2.getBlockY()-border1.getBlockY());
			deltaZ = 1 + (border2.getBlockZ()-border1.getBlockZ());
			minY = border1.getBlockY();
		}
		
		
		return true;
	}
	
	public boolean isAllowed(Location loc) {
		if(
				((loc.getBlockX()>=border1.getBlockX())&&(loc.getBlockX()<=border2.getBlockX()))&&
				((loc.getBlockY()>=border1.getBlockY())&&(loc.getBlockY()<=border2.getBlockY()))&&
				((loc.getBlockZ()>=border1.getBlockZ())&&(loc.getBlockZ()<=border2.getBlockZ()))
				) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int getMinY() {
		return minY;
	}
	
	public boolean getReady(){
		return ready;
	}
	
	
	
	/**
	 * resets activeWorld, borders and positions
	 */
	public void reset() {
		activeWorld = null;
		border1 = null;
		border2 = null;
		position1 = null;
		position2 = null;
	}
	
	/**
	 * gives a random x-coordinate inside of the allowed space
	 * @return x-coordinate
	 */
	public int getRandomX(){return border1.getBlockX() + new Random().nextInt(deltaX);}
	

	/**
	 * gives a random y-coordinate inside of the allowed space
	 * @return y-coordinate
	 */
	public int getRandomY(){return border1.getBlockY() + new Random().nextInt(deltaY);}
	

	/**
	 * gives a random z-coordinate inside of the allowed space
	 * @return z-coordinate
	 */
	public int getRandomZ(){return border1.getBlockZ() + new Random().nextInt(deltaZ);}
	
	/**
	 * gives a random Jump and Run starting location
	 * @return Location
	 */
	public Location getRandomStarterLocation() {
		return new Location(activeWorld, getRandomX(), minY, getRandomZ());
	}
	
	public Main getMain() {
		return main;
	}
	
	public void save() {
		main.getFileManager().saveLocations(position1, position2);
	}
	
}
