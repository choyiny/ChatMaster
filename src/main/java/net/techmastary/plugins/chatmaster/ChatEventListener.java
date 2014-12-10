package net.techmastary.plugins.chatmaster;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ChatEventListener implements Listener {
	static boolean ChatSilenced = false;
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		//Global Chat Disabling
		if (ChatSilenced) {
			if (!player.hasPermission("cm.bypass")) {
				event.setCancelled(true);
			} else {
				event.setCancelled(false);
			}
		}	
	}
}
