package me.joris.welcomeman;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class WelcomeCore extends JavaPlugin{
	
	String Name = "WelcomeMan";
	String ConsolePrefix = "[" + Name + "]" + " ";
	String WelcomePrefix = ChatColor.DARK_GRAY + "";
	String NameManagementPrefix = ChatColor.DARK_GRAY + "Management>" + ChatColor.RESET + " ";
	String EnableSuccess = ConsolePrefix + "Successfull enabled!";
	String DisableSuccess = ConsolePrefix + "Successfully disabled!";
	String NameSetSuccess = NameManagementPrefix + ChatColor.GREEN + "Name successfully set!";
	String NotEnoughArgs = NameManagementPrefix + ChatColor.RED + "Please specify a name!";
	String TooManyArgs = NameManagementPrefix + ChatColor.RED + "Please specify one name!";
	String error = NameManagementPrefix + ChatColor.RED + "Oops... something went wrong!";
	String username = "";
	String ConfigName = ".Name";
	String ConfigCustomName = ".CustomName";
	
	public void onEnable(){
		getServer().getPluginManager().registerEvents(new WelcomeListener(this), this);
		System.out.println(EnableSuccess);
	}
	public void onDisable(){
		System.out.println(DisableSuccess);
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(command.getName().equalsIgnoreCase("changename")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 0){
					player.sendMessage(NotEnoughArgs);
					return true;
				}else if(args.length == 1){
					getConfig().set(player.getUniqueId().toString() + ConfigName, player.getName());
					getConfig().set(player.getUniqueId().toString() + ConfigCustomName, args[0]);
					player.sendMessage(NameSetSuccess);
					return true;
				}else if(args.length > 1){
					player.sendMessage(TooManyArgs);
					return true;
				}else{
					player.sendMessage(error);
					return true;
				}
			}else{
				//TODO Make that!
				sender.sendMessage("This is not working yet!");
			}
		}
		return false;
	}
}
