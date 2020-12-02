package com.therealergo.csds444frontend;

import com.therealergo.main.Main;
import com.therealergo.main.gl.ContextRender;
import com.therealergo.main.gl.render.loading.UIScreenLoadingMain;
import com.therealergo.main.task.Task;

public class AppContextRender extends ContextRender {
	public BackendInterface backend;
	public UIScreenEVoteDemo screen;
	
	@Override protected final void glInit() {
		// Set window title
		Main.gl.window.setTitle("CSDS444 e-Voting Demo");
		
		// Log starting
		Main.log.log("Loading Application[title=\"" + Main.gl.window.getTitle() + "\"]");
		Main.log.addTabLevel();
		
		// Start loading screen
		Main.gl.enableLoadTicking();
		new UIScreenLoadingMain().activate();

		// Initialize OpenGL settings
		Main.gl.window.setSwapInterval(1);
		  
		// Load in all of our resources
		Main.tree.load();
		
		// Start up the backend
		backend = new BackendInterface();
		
		// Start up our UI
		screen = (UIScreenEVoteDemo) Main.gl.render.ui.addScreen(new UIScreenEVoteDemo("evotedemo"));
		
		// Hot-reload our UI for easy prototyping
		Main.gl.render.debug.getTaskReloadShaders().addChild(new Task<Task<?>>(0.0f){
			public void tick() {
				Main.gl.render.ui.removeScreen(screen);
				screen = (UIScreenEVoteDemo) Main.gl.render.ui.addScreen(new UIScreenEVoteDemo("evotedemo"));
			}
		});
		
		// End loading screen
		Main.gl.disableLoadTicking();
		
		// Log completion
		Main.log.log("Completed Application");
		Main.log.removeTabLevel();
	}
	
	@Override protected final void glTickPreRenderer() {
	}
	
	@Override protected final void glTickPostRenderer() {
	}
	
	@Override protected final void glStop() {
		
		// Ensure that the backend is shutdown when we are
		if (backend != null) {
			backend.kill();
		}
	}
}
