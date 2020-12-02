package com.therealergo.csds444frontend;

import com.therealergo.main.Main;
import com.therealergo.main.gl.render.font.FontLayout.HorizontalAlign;
import com.therealergo.main.gl.render.font.FontLayout.Sizing;
import com.therealergo.main.gl.render.font.FontLayout.VerticalAlign;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercent;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercentMin;
import com.therealergo.main.math.Vector4F;

public class UIPaneUser_Counter extends UIPaneUser {
	public UIPaneUser_Counter(String name, Vector4F colorText, UIScreenEVoteDemo eVote) {
		super(name, colorText, eVote);
		
		color.set(0.0f, 0.0f, 0.3f, 1.0f);
		setTitleText("COUNTER");
		setNextText(null);
		
		// "Committed" version of their ballot
		addChild(
		new UISizerPercentMin(0.7f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColorShadow("commitballot")
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
		
		// Admin signature, unblinded
		addChild(
		new UISizerPercentMin(0.8f, 0.3f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColorShadow("adminsigunblind")
		.color.set(1.0f, 1.0f, 1.0f, 1.0f)
		.setVisible(false)
				.addChild(
				new UISizerPercent(0.9f), 
				new UIPositionerIn.Center(),
				new UIPaneWithText("text")
				.setFont(Main.tree.font.get("fonts>main>Inconsolata-Bold.ttf"))
				.setTextSizing(Sizing.FixedSizePercent(0.124f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER, true))
				.color.set(0.1f, 0.7f, 0.1f, 1.0f)
				)
		);
		
		// "Committed" version of another person's ballot #0
		addChild(
		new UISizerPercentMin(0.7f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColorShadow("commitballot0")
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
		
		// "Committed" version of another person's ballot #1
		addChild(
		new UISizerPercentMin(0.7f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColorShadow("commitballot1")
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
		
		// "Committed" version of another person's ballot #2
		addChild(
		new UISizerPercentMin(0.7f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColorShadow("commitballot2")
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
		
		// "Digitized" version of their ballot
		addChild(
		new UISizerPercentMin(0.7f, 0.4f), 
		new UIPositionerIn.Center(), 
		new UIPaneWithColorShadow("digiballot")
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
		
		// Display screen for vote results
		addChild(
		new UISizerPercentMin(0.5f, 0.7f), 
		new UIPositionerIn.Center(), 
		new UIPaneResults("results")
		.setVisible(false)
		);
	}

	@Override public void advanceToState(int state) {
		if (state == 9) {
			((UIPaneWithText)getChild("commitballot>text")).setText(App.render.backend.committedUnblindedVote);
			((UIPaneWithText)getChild("adminsigunblind>text")).setText(App.render.backend.adminUnblindedSignature);
			animFadeIn(getChild("adminsigunblind"));
			animFadeIn(getChild("commitballot"));
			setNextText("Check Signature");
			setExplainText(
					"The counter has now received the last committed ballot and unblinded administrator signature from an anonymous individual. " + 
					"The counter must first check to ensure that the administrator's sigature actually corresponds to this committed ballot."
			);
		
		} else if (state == 10) {
			animFadeOut(getChild("adminsigunblind"));
			setNextText("Store");
			setExplainText(
					"Since the signature matched, the vote is now stored away with all of the other votes in this election."
			);
			
		} else if (state == 11) {
			((UIPaneWithText)getChild("commitballot0>text")).setText(App.render.backend.committedUnblindedVote0);
			((UIPaneWithText)getChild("commitballot1>text")).setText(App.render.backend.committedUnblindedVote1);
			((UIPaneWithText)getChild("commitballot2>text")).setText(App.render.backend.committedUnblindedVote2);
			animPaperRotate(getChild("commitballot"));
			animPaperRotate(getChild("commitballot0"));
			animPaperRotate(getChild("commitballot1"));
			animPaperRotate(getChild("commitballot2"));
			animFadeIn(getChild("commitballot0"));
			animFadeIn(getChild("commitballot1"));
			animFadeIn(getChild("commitballot2"));
			setNextText("Publish");
			setExplainText(
					"Since this was the last vote, the counter can now publish the list of committed ballots for everyone to see."
			);
			
		} else if (state == 12) {
			animSlideLeft(getChild("commitballot") , 1.0f + ((float)Math.random()) * 1.0f);
			animSlideLeft(getChild("commitballot0"), 1.0f + ((float)Math.random()) * 1.0f);
			animSlideLeft(getChild("commitballot1"), 1.0f + ((float)Math.random()) * 1.0f);
			animSlideLeft(getChild("commitballot2"), 1.0f + ((float)Math.random()) * 1.0f);
			animPopUser("v");
			setNextText(null);
			setExplainText("");
			
		} else if (state == 14) {
			animFadeIn(getChild("commitballot"));
			animPaperUnRotate(getChild("commitballot"));
			setNextText("Unlock");
			setExplainText(
					"Once the counter receives every voter's commitment key, they can perform their nominal duty. " + 
					"First, they use those commitment keys to unlock each ballot."
			);
			
		} else if (state == 15) {
			((UIPaneWithText)getChild("digiballot>text")).setText(App.render.backend.ballotData);
			animFadeOut(getChild("commitballot"));
			animFadeIn(getChild("digiballot"));
			setNextText("Count");
			setExplainText(
					"Finally, they can take all of the votes and tally the results, producing the results of the election."
			);
			
		} else if (state == 16) {
			((UIPaneResults)getChild("results")).finish();
			animFadeOut(getChild("digiballot"));
			animFadeIn(getChild("results"));
			setNextText("Restart");
			setExplainText(
					"There we have it! A provably secure election, courtesy of blind encryption. " + 
					"Be sure to click \"Restart\" if you want to run another one."
			);
			
		} else if (state == 17) {
			App.render.backend.kill();
			App.render.backend = new BackendInterface();
			Main.gl.render.ui.removeScreen(App.render.screen);
			App.render.screen = (UIScreenEVoteDemo) Main.gl.render.ui.addScreen(new UIScreenEVoteDemo("evotedemo"));
		}
	}
}
