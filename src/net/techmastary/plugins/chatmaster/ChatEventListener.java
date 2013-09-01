package net.techmastary.plugins.chatmaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ChatEventListener implements Listener {
	static List<String> nochat = new ArrayList<String>();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void OnPlayerChat(AsyncPlayerChatEvent event) {
		Set<?> receivers = event.getRecipients();
		Player[] online = Bukkit.getServer().getOnlinePlayers();
		for (Player p : online) {
			if ((!nochat.contains(p.getName())) || (!receivers.contains(p)))
				continue;
			receivers.remove(p);
		}
		if (!event.getPlayer().hasPermission("chat.bypass") && (ChatMaster.Silenced == true)) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(ChatColor.GRAY + "Global chat is currently " + ChatColor.RED + "Disabled.");
		}

		if (!event.getPlayer().hasPermission("chat.speak")) {
			event.setCancelled(true);
		}

		if (!event.getPlayer().hasPermission("chat.listen")) {
			ChatEventListener.nochat.add(event.getPlayer().getName());
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void OnPlayerJoin(PlayerJoinEvent event) {
		if (ChatMaster.Silenced == true) {
			event.getPlayer().sendMessage(ChatColor.GRAY + "Global chat is currently disabled.");
			if (event.getPlayer().hasPermission("chat.bypass")) {
				event.getPlayer().sendMessage(ChatColor.GRAY + "You have permission to talk.");
			}
		}
	}
}
