package net.techmastary.plugins.chatmaster;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCommand implements CommandExecutor {
	ChatMaster plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Messages.unknown);
			return true;
		}
		if (args.length >= 1) {

			// help command
			if (args[0].equalsIgnoreCase("help")) {
				Messages.help(sender);
			}

			// reload command
			if (args[0].equalsIgnoreCase("reload")) {
				if (sender.hasPermission("cm.reload")) {
					plugin.reloadConfig();
				} else {
					sender.sendMessage(Messages.no_permission);
				}
			}

			// global chat
			if (args[0].equalsIgnoreCase("silence")) {
				if (sender.hasPermission("cm.silence")) {
					if (!ChatEventListener.ChatSilenced) {
						ChatEventListener.ChatSilenced = true;
						Bukkit.broadcastMessage(Messages.silenced_global(sender.getName()));
						// TODO: Title
						// TODO: Server config - Clear Chat?
					} else {
						ChatEventListener.ChatSilenced = false;
						Bukkit.broadcastMessage(Messages.resumed_global(sender.getName()));
						// TODO: Title
					}
				} else {
					sender.sendMessage(Messages.no_permission);
				}
			}

			if (args[0].equalsIgnoreCase("status")) {
				if (sender.hasPermission("cm.status")) {
					if (!ChatEventListener.ChatSilenced) {
						sender.sendMessage(Messages.global_on);
					} else {
						sender.sendMessage(Messages.global_off);
					}
				} else {
					sender.sendMessage(Messages.no_permission);
				}
			}

			// clear chat command
			if (args[0].equalsIgnoreCase("clearchat")) {
				if (sender.hasPermission("cm.clearchat")) {
					for (int x = 0; x < 120; x++) {
						Bukkit.broadcastMessage("");
					}
				} else {
					sender.sendMessage(Messages.no_permission);
				}
			}

		}

		return false;

	}

}
