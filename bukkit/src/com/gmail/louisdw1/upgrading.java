package com.gmail.louisdw1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.louisdw1.freddie.parkour;
import com.gmail.louisdw1.louis.Listener2;
import com.gmail.louisdw1.louis.ListenerBasic;
import com.gmail.louisdw1.louis.SLAPI;
import com.gmail.louisdw1.louis.SetInv;
import com.gmail.louisdw1.louis.TrenchListener;
import com.gmail.louisdw1.louis.WorldManager;
import com.gmail.louisdw1.louis.hash;

@SuppressWarnings("serial")
public final class upgrading extends JavaPlugin implements Listener,Serializable,CommandExecutor{
	/*Note:
	 * lowest tier = 0
	 * 
	 * #######################
	 * #####Variables########
	 * #######################
	 */
	/*How many tiers (max) does a player go if he/she is killed
	 *  HIGH RISK OF CHANGE
	 *  Deprecated
	*/
	@Deprecated
	int humil = 1;
	/*How many points (max) does a player go if he/she is killed
	 *  May become deprecated
	*/
	int deduction1 = 30;
	/*Bool on how the player will be
	 * deducted for dying. False = based on tier.
	 * true = deduction
	*/
	boolean deductionsystem = false;
	/*#######################
	 * ########Code##########
	 * #######################
	 */
	public static void candamageTrue(Player player)
	{
		if(canKill.get(player.getName())==false)
		{
		player.sendMessage(ChatColor.DARK_BLUE+"You can now be damaged!");
		canKill.put(player.getName(), true);
		}
		}
	
