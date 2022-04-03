package de.vayion.ijar;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {
	Main main;
	
	private File file;
	private FileConfiguration config;
	
	public FileManager(Main _main) {
		main = _main;
		
		file = new File(main.getDataFolder(), "locations.yml");
		config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
		if(file.exists()) {
			main.setAllowedSpace( (Location)config.get("pos1"), (Location)config.get("pos2"));
			System.out.println((Location)config.get("pos1"));
			System.out.println((Location)config.get("pos2"));
		}
		else {
			try {
				file.createNewFile();
				main.setAllowedSpace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void saveLocations(Location loc1, Location loc2) {
		config.set("pos1", loc1);
		config.set("pos2", loc2);
	}
	
	public void save() {
		try {
			config.save(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
