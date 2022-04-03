package de.vayion.ijar.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.vayion.ijar.AllowedSpace;

public class IsInsideCmd implements CommandExecutor {
	
	AllowedSpace allowedSpace;
	
	public IsInsideCmd(AllowedSpace allowedSpace) {
		this.allowedSpace = allowedSpace;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("ijar.admin")) {
				if(allowedSpace.isAllowed(player.getLocation())) {player.sendMessage(ChatColor.GREEN+"You are inside of the allowed Space");}
				else {player.sendMessage(ChatColor.RED+"You are outside of the allowed Space");}
			}
		}
		return false;
	}
	
	

}
