package de.vayion.ijar.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.vayion.ijar.playerManagement.ReferenceManager;

public class JumpCmd implements CommandExecutor {
	ReferenceManager referenceManager;
	
	public JumpCmd(ReferenceManager referenceManager) {
		this.referenceManager = referenceManager;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			referenceManager.startJaR(player);
			
		}
		return false;
	}
	
	

}
