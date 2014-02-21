package net.techmastary.plugins.chatmaster;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatMaster extends JavaPlugin implements Listener {
	public static boolean Silenced;
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
		System.out.println("Subscribe to our Youtube channel for more: http://youtube.com/TechMastary");
		System.out.println("If you find any bugs, please report back to our BukkitDev page.");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new ChatEventListener(), this);
		getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
		getServer().getPluginCommand("cm").setExecutor(new ChatCommand());
		getServer().getPluginCommand("pping").setExecutor(new ChatPing());
		Silenced = false;
		Updater updater = new Updater(this, 63203, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
		update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
		name = updater.getLatestName();
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
		if (getConfig().getBoolean("update")) {
			if (label.equalsIgnoreCase("cmupdate")) {
				if (sender.hasPermission("chat.update") && ChatMaster.update) {
					Updater updater = new Updater(this, 63203, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
					sender.sendMessage("ChatMaster is updating. Check console for progress.");
				}	
			}
		} else {
			sender.sendMessage("Updating is not enabled. Please enable updating in the config file.");
		}
		return true;

	}
}
