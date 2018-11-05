import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ThreadRunner implements Runnable {
	
	private MainMenu mainMenu;
	
	private boolean studyMode;
	
	public ThreadRunner(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
	
	@Override
	public void run() {
		String takeABreakCommand = "display notification \"Relax\"" + 
				   				   "with title \"Take a Break From Studying\"" + 
				   				   "subtitle \"Break over in 5 minutes\"" +
				   				   "sound name \"Ping.aiff\"";
		String returnToStudyCommand = "display notification \"Start with a short revision\"" + 
			  		  				  "with title \"Resume Normal Study\"" + 
			  		  				  "subtitle \"Next break in 25 minutes\"" + 
			  		  				  "sound name \"Ping.aiff\"";
		String studyComplete = "display notification \"Take a short break before another study session\"" +
				   			   "with title \"Study Session Complete\"" +
				   			   "subtitle \"2 hour study session complete\"" + 
				   			   "sound name \"Ping.aiff\"";
		studyMode = true;
		long timeDiff = 0;
    	long startTime = System.nanoTime();
    	mainMenu.startButton.setDisable(true);
    	long totalTime = mainMenu.totalTime;
    	for(;timeDiff < totalTime;) {
    		long endTime = System.nanoTime();
    		timeDiff = endTime - startTime;
    		TimeUnit timeUnit = TimeUnit.NANOSECONDS;
    		timeDiff = timeUnit.toMinutes(timeDiff);
    		
    		try {
				if(studyMode) {
					Thread.sleep(1000);
				} else {
					Thread.sleep(10000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    		if(studyMode) {
    			try {
    				Runtime.getRuntime().exec(new String[] {"osascript", "-e", takeABreakCommand});
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			studyMode = false;
    		} else {
    			try {
    				Runtime.getRuntime().exec(new String[] {"osascript", "-e", returnToStudyCommand});
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			studyMode = true;
    		}
    		
    	}
    	
    	
    	if(timeDiff == totalTime) {
    		mainMenu.startButton.setDisable(false);
    		try {
				Runtime.getRuntime().exec(new String[] {"osascript", "-e", studyComplete});
			} catch (IOException e) {
				e.printStackTrace();
			}
    		return;
    	}
	}

}
