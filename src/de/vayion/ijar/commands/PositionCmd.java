package de.vayion.ijar.commands;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.vayion.ijar.AllowedSpace;

public class PositionCmd implements CommandExecutor {
	
	
	AllowedSpace allowedSpace;
	
	
	public PositionCmd(AllowedSpace allowedSpace) {
		this.allowedSpace = allowedSpace;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("ijar.admin")) {
				if(args.length != 0) {
					switch (args[0]) {
					case "1":
						if(allowedSpace.addPosition(true, player.getLocation().clone())) {
							player.sendMessage(ChatColor.GREEN+"Position 1 set at "+player.getLocation().getBlockX()+", "+player.getLocation().getBlockY()+", "+player.getLocation().getBlockZ()+".");
						}
						else {
							player.sendMessage(ChatColor.RED+"The given coordinate is not in the right world. If this seems to be an error please do '/reset'.");
						}
						break;
					case "2":
						if(allowedSpace.addPosition(false, player.getLocation().clone())) {
							player.sendMessage(ChatColor.GREEN+"Position 2 set at "+player.getLocation().getBlockX()+", "+player.getLocation().getBlockY()+", "+player.getLocation().getBlockZ()+".");
						}
						else {
							player.sendMessage(ChatColor.RED+"The given coordinate is not in the right world. If this seems to be an error please do '/reset'.");
						}
						break;
					default:
						player.sendMessage(ChatColor.RED+"Not a valid argument.");
					}
				}
				else {
					player.sendMessage(ChatColor.RED+"Missing args.");
				}
			}
			else {
				player.sendMessage(ChatColor.RED+"That would be nice");
			}
		}
		else {
			System.out.println("You can't use this command in the console");
		}
		
		return false;
	}
	
}
