package me.joris.welcomeman;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class WelcomeListener implements Listener{
	
	DateFormat time = new SimpleDateFormat("HH:mm");
	
	private final WelcomeCore welcomeCore;
	public WelcomeListener(WelcomeCore welcomeCore){
		this.welcomeCore = welcomeCore;
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		Calendar calendar = Calendar.getInstance();
		String currentTime = time.format(calendar.getTime());
		if(welcomeCore.getConfig().getString(player.getUniqueId().toString() + welcomeCore.ConfigCustomName) == null){
			welcomeCore.username = player.getDisplayName();
		}else{
			welcomeCore.username = welcomeCore.getConfig().getString(player.getUniqueId().toString() + welcomeCore.ConfigCustomName);
		}
		welcomeCore.updateUsername(player);
		player.sendMessage("-----------------------------------------------------");
		player.sendMessage(ChatColor.GRAY + "Hello" + " " + ChatColor.GOLD + welcomeCore.username + ChatColor.GRAY + ", " + "the time is" + " " + currentTime);
		player.sendMessage(ChatColor.GRAY + "Enjoy your stay!");
		player.sendMessage("-----------------------------------------------------");
	}
}
