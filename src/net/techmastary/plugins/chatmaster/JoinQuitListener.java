package net.techmastary.plugins.chatmaster;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class JoinQuitListener implements Listener {
	
    static ChatMaster plugin;

	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(plugin.getConfig().getBoolean("joinmessage.enable")) {
			event.setJoinMessage(plugin.getConfig().getString("joinmessage.message").replaceAll("&([0-9a-f])", "\u00A7$1").replace("%player%", player.getName()));
		} else {
			event.setJoinMessage(null);
		}
	}
	
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
	if(plugin.getConfig().getBoolean("quitmessage.enable")) {
		event.setQuitMessage(plugin.getConfig().getString("quitmessage.message").replaceAll("&([0-9a-f])", "\u00A7$1").replace("%player%", player.getName()));
		} else {
			event.setQuitMessage(null);
		}
	}

}
