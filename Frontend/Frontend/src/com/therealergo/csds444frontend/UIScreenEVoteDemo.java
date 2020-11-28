package com.therealergo.csds444frontend;

import com.therealergo.main.Main;
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
import com.therealergo.main.gl.render.ui.animation.UIAnimationScale;
import com.therealergo.main.gl.render.ui.pane.UIPaneButton;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithColor;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerInterpolate;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerOrigin;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPercent;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPercentOffset;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPixelOffset;
import com.therealergo.main.gl.render.ui.renderer.UIRendererBasic;
import com.therealergo.main.gl.render.ui.renderer.UIRendererBasic.UIAnimationColorize;
import com.therealergo.main.gl.render.ui.renderer.UIRendererBasic.UIAnimationFade;
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
		super(name, new UIRendererBasic(), 0.0f, false);
		
		Vector4F colorText = new Vector4F(1.00f, 0.80f, 0.20f, 1.0f);
		
		// Dark grey background behind everything
		addChild(
		new UISizerFullScreen(), 
		new UIPositionerOrigin(), 
		new UIPaneWithColor("background")
		.color.set(0.1f, 0.1f, 0.1f, 1.0f)
		);
		
		addChild(
		new UISizerPercentMin(0.15f, 0.0f).add(new UISizerPercent(0.0f, 1.0f)), 
		new UIPositionerOrigin(), 
		new UIPaneWithColor("left")
		.color.set(0.2f, 0.2f, 0.2f, 1.0f)
				.addChild(
				new UISizerPercentMin(0.75f), 
				new UIPositionerPercent(0.5f, 0.75f), 
				new UIPaneButton("v")      { protected void performAction() { switchToTab(paneV, paneVPos); } }
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithColor("vis")
						.color.set(0.3f, 0.3f, 0.3f, 1.0f)
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
						new UIPaneWithColor("vis")
						.color.set(0.3f, 0.3f, 0.3f, 1.0f)
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
						new UIPaneWithColor("vis")
						.color.set(0.3f, 0.3f, 0.3f, 1.0f)
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
		
		addChild(
		new UISizerFullScreen().add(new UISizerPercentMin(-0.15f, 0.0f)), 
		new UIPositionerIn.CenterRight(), 
		new UIPane("right")
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
				paneV    = new UIPaneWithColor("v")
				.color.set(0.3f, 0.0f, 0.0f, 1.0f)
					
						// Ballot where the user puts in their vote
						.addChild(
						new UISizerPercentMin(0.9f), 
						new UIPositionerIn.Center(), 
						new UIPaneBallot("ballot")
						)
						
						// "Digitized" version of their ballot
						.addChild(
						new UISizerPercentMin(0.7f), 
						new UIPositionerIn.Center(), 
						new UIPaneWithColor("digiballot")
						.color.set(0.3f, 0.3f, 0.3f, 1.0f)
						.setVisible(false)
								.addChild(
								new UISizerPercent(0.8f), 
								new UIPositionerIn.Center(),
								new UIPaneWithText("text")
								.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
								.color.set(colorText)
								)
						)
						
						// "Blinded" version of their ballot
						.addChild(
						new UISizerPercentMin(0.7f), 
						new UIPositionerIn.Center(), 
						new UIPaneWithColor("blindballot")
						.color.set(0.3f, 0.3f, 0.3f, 1.0f)
						.setVisible(false)
								.addChild(
								new UISizerPercent(0.8f), 
								new UIPositionerIn.Center(),
								new UIPaneWithText("text")
								.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
								.color.set(colorText)
								)
						)
						
						.addChild(
						new UISizerPercentMin(0.4f, 0.1f), 
						new UIPositionerIn.TopLeft().add(new UIPositionerPixelOffset(30.0f, -15.0f)), 
						new UIPaneWithText("text")
						.setText("VOTER")
						.setTextSizing(Sizing.VerticalFit(0.8f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.TOP))
						.color.set(colorText)
						)
						.addChild(
						new UISizerPercentMin(0.4f, 0.1f), 
						new UIPositionerIn.BottomRight().add(new UIPositionerPixelOffset(-30.0f, 30.0f)), 
						new UIPaneButton("next")      { protected void performAction() { advanceState(); } }
								.addChild(
								new UISizerFill(), 
								new UIPositionerOrigin(), 
								new UIPaneWithColor("vis")
								.color.set(0.3f, 0.3f, 0.3f, 1.0f)
								)
								.addChild(
								new UISizerFill(), 
								new UIPositionerOrigin(), 
								new UIPaneWithText("text")
								.setText("Digitize Ballot")
								.color.set(colorText)
								)
						)
				)
				
				// "ADMINISTRATOR" pane
				.addChild(
				new UISizerFill(), 
				paneAPos = new UIPositionerInterpolate(0.0f, new UIPositionerOrigin().add(new UIPositionerPercentOffset(1.0f, 0.0f)), new UIPositionerOrigin()), 
				paneA    = new UIPaneWithColor("a")
				.color.set(0.0f, 0.3f, 0.0f, 1.0f)
						.addChild(
						new UISizerFill(), 
						new UIPositionerIn.TopLeft().add(new UIPositionerPixelOffset(30.0f, -15.0f)), 
						new UIPaneWithText("text")
						.setText("ADMINISTRATOR")
						.setTextSizing(Sizing.FixedSizePixel(60.0f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.TOP, false))
						.color.set(colorText)
						)
				)
				
				// "COLLECTOR" pane
				.addChild(
				new UISizerFill(), 
				paneCPos = new UIPositionerInterpolate(0.0f, new UIPositionerOrigin().add(new UIPositionerPercentOffset(1.0f, 0.0f)), new UIPositionerOrigin()), 
				paneC    = new UIPaneWithColor("c")
				.color.set(0.0f, 0.0f, 0.3f, 1.0f)
						.addChild(
						new UISizerFill(), 
						new UIPositionerIn.TopLeft().add(new UIPositionerPixelOffset(30.0f, -15.0f)), 
						new UIPaneWithText("text")
						.setText("COLLECTOR")
						.setTextSizing(Sizing.FixedSizePixel(60.0f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.TOP, false))
						.color.set(colorText)
						)
				)
		);
	}

	private void switchToTab(UIPane pane, UIPositionerInterpolate panePos) {
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
		
		pane.playAnimation(new UIAnimation(
				ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_ALL, 2.0f, 
				new UIAnimationPositionerInterpolate(panePos, false)
		).seekTo(panePos.interp));
	}
	
	private void advanceState() {
		if (state == 0) {
			String ballotData = ((UIPaneBallot)getChild("right>v>ballot")).getBallotData();
			App.render.backend.writeBallotData(ballotData);
			((UIPaneWithText)getChild("right>v>digiballot>text")).setText(ballotData);
			
			getChild("right>v>ballot").playAnimation(new UIAnimation(
					ETimelineMode.PLAY_ONCE, ETimelineCurve.SMOOTH_START, 2.0f, 
					new UIAnimationFade(1.0f, -1.0f),
					new UIAnimationScale(1.0f, 0.0f)
			));
			getChild("right>v>digiballot").setVisible(true).playAnimation(new UIAnimation(
					ETimelineMode.PLAY_ONCE, ETimelineCurve.SMOOTH_END, 2.0f, 
					new UIAnimationFade(0.0f, 1.0f),
					new UIAnimationScale(0.5f, 1.0f)
			));
			((UIPaneWithText)getChild("right>v>next>text")).setText("Blind Ballot");
			
		} else if (state == 1) {
			((UIPaneWithText)getChild("right>v>blindballot>text")).setText(App.render.backend.blindedCommittedVote);
			
			getChild("right>v>digiballot").playAnimation(new UIAnimation(
					ETimelineMode.PLAY_ONCE, ETimelineCurve.SMOOTH_START, 2.0f, 
					new UIAnimationFade(1.0f, -1.0f),
					new UIAnimationScale(1.0f, 0.0f)
			));
			getChild("right>v>blindballot").setVisible(true).playAnimation(new UIAnimation(
					ETimelineMode.PLAY_ONCE, ETimelineCurve.SMOOTH_END, 2.0f, 
					new UIAnimationFade(0.0f, 1.0f),
					new UIAnimationScale(0.5f, 1.0f)
			));
			((UIPaneWithText)getChild("right>v>next>text")).setText("Send Ballot");
		
		} else if (state == 2) {
			getChild("right>v>blindballot").playAnimation(new UIAnimation(
					ETimelineMode.PLAY_ONCE, ETimelineCurve.SMOOTH_START, 2.0f, 
					new UIAnimationFade(1.0f, -1.0f),
					new UIAnimationScale(1.0f, 0.0f)
			));
			getChild("left>a").playAnimation(new UIAnimation(
					ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_END, 2.0f, 
					new UIAnimationColorize(new Vector4F(1.5f, 1.5f, 1.5f, 1.0f), new Vector4F(1.0f, 1.0f, 1.0f, 1.0f)),
					new UIAnimationScale(1.4f, 1.0f)
			));
			getChild("right>v>next").setVisible(false);
		}
		state++;
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
