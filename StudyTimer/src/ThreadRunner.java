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
//		BlackListEditor blackListEditor = new BlackListEditor();
//		try {
//			ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "src/RunBlock.sh");
//			ProcessBuilder processBuilder = new ProcessBuilder("osascript", "do shell script", "\"echo '127.0.0.1 www.facebook.com' >> /etc/hosts $*\"", "with administrator privileges");
//			processBuilder.start();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}

		studyMode = true;
		long timeDiff = 0;
    	long startTime = System.nanoTime();
    	mainMenu.startButton.setDisable(true);
    	long totalTime = mainMenu.totalTime;
    	for(;timeDiff < totalTime;) {
    		try {
				if(studyMode) {
					Thread.sleep(25*60000);
				} else {
					Thread.sleep(5*60000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
    		
    		long endTime = System.nanoTime();
    		timeDiff = endTime - startTime;
    		TimeUnit timeUnit = TimeUnit.NANOSECONDS;
    		timeDiff = timeUnit.toMinutes(timeDiff);

    		System.out.println(timeDiff);
    		if(timeDiff >= totalTime) break;
    		if(studyMode) {
    			try {
    				Runtime.getRuntime().exec(new String[] {"osascript", "-e", takeABreakCommand});
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
//    			try {
//					blackListEditor.deleteHosts();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
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
    	}
	}

}
