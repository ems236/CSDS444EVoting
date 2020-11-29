package com.therealergo.csds444frontend;

import com.therealergo.main.gl.render.font.FontLayout.HorizontalAlign;
import com.therealergo.main.gl.render.font.FontLayout.Sizing;
import com.therealergo.main.gl.render.font.FontLayout.VerticalAlign;
import com.therealergo.main.gl.render.simple2D.GeometrySimple2D;
import com.therealergo.main.gl.render.ui.UIAnimation;
import com.therealergo.main.gl.render.ui.UIAnimation.Action;
import com.therealergo.main.gl.render.ui.UIAnimation.ETimelineCurve;
import com.therealergo.main.gl.render.ui.UIAnimation.ETimelineMode;
import com.therealergo.main.gl.render.ui.UIPane;
import com.therealergo.main.gl.render.ui.UIScreen;
import com.therealergo.main.gl.render.ui.pane.UIPaneButton;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithColor;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerInterpolate;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerOrigin;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPercent;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPercentOffset;
import com.therealergo.main.gl.render.ui.sizer.UISizerFill;
import com.therealergo.main.gl.render.ui.sizer.UISizerFullScreen;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercent;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercentMin;
import com.therealergo.main.math.Vector4F;

public class UIScreenEVoteDemo extends UIScreen {
	private UIPane                  paneV;
	private UIPositionerInterpolate paneVPos;
	private UIPane                  paneA;
	private UIPositionerInterpolate paneAPos;
	private UIPane                  paneC;
	private UIPositionerInterpolate paneCPos;
	
	private int state = 0;
	