	public static Map<java.lang.String, Integer> getGame()
	{
		return game;
	}
	public static void putGame(Player player,int game1)
	{
		//Bukkit.broadcastMessage("Player: "+player+" game: "+game1);
		game.put(player.getName(), game1);
		
		//Bukkit.broadcastMessage("Get game: "+game.get(player.getName()));

	}
	public static void putFall(Player player,boolean canFallDam)
	{
		//Bukkit.broadcastMessage("Player: "+player+" game: "+game1);
		fallDam.put(player.getName(), canFallDam);
		
		//Bukkit.broadcastMessage("Get game: "+game.get(player.getName()));

	}
	public static Map<String, Boolean> getFall()
	{
		return fallDam;
	}
	public static Map<String, Boolean> getCanKill()
	{
		return canKill;
	}
	public boolean pointHelper(Player player)
	{
		java.lang.String name = player.getName();
		int point = points.get(name);
		if(point>=SetInv.points(tier.get(name)))
		{
			tier.put(name, (tier.get(name))+1);
			player.sendMessage(ChatColor.GOLD+""+ChatColor.BOLD+"Congratulations you have steped up a tier! You are now on tier: "+tier.get(name));
			points.put(name,point-SetInv.points(tier.get(name)-1));
			return false;
		}else if(point<0)
		{
			tier.put(name, (tier.get(name))-1);
			points.put(name,point+SetInv.points(tier.get(name)));
			if(tier.get(name)<0)
			{
				tier.put(name, 0);
				points.put(name, 0);
				return true;
			}
			player.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"Unfortunatly you have steped down a tier! You are now on tier: "+tier.get(name));

			return false;
			}
		else{
			return true;
		}
	}
	/*Usage:
	 * tierScore(Player killer, Player victim)
	 *Updates a players score.
	 */
	public void tierScore(Player[] killers, Player victim1)
	{
		//java.lang.String killer = killer1.getName();		//TEST
		java.lang.String victim = victim1.getName();		//TEST
//Bukkit.broadcastMessage("killers : "+killers+" Vicitm : "+victim1);
/*
		int vicOld = tier.get(victim);
		int killOld = tier.get(killer);

		tier.put(killer, killOld+1);
		if(vicOld<humil)
		{
			tier.put(victim, 0);
		}else {
			tier.put(victim, vicOld-humil);
		}
        victim1.sendMessage(ChatColor.RED +"You have died! Your new tier is: "+tier.get(victim));
        killer1.sendMessage(ChatColor.YELLOW +"You have killed a player! Your new tier is: "+tier.get(killer)+"  "+points.get(killer));
*/
		int deduction;
		if(deductionsystem==false)
		{
		
			if(tier.get(victim)<2)
			{
				deduction =  20;

			}else{
				deduction = (SetInv.points((tier.get(victim)-2)));
			}
		}else
		{
			deduction= deduction1;
		}
		//Bukkit.broadcastMessage("Tier : "+tier.get(victim));
		if(tier.get(victim)==0)
		{
			points.put(victim,0);

		}else
		{
		points.put(victim, points.get(victim)-deduction);
		Bukkit.broadcastMessage("Points : "+points.get(victim));
		}
		victim1.sendMessage(ChatColor.RED+""+ChatColor.BOLD+"You have been Deducted "+deduction+" points for dying!");
		while(pointHelper(victim1)==false);
		int i=0;
		if(killers!=null&&killers.length!=0)
		{
		while(i<=killers.length)
		{
		while(pointHelper(killers[i])==false);
		SetInv.main(killers[i],tier.get(killers),points.get(killers[i].getName()));
		i++;
		}
		}
		SetInv.main(victim1,tier.get(victim),points.get(victim1.getName()));
		
	}
	public Map<java.lang.String, Integer> tier = new HashMap<java.lang.String, Integer>(); 
	public Map<java.lang.String, Integer> points = new HashMap<java.lang.String, Integer>(); 
	public static Map<java.lang.String, Boolean> canKill = new HashMap<java.lang.String, Boolean>(); 
	public static Map<java.lang.String, Integer> game = new HashMap<java.lang.String, Integer>(); 
	public static Map<java.lang.String, Boolean> fallDam = new HashMap<java.lang.String, Boolean>(); 

	//public HashMap<java.lang.String, Float> damagedata = new HashMap<java.lang.String, Float>();

	//public HashMap<Integer, HashMap<java.lang.String, Float>> damage2 = new HashMap<Integer, HashMap<java.lang.String, Float>>();

	public Map<java.lang.String, HashMap<Integer, HashMap<java.lang.String, Float>>> damageList = new HashMap<java.lang.String, HashMap<Integer, HashMap<java.lang.String, Float>>>();

	
	@Override
	    public void onEnable(){

		Bukkit.broadcastMessage(ChatColor.YELLOW + ("Progression loaded!"));
		
		getCommand("leave").setExecutor(new ListenerBasic());

		
try{
			tier = SLAPI.load("tier.bin");
	            }catch(Exception e){
	                //handle the exception
	                e.printStackTrace();
	            }
		try{
			canKill = SLAPI.load("canKill.bin");
	            }catch(Exception e){
	                //handle the exception
	                e.printStackTrace();
	            }
		try{
			damageList = SLAPI.load("damage.bin");
	            }catch(Exception e){
	                //handle the exception
	                e.printStackTrace();
	            }
		try{
			points = SLAPI.load("points.bin");
	            }catch(Exception e){
	                //handle the exception
	                e.printStackTrace();
	            }
		try{
			game = SLAPI.load("game.bin");
	            }catch(Exception e){
	                //handle the exception
	                e.printStackTrace();
	            }
		  getLogger().info("onEnable has been invoked!"); 
	        getServer().getPluginManager().registerEvents(this, this);
	        getServer().getPluginManager().registerEvents(new WorldManager(), this);

	        getServer().getPluginManager().registerEvents(new Listener2(), this);
	        getServer().getPluginManager().registerEvents(new ListenerBasic(), this);
	        getServer().getPluginManager().registerEvents(new TrenchListener(), this);
	        getServer().getPluginManager().registerEvents(new parkour(), this);
			  getLogger().info("Listener enabled!"); 
			  
			  
		  // playerList.put(player, playerData(player));
			  
	  }
	@SuppressWarnings("null")
	public void deathHelper(Player player)
	{
		canKill.put(player.getName(),false);
		if(damageList.containsKey(player.getName()))
		{
	    	//Bukkit.broadcastMessage(ChatColor.YELLOW +"TESTSETSET " );
	    	
			double amountDelt = 0;
		 int id = 0;
		 int val = 0;
	//	Bukkit.broadcastMessage(ChatColor.YELLOW +"Amount healed : "+amount);
		 double amount = 1;
		 Player[] players = null;
		while(amountDelt<amount )
		 {
				HashMap<Integer, HashMap<java.lang.String, Float>> damage2 =  damageList.get(player.getName());

			 HashMap<java.lang.String, Float>  damagedata = damage2.get(id);
			 amount = damage2.size();
				ArrayList<java.lang.String> Keys = new ArrayList<java.lang.String>(damagedata.keySet());
				java.lang.String key = Keys.get(0);
				//Bukkit.broadcastMessage(ChatColor.YELLOW +"Data for damage : "+damagedata.values()+"  "+key);
				float points1 = damagedata.get(key);
				java.lang.String[] split = (Float.toString(points1)).split("\\.");
				//System.out.println(split[0] + " sadsa " + split[1]);
		      int points2 = Integer.parseInt(split[0]);
		      if(points.containsKey(key))
		      {
		    	  points2 = points2+points.get(key);
		      }
		      if(key!="nat")
		      {
		    	  players[val] = Bukkit.getServer().getPlayer(key);
		    	  points.put(key,points2);
		    	  val++;

		      }
		      //Bukkit.broadcastMessage(ChatColor.YELLOW +"TESTSETSET "+points2);
			amountDelt++;
			id++;
		 }
		
		damageList.remove(player.getName());

		player.setExp(0);
		//e.getEntity().openInventory();
		
		tierScore(players, player);
		
	}
	}
	@SuppressWarnings("null")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		//Bukkit.broadcastMessage("TEST");

		if(!(game.get(e.getEntity().getName()).equals(1)))
		{
			//Bukkit.broadcastMessage("Return");
			return;
		}
		//Bukkit.broadcastMessage("Test");
		Player player = e.getEntity();
		canKill.put(player.getName(),false);
		if(damageList.containsKey(player.getName()))
		{
	    	//Bukkit.broadcastMessage(ChatColor.YELLOW +"TESTSETSET " );
	    	
			double amountDelt = 0;
		 int id = 0;
		 int val = 0;
	//	Bukkit.broadcastMessage(ChatColor.YELLOW +"Amount healed : "+amount);
		 double amount = 1;
		 Player[] players = null;
		while(amountDelt<amount )
		 {
				HashMap<Integer, HashMap<java.lang.String, Float>> damage2 =  damageList.get(player.getName());

			 HashMap<java.lang.String, Float>  damagedata = damage2.get(id);
			 amount = damage2.size();
				ArrayList<java.lang.String> Keys = new ArrayList<java.lang.String>(damagedata.keySet());
				java.lang.String key = Keys.get(0);
				//Bukkit.broadcastMessage(ChatColor.YELLOW +"Data for damage : "+damagedata.values()+"  "+key);
				float points1 = damagedata.get(key);
				java.lang.String[] split = (Float.toString(points1)).split("\\.");
				//System.out.println(split[0] + " sadsa " + split[1]);
		      int points2 = Integer.parseInt(split[0]);
		      if(points.containsKey(key))
		      {
		    	  points2 = points2+points.get(key);
		      }
		      if(key!="nat")
		      {
		    	  players[val] = Bukkit.getServer().getPlayer(key);
		    	  points.put(key,points2);
		    	  val++;

		      }
		      //Bukkit.broadcastMessage(ChatColor.YELLOW +"TESTSETSET "+points2);
			amountDelt++;
			id++;
		 }
		//Bukkit.broadcastMessage("Player : "+players+" Victim : "+player);
		damageList.remove(player.getName());

		player.setExp(0);
		//e.getEntity().openInventory();
		
		tierScore(players, player);
		
	}
	

	}
	@EventHandler
