package main.java.net.techmastary.plugins.chatmaster;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatCommand implements CommandExecutor {
	ChatMaster plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Messages.help);
			return true;
		}
		if (args.length >= 1) {

			// help command
			if (args[0].equalsIgnoreCase("help")) {
			}

			// reload command
			if (args[0].equalsIgnoreCase("reload")) {
				if (sender.hasPermission("cm.relaod")) {
					plugin.reloadConfig();
				}
			}

			// global chat
			if (args[0].equalsIgnoreCase("silence")) {
				if (sender.hasPermission("cm.silence")) {
					if (!ChatEventListener.ChatSilenced) {
						ChatEventListener.ChatSilenced = true;
						Bukkit.broadcastMessage(Messages.silenced_global);
						// TODO: Title
					} else {
						ChatEventListener.ChatSilenced = false;
						Bukkit.broadcastMessage(Messages.resumed_global);
						// TODO: Title
					}
				}
			}

			// clear chat command
			if (args[0].equalsIgnoreCase("clearchat")) {
				if (sender.hasPermission("cm.clearchat")) {
					for (int x = 0; x < 120; x++) {
						Bukkit.broadcastMessage("");
					}
				}
			}
		}

		return false;

	}

}
