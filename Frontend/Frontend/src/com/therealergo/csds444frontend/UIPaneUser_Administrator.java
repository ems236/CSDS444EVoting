package com.therealergo.csds444frontend;

import com.therealergo.main.Main;
import com.therealergo.main.gl.render.font.FontLayout.HorizontalAlign;
import com.therealergo.main.gl.render.font.FontLayout.Sizing;
import com.therealergo.main.gl.render.font.FontLayout.VerticalAlign;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPercentOffset;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPixelOffset;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercent;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercentMin;
import com.therealergo.main.math.Vector4F;

public class UIPaneUser_Administrator extends UIPaneUser {
	public UIPaneUser_Administrator(String name, Vector4F colorText, UIScreenEVoteDemo eVote) {
		super(name, colorText, eVote);
		
		color.set(0.0f, 0.3f, 0.0f, 1.0f);
		setTitleText("ADMINISTRATOR");
		setNextText(null);
		
		// Voter registry
		addChild(
		new UISizerPercentMin(0.4f, 0.7f), 
		new UIPositionerIn.CenterRight().add(new UIPositionerPixelOffset(-30.0f, 0.0f)), 
		new UIPaneVoterRegistry("registry")
		);
		
		// "Blinded" version of their ballot
		addChild(
		new UISizerPercentMin(0.7f), 
		new UIPositionerIn.CenterLeft().add(new UIPositionerPixelOffset(30.0f, 0.0f)), 
		new UIPaneWithColorShadow("blindballot")
		.color.set(1.0f, 1.0f, 1.0f, 1.0f)
		.setVisible(false)
				.addChild(
				new UISizerPercent(0.8f), 
				new UIPositionerIn.TopCenter(),
				new UIPaneWithText("meta")
				.setText("John Smith\n1101 Example St.\nId: " + App.render.backend.voterId)
				.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
				.setTextSizing(Sizing.FixedSizePercent(0.08f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.TOP, true))
				.color.set(1.0f, 0.1f, 0.1f, 1.0f)
				)
				.addChild(
				new UISizerPercent(0.8f), 
				new UIPositionerIn.Center().add(new UIPositionerPercentOffset(0.0f, -0.1f)),
				new UIPaneWithText("text")
				.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
				.setTextSizing(Sizing.FixedSizePercent(0.08f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER, true))
				.color.set(0.1f, 0.1f, 0.1f, 1.0f)
				)
		);
		
		// Admin signature
		addChild(
		new UISizerPercentMin(0.8f, 0.3f), 
		new UIPositionerIn.CenterLeft().add(new UIPositionerPixelOffset(30.0f, 0.0f)), 
		new UIPaneWithColorShadow("adminsig")
		.color.set(1.0f, 1.0f, 1.0f, 1.0f)
		.setVisible(false)
				.addChild(
				new UISizerPercent(0.9f), 
				new UIPositionerIn.Center(),
				new UIPaneWithText("text")
				.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
				.setTextSizing(Sizing.FixedSizePercent(0.124f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER, true))
				.color.set(1.0f, 0.1f, 0.1f, 1.0f)
				)
		);
	}

	@Override public void advanceToState(int state) {
		if (state == 4) {
			((UIPaneWithText)getChild("blindballot>text")).setText(App.render.backend.committedBlindedVote);
			animFadeIn(getChild("blindballot"));
			setNextText("Check");
			setExplainText(
					"When the administrator receives the ballot, " + 
			        "they first check whether the voter is eligible to vote. " + 
					"If they are, the administrator then marks their name in the voter rolls. " + 
			        "This ensures that one person cannot vote multiple times."
			);
		
		} else if (state == 5) {
			((UIPaneVoterRegistry)getChild("registry")).finish();
			setNextText("Sign");
			setExplainText(
					"With the voter verified, the administrator can now sign the voter's blinded ballot. " + 
			        "This process uses the administrator's private key to create a signature for the blinded ballot. " + 
					"This signature was provably produced by the administrator and no one else."
			);
			
		} else if (state == 6) {
			((UIPaneWithText)getChild("adminsig>text")).setText(App.render.backend.adminBlindSignature);
			animFadeOut(getChild("blindballot"));
			animFadeIn(getChild("adminsig"));
			setNextText("Send");
			setExplainText(
					"The administrator will now send their blinded signature back to the voter. " + 
			        "The administrator has provably accepted the vote without ever seeing the vote's actual contents."
			);
			
		} else if (state == 7) {
			animFadeOut(getChild("adminsig"));
			animPopUser("v");
			setNextText(null);
			setExplainText("");
		}
	}
}
