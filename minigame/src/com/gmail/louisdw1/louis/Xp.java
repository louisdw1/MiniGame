package com.gmail.louisdw1.louis;

import org.bukkit.entity.Player;

public class Xp {

public static void dmgtoxp(double damage, Player player)
{
	String player1= player.toString();
//DBConnect.up(player1,damage);
	int i = 0;
	while(damage>=1.1)
	{
		i++;
		player.giveExp(1);
		damage = damage-0.85;
		//Bukkit.broadcastMessage(ChatColor.YELLOW +"Xp " +player.getExp()+" increment: "+i);

	}
	
	
}

}
