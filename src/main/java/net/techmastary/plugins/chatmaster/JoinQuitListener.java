package main.java.net.techmastary.plugins.chatmaster;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

	ChatMaster plugin;

	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (plugin.getConfig().getBoolean("joinmsg.enable")) {
			event.setJoinMessage(plugin.getConfig().getString("joinmsg.message").replaceAll("&([0-9a-f])", "\u00A7$1").replace("%player%", player.getName()));
		} else {
			event.setJoinMessage(null);
		}
		
		File folder = new File(plugin.getDataFolder() + "/", "users");
		File UserFile = new File(folder, player.getUniqueId().toString() + ".yml");
		YamlConfiguration UserData = YamlConfiguration.loadConfiguration(UserFile);
		if (!UserFile.exists()) {
			try {
				UserFile.createNewFile();
				UserData.set("deaf", false);
				UserData.save(UserFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("quitmsg.enable")) {
			event.setQuitMessage(plugin.getConfig().getString("quitmsg.message").replaceAll("&([0-9a-f])", "\u00A7$1").replace("%player%", player.getName()));
		} else {
			event.setQuitMessage(null);
		}
	}
}
