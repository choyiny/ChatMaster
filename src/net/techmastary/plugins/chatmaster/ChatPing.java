package net.techmastary.plugins.chatmaster;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatPing implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("chat.pping")) {
			if (args.length == 1) {
				sender.sendMessage("Unknown Syntax. (/pping <player>)");
			}
			if (args.length >= 2) {
				Player pinged = Bukkit.getServer().getPlayer(args[0]);
				if (Bukkit.getServer().getPlayer(args[0]) != null) {
					if (!ChatEventListener.nochat.contains(pinged.getName())) {
						pinged.playSound(pinged.getLocation(), Sound.ORB_PICKUP, 10, 1);
						//TODO: Cost money to ping people.
					}
				}
			}
		}
		return true;
	}

}
