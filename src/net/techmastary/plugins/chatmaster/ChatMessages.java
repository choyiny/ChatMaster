package net.techmastary.plugins.chatmaster;

public class ChatMessages {

	private static String colorize(String string) {
		if (string == null) {
			return null;
		}
		return string.replaceAll("&([0-9a-f])", "\u00A7$1");
	}

	static String invalid_arguments = colorize("&cInvalid Arguments.");
	static String player_not_found = colorize("&4ERROR: &cPlayer Not found.");
}
