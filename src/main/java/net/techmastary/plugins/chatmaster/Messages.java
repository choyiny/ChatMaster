package net.techmastary.plugins.chatmaster;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Messages {

	static String prefix = ChatColor.GOLD + "[" + ChatColor.RED + "ChatMaster" + ChatColor.GOLD + "] ";
	static String unknown = prefix + ChatColor.YELLOW + "Use \"/cm help\" for help.";
	static String no_permission = prefix + ChatColor.WHITE + "Unknown command. Type \"help\" for help.";

	static String global_on = prefix + ChatColor.YELLOW + "Global chat is enabled.";
	static String global_off = prefix + ChatColor.YELLOW + "Global chat is disabled.";

	static void help(CommandSender sender) {
		sender.sendMessage("Hello World.");
	}

	static String silenced_global(String player) {
		return prefix + ChatColor.YELLOW + "Global Chat is disabled by " + player + ".";
	}

	static String resumed_global(String player) {
		return prefix + ChatColor.YELLOW + "GlobalChat is now resumed by " + player + ".";
	}

}
