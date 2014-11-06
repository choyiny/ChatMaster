package main.java.net.techmastary.plugins.chatmaster;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class ChatMaster extends JavaPlugin{
	
	public void onEnable() {
		this.saveDefaultConfig();
		System.out.println("Enabled ChatMaster v" + getDescription().getVersion() + ".");
		
		
	}
	
	public void onDisable() {
		System.out.println("Disabled ChatMaster v" + getDescription().getVersion() + ".");
	}
	
}
