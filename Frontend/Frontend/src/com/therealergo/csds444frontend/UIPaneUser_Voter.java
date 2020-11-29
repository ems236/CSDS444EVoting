package com.therealergo.csds444frontend;

import com.therealergo.main.Main;
import com.therealergo.main.gl.render.font.FontLayout.HorizontalAlign;
import com.therealergo.main.gl.render.font.FontLayout.Sizing;
import com.therealergo.main.gl.render.font.FontLayout.VerticalAlign;
import com.therealergo.main.gl.render.ui.UIAnimation;
import com.therealergo.main.gl.render.ui.UIAnimation.ETimelineCurve;
import com.therealergo.main.gl.render.ui.UIAnimation.ETimelineMode;
import com.therealergo.main.gl.render.ui.animation.UIAnimationScale;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithColor;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerOrigin;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPixelOffset;
import com.therealergo.main.gl.render.ui.renderer.UIRendererBasic.UIAnimationColorize;
import com.therealergo.main.gl.render.ui.sizer.UISizerFill;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercent;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercentMin;
import com.therealergo.main.math.Vector4F;

public class UIPaneUser_Voter extends UIPaneUser {
	public UIPaneUser_Voter(String name, Vector4F colorText, UIScreenEVoteDemo eVote) {
		super(name, colorText, eVote);
		
		color.set(0.3f, 0.0f, 0.0f, 1.0f);
		setTitleText("VOTER");
		setNextText("Digitize Ballot");
		setExplainText(
				"It looks like you're casting the last vote in the election! " + 
				"Fill out your ballot. When you click \"Digitize Ballot\" it will be " + 
				"converted into a computerized version."
		);
		
		// Ballot where the user puts in their vote
		addChild(
		new UISizerPercentMin(0.9f), 
		new UIPositionerIn.Center(), 
		new UIPaneBallot("ballot")
		);
		
		// Note containing the user's name and voter id
		addChild(
		new UISizerPercentMin(0.4f, 0.1f), 
		new UIPositionerIn.TopRight().add(new UIPositionerPixelOffset(-30.0f, -30.0f)), 
		new UIPaneWithColor("note")
		.color.set(1.0f, 1.0f, 1.0f, 1.0f)
				.addChild(
				new UISizerFill(), 
				new UIPositionerOrigin(), 
				new UIPaneWithText("text")
				.setText("Name: John Smith\nVoter Id: " + App.render.backend.voterId)
				.color.set(0.1f, 0.1f, 0.1f, 1.0f)
				)
		);
		
		// "Digitized" version of their ballot
		addChild(
		new UISizerPercentMin(0.7f, 0.4f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColor("digiballot")
		.color.set(1.0f, 1.0f, 1.0f, 1.0f)
		.setVisible(false)
				.addChild(
				new UISizerPercent(0.8f), 
				new UIPositionerIn.Center(),
				new UIPaneWithText("text")
				.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
				.setTextSizing(Sizing.ScaledToFit(1.0f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.1f, 0.1f, 0.1f, 1.0f)
				)
		);
		
		// "Committed" version of their ballot
		addChild(
		new UISizerPercentMin(0.7f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColor("commitballot")
		.color.set(1.0f, 1.0f, 1.0f, 1.0f)
		.setVisible(false)
				.addChild(
				new UISizerPercent(0.8f), 
				new UIPositionerIn.Center(),
				new UIPaneWithText("text")
				.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
				.setTextSizing(Sizing.FixedSizePercent(0.08f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER, true))
				.color.set(0.1f, 0.1f, 0.1f, 1.0f)
				)
		);
		
		// "Blinded" version of their ballot
		addChild(
		new UISizerPercentMin(0.7f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColor("blindballot")
		.color.set(1.0f, 1.0f, 1.0f, 1.0f)
		.setVisible(false)
				.addChild(
				new UISizerPercent(0.8f), 
				new UIPositionerIn.Center(),
				new UIPaneWithText("text")
				.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
				.setTextSizing(Sizing.FixedSizePercent(0.08f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER, true))
				.color.set(0.1f, 0.1f, 0.1f, 1.0f)
				)
		);
	}

	@Override public void advanceToState(int state) {
		if (state == 0) {
			String ballotData = ((UIPaneBallot)getChild("ballot")).getBallotData();
			App.render.backend.writeBallotData(ballotData);
			((UIPaneWithText)getChild("digiballot>text")).setText(ballotData);
			
			animFadeOut(getChild("ballot"));
			animFadeIn(getChild("digiballot"));
			setNextText("Commit Ballot");
			setExplainText(
					"Now that your ballot can be handled by a computer, we're going to commit it. " + 
					"This signs your vote with your key, proving that YOU (and no one else) cast the vote. "
			);
			
		} else if (state == 1) {
			((UIPaneWithText)getChild("commitballot>text")).setText(App.render.backend.committedUnblindedVote);
			
			animFadeOut(getChild("digiballot"));
			animFadeIn(getChild("commitballot"));
			setNextText("Blind Ballot");
			setExplainText(
					"Now that we have your committed ballot, we're going to blind it. " + 
					"This process uses your secret key to ensure that no one can see who you've voted for " + 
					"(until you use that same key to unblind the vote)."
			);
			
		} else if (state == 2) {
			((UIPaneWithText)getChild("blindballot>text")).setText(App.render.backend.committedBlindedVote);
			
			animFadeOut(getChild("commitballot"));
			animFadeIn(getChild("blindballot"));
			setNextText("Send Ballot");
			setExplainText(
					"With your ballot blinded, who you voted for is now completely secret. Just try to read that text! " + 
					"You must now send that blinded ballot data to the administrator, " + 
					"Alongside any verification information like name and voter id. "
			);
		
		} else if (state == 3) {

			animFadeOut(getChild("blindballot"));
			//TODO: Pulse animation proper
			getParentPane().getParentPane().getChild("left>a").playAnimation(new UIAnimation(
					ETimelineMode.PLAY_ONCE_AND_STOP, ETimelineCurve.SMOOTH_END, 2.0f, 
					new UIAnimationColorize(new Vector4F(1.5f, 1.5f, 1.5f, 1.0f), new Vector4F(1.0f, 1.0f, 1.0f, 1.0f)),
					new UIAnimationScale(1.4f, 1.0f)
			));
			setNextText(null);
			setExplainText("");
		}
	}
	
	@Override protected boolean shouldAdvance(int state) {
		if (state == 0) {
			return ((UIPaneBallot)getChild("ballot")).isValid();
		} else {
			return true;
		}
	}
}
