package com.gmail.louisdw1.freddie;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class parkour implements Listener{
	//This is a single line comment
	/*This is a multiline comment:
	 * 
	 * 
	 */
	//This is creating a variable with lots of variables inside.
	//public static HashMap<java.lang.String, Integer> checkpoint = new HashMap<java.lang.String, Integer>();

	public static void onPlayJoinParkour(Player player)
	{
		//Note go onto eclipse and get the player variable and press . after it and scroll through :-)
		//Sends a message to a player player.getName() returns a string of the players name
		player.sendMessage("Welcome "+player.getName()+"!");
		player.teleport (new Location(Bukkit.getWorld("world_parkour"), 183, 4, 374));
		
	}
	@EventHandler
	public void onSpawn(CreatureSpawnEvent event)
	{
		Entity entity = event.getEntity();
		if(entity.getWorld()==Bukkit.getWorld("world_parkour"))
		{
			event.setCancelled(true);
		}
		//Bukkit.broadcastMessage("Location : "+event.getLocation());
	}
	
}