public void onRespawn(PlayerRespawnEvent event)
	{
		//Bukkit.broadcastMessage("Game : "+game.get(event.getPlayer().getName()));
		if(!(game.get(event.getPlayer().getName()).equals(1)))
		{
			//Bukkit.broadcastMessage("Return");

			return;
		}
		Player player1 = event.getPlayer(); 
	
		java.lang.String player = player1.getName();
		if(!(tier.containsKey(player)))
		{
			tier.put(player,0);
		}
		if(!(points.containsKey(player)))
		{
			points.put(player,0);
		}
		SetInv.main(player1,tier.get(player),points.get(player1.getName()));

	}
	@EventHandler
	public void onPlayerJoinGame(PlayerJoinEvent evt) {
		
	    Player player1 = evt.getPlayer(); 

		java.lang.String player = player1.getName();		


	    if(tier.containsKey(player)==false)
	    {
	    	tier.put(player, 0);
			points.put(player1.getName(),0);

	    }
	    if(canKill.containsKey(player)==false)
	    {
	    	canKill.put(player1.getName(), false);
	    }
	    
     
	}
	@EventHandler
    public void onDrop(PlayerDropItemEvent event) {
		int game1 = game.get(event.getPlayer().getName());
		if(game1 !=1 && game1 !=0)
		{
			return;
		}
        Player player = event.getPlayer();
        
       // java.lang.String player1 = player.getName();		

        GameMode gamemode = player.getGameMode();
        if(!(gamemode==GameMode.CREATIVE))
        {
        	
    		event.getItemDrop().remove();
    		event.setCancelled(true);
       
        }
        }
	@EventHandler
	public void onHeal(EntityRegainHealthEvent event)
	{
		
		if(!(event.getEntity() instanceof Player))
		{
			return;
		}
		
		Player player = (Player) event.getEntity();
		if(!(game.get(player.getName()).equals(1)))
		{
			return;
		}
		double amount = event.getAmount();
		 double amountDelt = 0;
		 int id = 0;
	//	Bukkit.broadcastMessage(ChatColor.YELLOW +"Amount healed : "+amount);
		 while(amountDelt<amount)
		 {
				HashMap<Integer, HashMap<java.lang.String, Float>> damage2 =  damageList.get(player.getName());
			 if(damage2.containsKey(id)==true)
			 {
			 HashMap<java.lang.String, Float>  damagedata = damage2.get(id);
			 //Bukkit.broadcastMessage("Key set : "+damagedata.keySet());
				ArrayList<java.lang.String> Keys = new ArrayList<java.lang.String>(damagedata.keySet());
				java.lang.String key = Keys.get(0);
				//Bukkit.broadcastMessage(ChatColor.YELLOW +"Data for damage : "+damagedata.values()+"  "+key);
				float damage = damagedata.get(key);
			if(damage>=1)
			{
				damage = damage-1;
				damagedata.put(key, (damage));
				
				}
			//Bukkit.broadcastMessage(ChatColor.YELLOW +"after heal damage : "+damage);
			if(amount<1)
			{
				amountDelt=amount;
			}
			 amountDelt++;
			 id++;
			 }
		 	else
		 	{
		 		 amountDelt=amount;
		 	}
		 }
	}
	@EventHandler
	public void EntityDamage(EntityDamageEvent event)
	{
		if (!(event.getEntity() instanceof Player)) {
			return;
			}
		
		/*if (!(( (org.bukkit.event.entity.EntityDamageByEntityEvent) event.getEntity()).getDamager() instanceof Player)) {
			return;
			}
			*/
		if(!(event.getCause() == DamageCause.ENTITY_ATTACK))
		{
			Player player = (Player) event.getEntity();
			if(damageList.containsKey(player.getName())==false)
	    	{
	    		 getLogger().info("Putting "+player.getName()+" into damage map!");
	    		damageList.put(player.getName(), new HashMap<Integer, HashMap<java.lang.String, Float>>());
	    	}
	double damage = event.getDamage();
	if(player.getHealth()<=damage)
	{
		damage=player.getHealth();

	}
	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Damage : "+damage);

	HashMap<Integer, HashMap<java.lang.String, Float>> damage2 =  damageList.get(player.getName());
	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Length : "+damage2.size());

	int nextInt = hash.getNextInt(damage2,"nat");
	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Next int : "+nextInt);
	// float percentage = (float) ((damage/player.getMaxHealth())*100);

	 if(!(damage2.containsKey(nextInt)))
	 {
	 damage2.put(nextInt , new HashMap<java.lang.String, Float>());   
	 }
	 HashMap<java.lang.String, Float>  damagedata = damage2.get(nextInt);
	 float damage1 = (float) damage;
	 if(damagedata.containsKey("nat")==true)
	 {
		float damageOld = damagedata.get("nat"); 
		damage1 = (float) (damage+ damageOld);
		//Bukkit.broadcastMessage(ChatColor.YELLOW +"ADKJSAKLDJKL : " +damagedata.containsKey(Damager.getName()));
		damagedata.remove("nat");
	 }

	 damagedata.put("nat", damage1);
		}
	}
	@EventHandler
	public void EntityDamageByEntityEvent(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
				
		if (!(event.getEntity() instanceof Player)) {
			return;
			}
		
		/*if (!(( (org.bukkit.event.entity.EntityDamageByEntityEvent) event.getEntity()).getDamager() instanceof Player)) {
			return;
			}
			*/
		if(!(event.getDamager() instanceof Player))
		{
			Player player = (Player) event.getEntity();
			if(damageList.containsKey(player.getName())==false)
	    	{
	    		 getLogger().info("Putting "+player.getName()+" into damage map!");
	    		damageList.put(player.getName(), new HashMap<Integer, HashMap<java.lang.String, Float>>());
	    	}
	double damage = event.getDamage();
	if(player.getHealth()<=damage)
	{
		damage=player.getHealth();

	}
	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Damage : "+damage);

	HashMap<Integer, HashMap<java.lang.String, Float>> damage2 =  damageList.get(player.getName());
	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Length : "+damage2.size());

	int nextInt = hash.getNextInt(damage2,"nat");
	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Next int : "+nextInt);
	// float percentage = (float) ((damage/player.getMaxHealth())*100);

	 if(!(damage2.containsKey(nextInt)))
	 {
	 damage2.put(nextInt , new HashMap<java.lang.String, Float>());   
	 }
	 HashMap<java.lang.String, Float>  damagedata = damage2.get(nextInt);
	 float damage1 = (float) damage;
	 if(damagedata.containsKey("nat")==true)
	 {
		float damageOld = damagedata.get("nat"); 
		damage1 = (float) (damage+ damageOld);
		//Bukkit.broadcastMessage(ChatColor.YELLOW +"ADKJSAKLDJKL : " +damagedata.containsKey(Damager.getName()));
		damagedata.remove("nat");
	 }

	 damagedata.put("nat", damage1);
		}
		 Player Damager = (Player) event.getDamager();
		 Player player = (Player) event.getEntity();
		 if(!(game.get(player.getName()).equals(1)))
			{
				return;
			}
		    if(event.getEntity() instanceof Player && Damager instanceof Player)
		    {

		    	if(canKill.get(player.getName())==false)
		    	{
		    		Damager.sendMessage(ChatColor.RED+player.getName()+" has spawn protection! You cannot damage him!");
		    		event.setCancelled(true);
		    	}else if(canKill.get(Damager.getName())==false)
		    	{
		    		//Bukkit.broadcastMessage("Damager Damaging!");
		    		candamageTrue(Damager);
		    	}
		    	if(damageList.containsKey(player.getName())==false)
		    	{
		    		 getLogger().info("Putting "+player.getName()+" into damage map!");
		    		damageList.put(player.getName(), new HashMap<Integer, HashMap<java.lang.String, Float>>());
		    	}
		double damage = event.getDamage();
		if(player.getHealth()<=damage)
		{
			damage=player.getHealth();

		}
		//Bukkit.broadcastMessage(ChatColor.YELLOW +"Damage : "+damage);

		HashMap<Integer, HashMap<java.lang.String, Float>> damage2 =  damageList.get(player.getName());
		//Bukkit.broadcastMessage(ChatColor.YELLOW +"Length : "+damage2.size());

		int nextInt = hash.getNextInt(damage2,Damager.getName());
		//Bukkit.broadcastMessage(ChatColor.YELLOW +"Next int : "+nextInt);
		// float percentage = (float) ((damage/player.getMaxHealth())*100);

		 if(!(damage2.containsKey(nextInt)))
		 {
		 damage2.put(nextInt , new HashMap<java.lang.String, Float>());   
		 }
		 HashMap<java.lang.String, Float>  damagedata = damage2.get(nextInt);
		 float damage1 = (float) damage;
		 if(damagedata.containsKey(Damager.getName())==true)
		 {
			float damageOld = damagedata.get(Damager.getName()); 
			damage1 = (float) (damage+ damageOld);
			//Bukkit.broadcastMessage(ChatColor.YELLOW +"ADKJSAKLDJKL : " +damagedata.containsKey(Damager.getName()));
			damagedata.remove(Damager.getName());
		 }

		 damagedata.put(Damager.getName(), damage1);
	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Damage : "+damagedata.get(Damager.getName()));
	//Xp.dmgtoxp(damage,Damager);
		

		    }
		}
	/*@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		
}
*/
	
	
	    @Override
	    public void onDisable() {
			getLogger().info("onDisable has been invoked!");
			 try{
					SLAPI.save(tier,"tier.bin");
			            }catch(Exception e){
			                 e.printStackTrace();
			            }
			 try{
					SLAPI.save(damageList,"damage.bin");
			            }catch(Exception e){
			                 e.printStackTrace();
			            }
			 try{
					SLAPI.save(canKill,"canKill.bin");
			            }catch(Exception e){
			                 e.printStackTrace();
			            }
	    try{
			SLAPI.save(points,"points.bin");
	            }catch(Exception e){
	                 e.printStackTrace();
	            }
	    
	    try{
			SLAPI.save(game,"game.bin");
	            }catch(Exception e){
	                 e.printStackTrace();
	            }
	    }
}