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
import com.therealergo.main.gl.render.ui.animation.UIAnimationScale;
import com.therealergo.main.gl.render.ui.pane.UIPaneButton;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerOrigin;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPixelOffset;
import com.therealergo.main.gl.render.ui.renderer.UIRendererBasic.UIAnimationColorize;
import com.therealergo.main.gl.render.ui.renderer.UIRendererBasic.UIAnimationFade;
import com.therealergo.main.gl.render.ui.sizer.UISizerFill;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercentMin;
import com.therealergo.main.math.Vector4F;

public abstract class UIPaneUser extends UIPaneWithColorShadow {
	public UIPaneUser(String name, Vector4F colorText, UIScreenEVoteDemo eVote) {
		super(name);
		
		// Title area at top
		addChild(
		new UISizerPercentMin(0.8f, 0.1f), 
		new UIPositionerIn.TopLeft().add(new UIPositionerPixelOffset(30.0f, -15.0f)), 
		new UIPaneWithText("title")
		.setTextSizing(Sizing.VerticalFit(0.8f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.TOP))
		.color.set(colorText)
		);
		
		// Explanation area at bottom
		addChild(
		new UISizerPercentMin(0.45f, 0.5f), 
		new UIPositionerIn.BottomLeft().add(new UIPositionerPixelOffset(30.0f, 15.0f)), 
		new UIPaneWithText("explain")
		.setTextSizing(Sizing.FixedSizePixel(20.0f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.BOTTOM, true))
		.color.set(colorText)
		);
		
		// Button that actually advances this <s>slideshow</s> UI at bottom right
		addChild(
		new UISizerPercentMin(0.4f, 0.1f), 
		new UIPositionerIn.BottomRight().add(new UIPositionerPixelOffset(-30.0f, 30.0f)), 
		new UIPaneButton("next")      { protected void performAction() { eVote.advanceState(UIPaneUser.this); } }
				.addChild(
				new UISizerFill(), 
				new UIPositionerOrigin(), 
				new UIPaneWithColorRoundedShadow("vis")
				.color.set(0.3f, 0.3f, 0.3f, 1.0f)
				)
				.addChild(
				new UISizerFill(), 
				new UIPositionerOrigin(), 
				new UIPaneWithText("text")
				.color.set(colorText)
				)
		);
	}

	protected final void setTitleText(String text) {
		((UIPaneWithText)getChild("title")).setText(text);
	}
	
	protected final void setNextText(String text) {
		if (text == null) {
			getChild("next").setVisible(false);
		} else {
			getChild("next").setVisible(true);
			((UIPaneWithText)getChild("next>text")).setText(text);
		}
	}
	
	protected final void setExplainText(String text) {
		((UIPaneWithText)getChild("explain")).setText(text);
	}
	
	protected final void animFadeIn(UIPane pane) {
		pane.setVisible(true).playAnimation(new UIAnimation(
				ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_END, 2.0f, 
				new UIAnimationFade(0.0f, 1.0f),
				new UIAnimationScale(0.5f, 1.0f)
		));
	}
	
	protected final void animFadeOut(UIPane pane) {
		pane.playAnimation(new UIAnimation(
				ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_START, 2.0f, 
				new UIAnimationFade(1.0f, -1.0f),
				new UIAnimationScale(1.0f, 0.0f),
				new UIAnimationHideAtEnd()
		));
	}
	
	protected final void animPopUser(String user) {
		getParentPane().getParentPane().getChild("left>" + user).playAnimation(new UIAnimation(
				ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_END, 2.0f, 
				new UIAnimationColorize(new Vector4F(1.5f, 1.5f, 1.5f, 1.0f), new Vector4F(1.0f, 1.0f, 1.0f, 1.0f)),
				new UIAnimationScale(1.4f, 1.0f)
		));
	}
	
	public abstract void advanceToState(int state);
	
	protected boolean shouldAdvance(int state) {
		return true;
	}
	
	private static class UIAnimationHideAtEnd extends Action {
		@Override protected void renderPost(GeometrySimple2D arg0, UIPane arg1, float arg2) { }
		
		@Override protected void renderPre(GeometrySimple2D arg0, UIPane arg1, float arg2) {
			if (arg2 == 1.0f) {
				arg1.setVisible(false);
			}
		}
	}
}
