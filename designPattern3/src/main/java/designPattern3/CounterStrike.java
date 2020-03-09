package designPattern3;


import java.util.Random; 
import java.util.HashMap; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




interface Player 
{ 
	public void assignWeapon(String weapon); 
	public void mission(); 
} 
class Terrorist implements Player 
{ 
	public static final Logger LOGGER1=LogManager.getLogger(Terrorist.class);
	private final String TASK; 

	
	private String weapon; 

	public Terrorist() 
	{ 
		TASK = "PLANT A BOMB"; 
	} 
	public void assignWeapon(String weapon) 
	{ 
		
		this.weapon = weapon; 
	} 
	public void mission() 
	{ 
		
		LOGGER1.info("Terrorist with weapon "
						+ weapon + "|" + " Task is " + TASK); 
	} 
} 


class CounterTerrorist implements Player 
{ 
	public static final Logger LOGGER2=LogManager.getLogger(CounterTerrorist.class);
	private final String TASK; 

	
	private String weapon; 

	public CounterTerrorist() 
	{ 
		TASK = "DIFFUSE BOMB"; 
	} 
	public void assignWeapon(String weapon) 
	{ 
		this.weapon = weapon; 
	} 
	public void mission() 
	{ 
		LOGGER2.info("Counter Terrorist with weapon "
						+ weapon + "|" + " Task is " + TASK); 
	} 
} 

class PlayerFactory 
{ 
	public static final Logger LOGGER3=LogManager.getLogger(PlayerFactory.class);
	private static HashMap <String, Player> hm = 
						new HashMap<String, Player>(); 

	
	public static Player getPlayer(String type) 
	{ 
		Player p = null; 

		
		if (hm.containsKey(type)) 
				p = hm.get(type); 
		else
		{ 
			
			switch(type) 
			{ 
			case "Terrorist": 
				LOGGER3.info("Terrorist Created"); 
				p = new Terrorist(); 
				break; 
			case "CounterTerrorist": 
				LOGGER3.info("Counter Terrorist Created"); 
				p = new CounterTerrorist(); 
				break; 
			default : 
				LOGGER3.info("Unreachable code!"); 
			} 

			 
			hm.put(type, p); 
		} 
		return p; 
	} 
} 


public class CounterStrike 
{ 
	
	private static String[] playerType = 
					{"Terrorist", "CounterTerrorist"}; 
	private static String[] weapons = 
	{"AK-47", "Maverick", "Gut Knife", "Desert Eagle"}; 


	
	public static void main(String args[]) 
	{ 
		
		for (int i = 0; i < 10; i++) 
		{ 
			
			Player p = PlayerFactory.getPlayer(getRandPlayerType()); 

			
			p.assignWeapon(getRandWeapon()); 

			
			p.mission(); 
		} 
	} 

	
	public static String getRandPlayerType() 
	{ 
		Random r = new Random(); 

		
		int randInt = r.nextInt(playerType.length); 

		
		return playerType[randInt]; 
	} 
	public static String getRandWeapon() 
	{ 
		Random r = new Random(); 

		
		int randInt = r.nextInt(weapons.length); 

		
		return weapons[randInt]; 
	} 
} 

