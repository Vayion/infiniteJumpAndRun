package de.vayion.ijar;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import de.vayion.ijar.playerManagement.ReferenceManager;

public class Main extends JavaPlugin {
	
	
	private ReferenceManager referenceManager;
	private AllowedSpace allowedSpace;
	private FileManager fileManager;
	
	@Override
	public void onEnable() {
		if(!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		this.getServer().getPluginManager().registerEvents(new Listeners(this), this);

		fileManager = new FileManager(this);
		referenceManager = new ReferenceManager(this);
	}

	@Override
	public void onDisable() {
		referenceManager.killAllJaR();
		allowedSpace.save();
		fileManager.save();
	}
	
	public void setAllowedSpace() {
		allowedSpace = new AllowedSpace(this);
	}
	
	public void setAllowedSpace(Location loc1, Location loc2) {
		allowedSpace = new AllowedSpace(this);;
		System.out.println("Loading AllowedSpace");
 		allowedSpace.addPosition(true, loc1);
 		allowedSpace.addPosition(false, loc2);
		
	}
	
	public AllowedSpace getAllowedSpace() {
		return allowedSpace;
	}
	
	public ReferenceManager getReferenceManager() {
		return referenceManager;
	}
	public FileManager getFileManager() {
		return fileManager;
	}

}
