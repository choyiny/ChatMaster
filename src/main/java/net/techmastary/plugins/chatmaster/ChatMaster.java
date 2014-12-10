package net.techmastary.plugins.chatmaster;

import org.bukkit.plugin.java.JavaPlugin;

public class ChatMaster extends JavaPlugin{
	
	public void onEnable() {
		this.saveDefaultConfig();
		System.out.println("Enabled ChatMaster v" + getDescription().getVersion() + ".");
		getServer().getPluginManager().registerEvents(new ChatEventListener(), this);
		getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
		getServer().getPluginCommand("cm").setExecutor(new ChatCommand());
	}
	
	public void onDisable() {
		System.out.println("Disabled ChatMaster v" + getDescription().getVersion() + ".");
	}
	
}
