package com.gmail.louisdw1.louis;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
public class hash {
	public HashMap<Integer, HashMap<java.lang.String, Float>> damage2 = new HashMap<Integer, HashMap<java.lang.String, Float>>();
	public HashMap<java.lang.String, Float> damagedata = new HashMap<java.lang.String, Float>();

public static int getNextInt(HashMap damage2, String damager)
{
	int nextInt = 0;
	
	ArrayList<Integer> intKeys = new ArrayList<Integer>(damage2.keySet());
	if (!intKeys.isEmpty()) {
		int i = 0;
		//int f = 0;
		//int s = 0;
		while(intKeys.size()!=i)
		{
			HashMap damagedata = (HashMap) damage2.get(i);
			if(damagedata.containsKey(damager))
			{
				return i;
			}
			i++;
		}
		
		 nextInt = intKeys.size();
		 //Bukkit.broadcastMessage("Last key: "+intKeys.get(intKeys.size()-1));
		}
	
return nextInt;	
}

}
