package net.techmastary.plugins.chatmaster;

import java.io.IOException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatMaster extends JavaPlugin implements Listener {
	public static boolean Silenced;
	ChatCommand chatcmds = new ChatCommand();
	public static double version = 1.1;
	ChatEventListener chateventlistener = new ChatEventListener();

	@Override
	public void onDisable() {
		System.out.println("Disabled ChatMaster " + version + ".");
	}

	@Override
	public void onEnable() {
		loadConf();
		System.out.println("Enabled ChatMaster " + version + ".");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(this.chateventlistener, this);
		getServer().getPluginCommand("cm").setExecutor(this.chatcmds);
		Silenced = false;
		if (getConfig().getBoolean("metrics")) {
			try {
				Metrics metrics = new Metrics(this);
				metrics.start();
			} catch (IOException e) {
				// I'm sad.
			}
		} else {
			System.out.print("[AdminUtils] Metrics was not enabled");
		}
	}

	private void loadConf() {
		getConfig().addDefault("metrics", true);
	}
}