	public UIScreenEVoteDemo(String name) {
		super(name, new UIRendererEVoteDemo(), 0.0f, false);
		
		Vector4F colorText = new Vector4F(1.0f, 1.0f, 1.0f, 0.8f);
		
		// Dark grey background behind everything
		addChild(
		new UISizerFullScreen(), 
		new UIPositionerOrigin(), 
		new UIPaneWithColor("background")
		.color.set(0.1f, 0.1f, 0.1f, 1.0f)
		);
		
		// Left-hand pane
		addChild(
		new UISizerPercentMin(0.15f, 0.0f).add(new UISizerPercent(0.0f, 1.0f)), 
		new UIPositionerOrigin(), 
		new UIPaneWithColorShadow("left")
		.color.set(0.2f, 0.2f, 0.2f, 1.0f)
				.addChild(
				new UISizerPercentMin(0.75f), 
				new UIPositionerPercent(0.5f, 0.75f), 
				new UIPaneButton("v")      { protected void performAction() { switchToTab(paneV, paneVPos); } }
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithColorRoundedShadow("vis")
						.color.set(0.3f, 0.0f, 0.0f, 1.0f)
								.addChild(
								new UISizerFill(), 
								new UIPositionerOrigin(), 
								new UIPaneWithText("text")
								.setText("V")
								.color.set(colorText)
								)
						)
				)
				.addChild(
				new UISizerPercentMin(0.75f), 
				new UIPositionerPercent(0.5f, 0.50f), 
				new UIPaneButton("a")      { protected void performAction() { switchToTab(paneA, paneAPos); } }
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithColorRoundedShadow("vis")
						.color.set(0.0f, 0.3f, 0.0f, 1.0f)
								.addChild(
								new UISizerFill(), 
								new UIPositionerOrigin(), 
								new UIPaneWithText("text")
								.setText("A")
								.color.set(colorText)
								)
						)
				)
				.addChild(
				new UISizerPercentMin(0.75f), 
				new UIPositionerPercent(0.5f, 0.25f), 
				new UIPaneButton("c")      { protected void performAction() { switchToTab(paneC, paneCPos); } }
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithColorRoundedShadow("vis")
						.color.set(0.0f, 0.0f, 0.3f, 1.0f)
								.addChild(
								new UISizerFill(), 
								new UIPositionerOrigin(), 
								new UIPaneWithText("text")
								.setText("C")
								.color.set(colorText)
								)
						)
				)
		);
		
		// Right hand pane
		addChild(
		new UISizerFullScreen().add(new UISizerPercentMin(-0.15f, 0.0f)), 
		new UIPositionerIn.CenterRight(), 
		new UIPane("right")
		
				// Helpful explain-y text behind all of the panes
				.addChild(
				new UISizerFill(), 
				new UIPositionerOrigin(), 
				new UIPaneWithText("text")
				.setText("Select a user to get started")
				.setTextSizing(Sizing.FixedSizePixel(15.0f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER, false))
				.color.set(colorText)
				)
				
				// "VOTER" pane
				.addChild(
				new UISizerFill(), 
				paneVPos = new UIPositionerInterpolate(0.0f, new UIPositionerOrigin().add(new UIPositionerPercentOffset(1.0f, 0.0f)), new UIPositionerOrigin()), 
				paneV    = new UIPaneUser_Voter("v", colorText, this)
				)
				
				// "ADMINISTRATOR" pane
				.addChild(
				new UISizerFill(), 
				paneAPos = new UIPositionerInterpolate(0.0f, new UIPositionerOrigin().add(new UIPositionerPercentOffset(1.0f, 0.0f)), new UIPositionerOrigin()), 
				paneA    = new UIPaneUser_Administrator("a", colorText, this)
				)
				
				// "COUNTER" pane
				.addChild(
				new UISizerFill(), 
				paneCPos = new UIPositionerInterpolate(0.0f, new UIPositionerOrigin().add(new UIPositionerPercentOffset(1.0f, 0.0f)), new UIPositionerOrigin()), 
				paneC    = new UIPaneUser_Counter("c", colorText, this)
				)
		);

		// Start with everything setup for state 0
		// Can be used while hot-reload debugging to start at a different state
		state = 0;
		((UIPaneUser)getChild("right>v")).advanceToState(state);
		((UIPaneUser)getChild("right>a")).advanceToState(state);
		((UIPaneUser)getChild("right>c")).advanceToState(state);
	}

	private void switchToTab(UIPane pane, UIPositionerInterpolate panePos) {
		
		// Animate away all tabs
		paneV.playAnimation(new UIAnimation(
				ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_ALL, 2.0f, 
				new UIAnimationPositionerInterpolate(paneVPos, true)
		).seekTo(1.0f - paneVPos.interp));
		paneA.playAnimation(new UIAnimation(
				ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_ALL, 2.0f, 
				new UIAnimationPositionerInterpolate(paneAPos, true)
		).seekTo(1.0f - paneAPos.interp));
		paneC.playAnimation(new UIAnimation(
				ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_ALL, 2.0f, 
				new UIAnimationPositionerInterpolate(paneCPos, true)
		).seekTo(1.0f - paneCPos.interp));
		
		// Animate in the selected tab
		pane.playAnimation(new UIAnimation(
				ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_ALL, 2.0f, 
				new UIAnimationPositionerInterpolate(panePos, false)
		).seekTo(panePos.interp));
	}
	
	public void advanceState(UIPaneUser user) {
		if (user.shouldAdvance(state)) {
			state++;
			((UIPaneUser)getChild("right>v")).advanceToState(state);
			((UIPaneUser)getChild("right>a")).advanceToState(state);
			((UIPaneUser)getChild("right>c")).advanceToState(state);
		}
	}
	
	public static class UIAnimationPositionerInterpolate extends Action {
		private UIPositionerInterpolate pos;
		private boolean reverse;

		public UIAnimationPositionerInterpolate(UIPositionerInterpolate pos, boolean reverse) {
			this.pos = pos;
			this.reverse = reverse;
		}
		
		@Override protected void renderPre(GeometrySimple2D arg0, UIPane arg1, float arg2) {
			pos.interp = reverse ? 1.0f - arg2 : arg2;
		}
		
		@Override protected void renderPost(GeometrySimple2D arg0, UIPane arg1, float arg2) {
		}
	}
}
