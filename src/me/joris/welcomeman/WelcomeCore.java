package me.joris.welcomeman;
import org.bukkit.Bukkit;
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
	String ConsoleNoPlayer = ConsolePrefix + "Please specify a player!";
	String ConsoleNotEnoughArgs = ConsolePrefix + "Please specify a name!";
	String ConsoleNameSetSuccess = ConsolePrefix + "Name successfully set!";
	String ConsoleTooManyArgs = ConsolePrefix + "Please check your command formatting! Specify a player and a name";
	String ConsoleError = ConsolePrefix + "Something went wrong!";
	String NotEnoughArgs = NameManagementPrefix + ChatColor.RED + "Please specify a name!";
	String TooManyArgs = NameManagementPrefix + ChatColor.RED + "Please specify one name!";
	String error = NameManagementPrefix + ChatColor.RED + "Oops... something went wrong!";
	String NoPermission = WelcomePrefix + ChatColor.RED + "Many permissions, such denied, much no. Wow.";
	String NameChangePermission = "welcomeman.changename";
	String NameChangePermissionOthers = "welcomeman.changename.others";
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
					getConfig().set(player.getUniqueId().toString() + ConfigName, player.getDisplayName());
					saveConfig();
					player.sendMessage(NotEnoughArgs);
					return true;
				}else if(args.length == 1){
					if(player.hasPermission(NameChangePermission)){
						getConfig().set(player.getUniqueId().toString() + ConfigName, player.getDisplayName());
						getConfig().set(player.getUniqueId().toString() + ConfigCustomName, args[0]);
						saveConfig();
						player.sendMessage(NameSetSuccess);
						return true;
					}else{
						getConfig().set(player.getUniqueId().toString() + ConfigName, player.getDisplayName());
						saveConfig();
						player.sendMessage(NoPermission);
						return true;
					}
				}else if(args.length == 2){
					if(player.hasPermission(NameChangePermissionOthers)){
						Player target = Bukkit.getPlayer(args[0]);
						getConfig().set(player.getUniqueId().toString() + ConfigName, player.getDisplayName());
						getConfig().set(target.getUniqueId().toString() + ConfigName, target.getDisplayName());
						getConfig().set(target.getUniqueId().toString() + ConfigCustomName, args[1]);
						saveConfig();
					}else{
						getConfig().set(player.getUniqueId().toString() + ConfigName, player.getUniqueId());
						saveConfig();
						player.sendMessage(NoPermission);
						return true;
					}
				}else if(args.length > 2){
					getConfig().set(player.getUniqueId().toString() + ConfigName, player.getDisplayName());
					saveConfig();
					player.sendMessage(TooManyArgs);
					return true;
				}else{
					getConfig().set(player.getUniqueId().toString() + ConfigName, player.getDisplayName());
					saveConfig();
					player.sendMessage(error);
					return true;
				}
			}else{
				if(args.length == 0){
					sender.sendMessage(ConsoleNoPlayer);
					return true;
				}else if(args.length == 1){
					sender.sendMessage(ConsoleNotEnoughArgs);
					return true;
				}else if(args.length == 2){
					Player target = Bukkit.getPlayer(args[0]);
					getConfig().set(target.getUniqueId().toString() + ConfigName, target.getDisplayName());
					getConfig().set(target.getUniqueId().toString() + ConfigCustomName, args[1]);
					saveConfig();
					sender.sendMessage(ConsoleNameSetSuccess);
					return true;
				}else if(args.length > 2){
					sender.sendMessage(ConsoleTooManyArgs);
					return true;
				}else{
					sender.sendMessage(ConsoleError);
					return true;
				}
			}
		}
		return false;
	}
}
