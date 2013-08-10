package net.techmastary.plugins.chatmaster;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
			} else {
				sender.sendMessage(ChatColor.WHITE + "Unknown command. Type \"help\" for help.");
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
			if (args.length == 1) {
				if (sender.hasPermission("chat.clean")) {
					for (int x = 0; x < 120; x++) {
						sender.sendMessage("");
						if (x == 119) {
							sender.sendMessage(ChatColor.GRAY + "You cleared your chat.");
						}
					}
				} else {
					sender.sendMessage(ChatColor.WHITE + "Unknown command. Type \"help\" for help.");
				}
			} else if (args.length == 2) {
				if (args[1].equalsIgnoreCase("all")) {
					if (sender.hasPermission("chat.clean.all")) {
						for (int x = 0; x < 120; x++) {
							Bukkit.broadcastMessage("");
						}
						Bukkit.broadcastMessage(ChatColor.GRAY + "Your chat has been cleared by: " + sender.getName());
						sender.sendMessage(ChatColor.GRAY + "You cleared everybody's chat.");
					} else {
						sender.sendMessage(ChatColor.WHITE + "Unknown command. Type \"help\" for help.");
					}
				} else {
					if (sender.hasPermission("chat.clean.others")) {
						Player p = Bukkit.getServer().getPlayer(args[1]);
						if (Bukkit.getServer().getPlayer(args[1]) != null) {
							for (int x = 0; x < 120; x++) {
								p.sendMessage("");
							}
							sender.sendMessage(ChatColor.GRAY + "You cleared chat for: " + p.getName());
							p.sendMessage(ChatColor.GRAY + "Your chat has been cleared by: " + sender.getName());
						} else {
							sender.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GRAY + "Player not found.");
						}
					} else {
						sender.sendMessage(ChatColor.WHITE + "Unknown command. Type \"help\" for help.");
					}
				}
			}
		}
		return true;
	}
}