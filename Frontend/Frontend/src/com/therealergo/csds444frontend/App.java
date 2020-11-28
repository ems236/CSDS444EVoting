package com.therealergo.csds444frontend;

import com.therealergo.main.Main;

public class App {
	public static AppContextRender render;
	
	public static final void main(String[] args) throws Throwable {
		try {
			Thread.currentThread().setName("Loader");
			
			// Stupid Mac compatibility wrapper
			if (Main.restartJVMOnFirstThread(true, args)) { return; }
			
			render = new AppContextRender();
			
			Main.mainInit(App.class, args, "MainGL.numScreenMultisamples=8", 
										   "MainGL.isScreenModeSRGB=true", 
										   "MainGL.isForwardCompat=false", 
										   "Main.isDebug=true");
			
			Main.gl.glInit(render, true);
		} catch (Throwable t) {
			Main.error.setError();
			Main.log.logErr(t);
		}
	}
}
