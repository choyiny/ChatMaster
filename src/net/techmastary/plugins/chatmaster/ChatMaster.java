package net.techmastary.plugins.chatmaster;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatMaster extends JavaPlugin implements Listener {
	public static boolean Muted;
	ChatCommand chatcmds = new ChatCommand();
	public static double version = 1.1;

	@Override
	public void onDisable() {
		System.out.println("Disabled ChatMaster " + version + ".");
	}

	@Override
	public void onEnable() {
		System.out.println("Enabled ChatMaster " + version + ".");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginCommand("cm").setExecutor(this.chatcmds);
		Muted = false;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnPlayerChat(AsyncPlayerChatEvent event) {
		if (!event.getPlayer().hasPermission("chat.speak") && (Muted == true)) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.GRAY + "Global chat is currently " + ChatColor.RED + "Disabled.");
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void OnPlayerJoin(PlayerJoinEvent event) {
		if (Muted == true) {
			event.getPlayer().sendMessage(ChatColor.GRAY + "Global chat is currently disabled.");
			if (event.getPlayer().hasPermission("chat.speak")) {
				event.getPlayer().sendMessage(ChatColor.GRAY + "You have permission to talk.");
			}
		}
	}
}