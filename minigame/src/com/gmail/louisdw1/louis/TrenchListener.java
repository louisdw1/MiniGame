package com.gmail.louisdw1.louis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TrenchListener implements Listener {
	@EventHandler
    public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.isInsideVehicle())
		{
			Entity vehicle = player.getVehicle();
			Bukkit.broadcastMessage("Vehicle : "+vehicle);
		if(event.getAction()==Action.RIGHT_CLICK_AIR)
		{
			if(player.getItemInHand().getType()==Material.BOW)
			{
				
			}
		}
	}
	}
}
