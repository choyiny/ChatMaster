package net.techmastary.plugins.chatmaster;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCommand implements CommandExecutor {
	double version = ChatMaster.version;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 0) {
			sender.sendMessage(ChatColor.YELLOW + "Use \"/cm help\" for help");
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(ChatColor.YELLOW + "==== ChatMaster " + version + " ====");
				sender.sendMessage(ChatColor.GOLD + "Your available commands:");
				if (sender.hasPermission("chat.silence")) {
					sender.sendMessage(ChatColor.GOLD + "/cm silence - Silences global chat.");
				}
				if (sender.hasPermission("chat.status")) {
					sender.sendMessage(ChatColor.GOLD + "/cm chatstatus - Check for current global chat status");
				}
				if (sender.hasPermission("chat.clean")) {
					sender.sendMessage(ChatColor.GOLD + "/cm cleanchat - Cleans your chat.");
				}
				return true;
			}
		}
		if (args[0].equalsIgnoreCase("silence")) {
			if (sender.hasPermission("chat.silence")) {
				if (ChatMaster.Muted == false) {
					ChatMaster.Muted = true;
					sender.sendMessage(ChatColor.GRAY + "You silenced global chat.");
					Bukkit.broadcastMessage(ChatColor.GRAY + "" + sender.getName() + " disabled global chat.");
					return true;
				} else {
					ChatMaster.Muted = false;
					sender.sendMessage(ChatColor.GRAY + "You have resumed global chat.");
					Bukkit.broadcastMessage(ChatColor.GRAY + "" + sender.getName() + " resumed global chat.");
					return true;
				}
			}
		}
		if (args[0].equalsIgnoreCase("chatstatus")) {
			if (sender.hasPermission("chat.status") && (ChatMaster.Muted == true)) {
				sender.sendMessage(ChatColor.GRAY + "Global chat is currently" + ChatColor.RED + " DISABLED" + ChatColor.GRAY + ".");
			}
			if (sender.hasPermission("chat.status") && (ChatMaster.Muted == false)) {
				sender.sendMessage(ChatColor.GRAY + "Global chat is currently" + ChatColor.GREEN + " ENABLED" + ChatColor.GRAY + ".");

			}
		}
		if (args[0].equalsIgnoreCase("cleanchat")) {
			if (sender.hasPermission("chat.clean")) {
				for (int x = 0; x < 120; x++) {
					sender.sendMessage("");
					if (x == 119) {
						sender.sendMessage(ChatColor.GRAY + "You cleared your chat.");
					}
				}
			}

		} else {
			sender.sendMessage(ChatColor.YELLOW + "Use \"/cm help\" for help ");
		}

		return true;
	}

}