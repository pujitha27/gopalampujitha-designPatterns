package designpattern;

import java.util.ArrayList; 
import java.util.Iterator; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



interface Subject 
{ 
	public void register_Observer(Observer a); 
	public void unregister_Observer(Observer a); 
	public void notify_Observers(); 
} 

class CricketInfo implements Subject 
{ 
	int runs_taken; 
	int wickets_taken; 
	float overs; 
	ArrayList<Observer> observerList; 

	public CricketInfo() { 
		observerList = new ArrayList<Observer>(); 
	} 

	public void register_Observer(Observer a) { 
		observerList.add(a); 
	} 

	public void unregister_Observer(Observer a) { 
		observerList.remove(observerList.indexOf(a)); 
	} 

	public void notify_Observers() 
	{ 
		for (Iterator<Observer> iter = 
			observerList.iterator(); iter.hasNext();) 
		{ 
			Observer a = iter.next(); 
			a.update(runs_taken,wickets_taken,overs); 
		} 
	} 

	
	private int getLatestRunsTaken() 
	{ 
		
		return 90; 
	} 

	
	private int getLatestWicketsTaken() 
	{ 
		
		return 2; 
	} 

	
	private float getLatestOvers() 
	{ 
		
		return (float)10.2; 
	} 

	
	public void infoChanged() 
	{ 
		
		runs_taken = getLatestRunsTaken(); 
		wickets_taken = getLatestWicketsTaken(); 
		overs = getLatestOvers(); 

		notify_Observers(); 
	} 
} 


interface Observer 
{ 
	public void update(int runs, int wickets, 
					float overs); 
} 

class AvgScoreDisplay implements Observer 
{ 
	public static final Logger LOGGER1=LogManager.getLogger(AvgScoreDisplay.class);
	private float runRate; 
	private int predictedScore; 

	public void update(int runs, int wickets, 
					float overs) 
	{ 
		this.runRate =(float)runs/overs; 
		this.predictedScore = (int)(this.runRate * 50); 
		display(); 
	} 

	public void display() 
	{ 
		LOGGER1.info("\nAverage Score Display: \n"
						+ "Run Rate: " + runRate + 
						"\nPredictedScore: " + 
						predictedScore); 
	} 
} 

class CurrentScoreDisplay implements Observer 
{ 
	public static final Logger LOGGER2=LogManager.getLogger(CurrentScoreDisplay.class);
	private int runs, wickets; 
	private float overs; 

	public void update(int runs, int wickets, 
					float overs) 
	{ 
		this.runs = runs; 
		this.wickets = wickets; 
		this.overs = overs; 
		display(); 
	} 

	public void display() 
	{ 
		LOGGER2.info("\nCurrent Score Display:\n"
						+ "Runs: " + runs + 
						"\nWickets:" + wickets + 
						"\nOvers: " + overs ); 
	} 
} 


public class DesignPattern1
{ 
	public static void main(String args[]) 
	{ 
		
		AvgScoreDisplay averageScoreDisplay = 
						new AvgScoreDisplay(); 
		CurrentScoreDisplay currentScoreDisplay = 
						new CurrentScoreDisplay(); 

		CricketInfo cricketData = new CricketInfo(); 

		
		cricketData.register_Observer(averageScoreDisplay); 
		cricketData.register_Observer(currentScoreDisplay); 

		
		cricketData.infoChanged(); 

		
		cricketData.unregister_Observer(averageScoreDisplay); 

		
		cricketData.infoChanged(); 
	} 
} 
