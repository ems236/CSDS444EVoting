package com.therealergo.csds444frontend;

import com.therealergo.main.Main;
import com.therealergo.main.gl.ContextRender;
import com.therealergo.main.gl.render.loading.UIScreenLoadingMain;

public class AppContextRender extends ContextRender {
	@Override protected final void glInit() {
		// Set window title
		Main.gl.window.setTitle("Lorenz Attractor Viewer");
		
		// Log starting
		Main.log.log("Loading Application[title=\"" + Main.gl.window.getTitle() + "\"]");
		Main.log.addTabLevel();
		
		// Start loading framebuffer
		Main.gl.enableLoadTicking();
		new UIScreenLoadingMain().activate();

		// Initialize OpenGL settings
		Main.gl.window.setSwapInterval(1);
		  
		// Load in all of our resources
		Main.tree.load();
		
		// End loading framebuffer
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
	}
}
