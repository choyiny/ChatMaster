package net.techmastary.plugins.chatmaster;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatMaster extends JavaPlugin implements Listener {
	public static boolean Silenced;
	ChatCommand chatcmds = new ChatCommand();
	ChatPing chatping = new ChatPing();
	ChatEventListener chateventlistener = new ChatEventListener();
	public static boolean update = false;
	public static String name = "";
	public static long size = 0;

	@Override
	public void onDisable() {
		System.out.println("Disabled ChatMaster " + getDescription().getVersion() + ".");
	}

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		System.out.println("Enabled ChatMaster " + getDescription().getVersion() + ".");
		System.out.println("Remember to subscribe to our Youtube channel for more updates: http://youtube.com/TechMastary");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(this.chateventlistener, this);
		getServer().getPluginCommand("cm").setExecutor(this.chatcmds);
		getServer().getPluginCommand("pping").setExecutor(this.chatping);
		Silenced = false;
		Updater updater = new Updater(this, "ChatMaster", this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
		update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
		name = updater.getLatestVersionString(); // Get the latest version
		size = updater.getFileSize(); // Get latest size
		if (getConfig().getBoolean("metrics")) {
			try {
				Metrics metrics = new Metrics(this);
				metrics.start();
			} catch (IOException e) {
				// I'm sad lol.
			}
		} else {
			System.out.println("[ChatMaster] Metrics was not enabled");
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("cmupdate")) {
			if (sender.hasPermission("chat.update") && ChatMaster.update) {
				@SuppressWarnings("unused")
				Updater updater = new Updater(this, "chatmaster", this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
				sender.sendMessage("ChatMaster is updating. Check console for progress.");
			}
		}

		return true;

	}

}