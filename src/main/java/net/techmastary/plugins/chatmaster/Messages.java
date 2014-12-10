package net.techmastary.plugins.chatmaster;

import org.bukkit.ChatColor;

public class Messages {

	static String prefix = ChatColor.GOLD + "[" + ChatColor.RED + "ChatMaster" + ChatColor.GOLD + "] ";
	static String help = prefix + ChatColor.YELLOW + "Use \"/cm help\" for help.";
	static String no_permission = prefix + ChatColor.WHITE + "Unknown command. Type \"help\" for help.";

	static String silenced_global(String player) {
		return prefix + ChatColor.YELLOW + "Global Chat is disabled by " + player + ".";
	}

	static String resumed_global(String player) {
		return prefix + ChatColor.YELLOW + "GlobalChat is now resumed by " + player + ".";
	}

}
