package me.joris.welcomeman;
import org.bukkit.plugin.java.JavaPlugin;
public class WelcomeCore extends JavaPlugin{
	
	String Name = "WelcomeMan";
	String ConsolePrefix = "[" + Name + "]" + " ";
	String EnableSuccess = ConsolePrefix + "Successfull enabled!";
	String DisableSuccess = ConsolePrefix + "Successfully disabled!";
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new WelcomeListener(), this);
		System.out.println(EnableSuccess);
	}
	public void onDisable(){
		System.out.println(DisableSuccess);
	}
}
