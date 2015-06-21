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
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		Calendar calendar = Calendar.getInstance();
		String currentTime = time.format(calendar.getTime());
		player.sendMessage("-----------------------------------------------------");
		player.sendMessage(ChatColor.GRAY + "Hello" + " " + ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY + ", " + "the time is" + " " + currentTime);
		player.sendMessage(ChatColor.GRAY + "Enjoy your stay!");
		player.sendMessage("-----------------------------------------------------");
	}
}
