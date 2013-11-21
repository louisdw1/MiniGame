package com.gmail.louisdw1.freddie;

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
		
	}
	@EventHandler
	public void onSpawn(CreatureSpawnEvent event)
	{
		
		//Bukkit.broadcastMessage("Location : "+event.getLocation());
	}
	
}
