package com.therealergo.csds444frontend;

import com.therealergo.main.Main;
import com.therealergo.main.gl.render.font.FontLayout.HorizontalAlign;
import com.therealergo.main.gl.render.font.FontLayout.Sizing;
import com.therealergo.main.gl.render.font.FontLayout.VerticalAlign;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerOrigin;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPercentOffset;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPixelOffset;
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
		new UIPaneWithColorShadow("note")
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
		
		// "Blinded" version of their ballot
		addChild(
		new UISizerPercentMin(0.7f), 
		new UIPositionerIn.Center(), 
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
		new UIPositionerIn.Center(), 
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
	}

	@Override public void advanceToState(int state) {
		if (state == 1) {
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
			
		} else if (state == 2) {
			((UIPaneWithText)getChild("commitballot>text")).setText(App.render.backend.committedUnblindedVote);
			
			animFadeOut(getChild("digiballot"));
			animFadeIn(getChild("commitballot"));
			setNextText("Blind Ballot");
			setExplainText(
					"Now that we have your committed ballot, we're going to blind it. " + 
					"This process uses your secret key to ensure that no one can see who you've voted for " + 
					"(until you use that same key to unblind the vote)."
			);
			
		} else if (state == 3) {
			((UIPaneWithText)getChild("blindballot>text")).setText(App.render.backend.committedBlindedVote);
			
			animFadeOut(getChild("commitballot"));
			animFadeIn(getChild("blindballot"));
			setNextText("Send Ballot");
			setExplainText(
					"With your ballot blinded, who you voted for is now completely secret. Just try to read that text! " + 
					"You must now send that blinded ballot data to the administrator, " + 
					"Alongside any verification information like name and voter id. "
			);
		
		} else if (state == 4) {
			animFadeOut(getChild("blindballot"));
			animPopUser("a");
			setNextText(null);
			setExplainText("");
			
		} else if (state == 7) {
			((UIPaneWithText)getChild("adminsig>text")).setText(App.render.backend.adminBlindSignature);
			animFadeIn(getChild("adminsig"));
			setNextText("Unblind");
			setExplainText(
					"With the administrator's blinded signature in hand, " + 
					"the voter can now unblind that signature to get a valid administrator signature for their original committed ballot. " + 
					"The voter will check to make sure that this signature matches that original committed ballot."
			);
		
		} else if (state == 8) {
			((UIPaneWithText)getChild("adminsigunblind>text")).setText(App.render.backend.adminUnblindedSignature);
			animFadeOut(getChild("adminsig"));
			animFadeIn(getChild("adminsigunblind"));
			animFadeIn(getChild("commitballot"));
			setNextText("Send");
			setExplainText(
					"The voter now sends the unblinded administrator signature & ballot to the counter. " + 
					"The counter will know that the ballot is valid because the administrator's signature matches the ballot. " + 
					"As such, the voter does not also need to send any personal information that could link them with their ballot."
			);
		
		} else if (state == 9) {
			animFadeOut(getChild("adminsigunblind"));
			animFadeOut(getChild("commitballot"));
			animPopUser("c");
			setNextText(null);
			setExplainText("");
		
		} else if (state == 12) {
			((UIPaneWithText)getChild("commitballot0>text")).setText(App.render.backend.committedUnblindedVote0);
			((UIPaneWithText)getChild("commitballot1>text")).setText(App.render.backend.committedUnblindedVote1);
			((UIPaneWithText)getChild("commitballot2>text")).setText(App.render.backend.committedUnblindedVote2);
			animPaperRotate(getChild("commitballot"));
			animPaperRotate(getChild("commitballot0"));
			animPaperRotate(getChild("commitballot1"));
			animPaperRotate(getChild("commitballot2"));
			animFadeIn(getChild("commitballot"));
			animFadeIn(getChild("commitballot0"));
			animFadeIn(getChild("commitballot1"));
			animFadeIn(getChild("commitballot2"));
			setNextText("Search");
			setExplainText(
					"With the list of all committed votes now published, " + 
					"the voter may now search the list to ensure that their ballot is included within and being counted."
			);
		
		} else if (state == 13) {
			animSlideRight(getChild("commitballot0"), 1.0f + ((float)Math.random()) * 1.0f);
			animSlideRight(getChild("commitballot1"), 1.0f + ((float)Math.random()) * 1.0f);
			animSlideRight(getChild("commitballot2"), 1.0f + ((float)Math.random()) * 1.0f);
			animPaperUnRotate(getChild("commitballot"));
			setNextText("Send");
			setExplainText(
					"Once the voter has located their vote, they must anonymously send the counter their commitment key. " + 
					"This lets the counter unlock their ballot and read the votes contained within."
			);
			
		} else if (state == 14) {
			animSlideLeft(getChild("commitballot") , 1.0f + ((float)Math.random()) * 1.0f);
			animPopUser("c");
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
