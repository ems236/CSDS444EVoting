package com.therealergo.csds444frontend;

import com.therealergo.main.gl.render.font.FontLayout.HorizontalAlign;
import com.therealergo.main.gl.render.font.FontLayout.Sizing;
import com.therealergo.main.gl.render.font.FontLayout.VerticalAlign;
import com.therealergo.main.gl.render.ui.UIPane;
import com.therealergo.main.gl.render.ui.pane.UIPaneTextEditor;
import com.therealergo.main.gl.render.ui.pane.UIPaneToggleButton;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithColor;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithTexture;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerList;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerList.Direction;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerOrigin;
import com.therealergo.main.gl.render.ui.sizer.UISizerFill;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercent;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercentMin;

public class UIPaneBallot extends UIPaneWithTexture {
	public UIPaneBallot(String name) {
		super(name);
		texture.set("packedtextures>ui>ballot.jpg");
		
		float vSize = 0.036f;

		addChild(
		new UISizerPercent(0.45f, 0.76f), 
		new UIPositionerIn.BottomCenter(), 
		new UIPane("rows")
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row0")
				.setText("Vote for one candidate for President of CWRU")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row1")
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("l")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row1>l", "row1>r", "row2>l", "row2>r"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Barbara Snyder")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("r")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row1>r", "row1>l", "row2>l", "row2>r"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Baker Mayfield")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row2")
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("l")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row2>l", "row1>l", "row1>r", "row2>r"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Barack Obama")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("r")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row2>r", "row1>l", "row1>r", "row2>l"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Craig Newmark")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row3")
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row4")
				.setText("Vote for one candidate for Mayor of Cleveland")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row5")
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("l")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row5>l", "row5>r", "row6>l"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Drew Carey")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("r")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row5>r", "row5>l", "row6>l"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Kid Cudi")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row6")
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("l")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row6>l", "row5>l", "row5>r"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Jimmy Haslam")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row7")
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row8")
				.setText("Do you support building a pedestrian bridge")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row9")
				.setText("over Euclid at Adelbert?")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row10")
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("l")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row10>l", "row10>r"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Yes")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("r")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row10>r", "row10>l"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneWithText("text")
								.setText("No")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row11")
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row12")
				.setText("Do you support replacing the chicken tendies")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row13")
				.setText("at Carlton Commons with cantaloupe salad?")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row14")
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("l")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row14>l", "row14>r"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
								new UIPaneWithText("text")
								.setText("Yes")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPane("r")
								.addChild(
								new UISizerPercentMin(1.0f), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneToggleButton("button")      { protected void performAction() { handleVoteSelect("row14>r", "row14>l"); } }
								)
								.addChild(
								new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
								new UIPositionerList(Direction.RIGHT_TO_LEFT, 0.5f), 
								new UIPaneWithText("text")
								.setText("No")
								.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
								.color.set(0.0f, 0.0f, 0.0f, 1.0f)
								)
						)
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row15")
				)
				
				.addChild(
				new UISizerPercent(1.0f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPane("row16")
						.addChild(
						new UISizerPercent(0.4f, 1.0f), 
						new UIPositionerList(Direction.LEFT_TO_RIGHT), 
						new UIPaneWithText("label")
						.setText("Voter Id Number:")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER))
						.color.set(0.0f, 0.0f, 0.0f, 1.0f)
						)
						.addChild(
						new UISizerPercent(0.6f, 1.0f), 
						new UIPositionerList(Direction.LEFT_TO_RIGHT), 
						new UIPaneTextEditor("text")
						.setGhostText("Enter Voter Id Number")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
						.color.set(0.0f, 0.0f, 0.0f, 1.0f)
						)
				)
		);
	}

	protected void handleVoteSelect(String selectPath, String... otherPaths) {
		for (String otherPath : otherPaths) {
			((UIPaneToggleButton)getChild("rows>" + otherPath + ">button")).manuallySetToggled(false);
		}
	}
	
	private String readButton(String prev, String path) {
		if (prev != null) {
			return prev;
		}
		if (((UIPaneToggleButton)getChild(path + ">button")).isToggled()) {
			return ((UIPaneWithText)getChild(path + ">text")).getText();
		} else {
			return null;
		}
	}

	public String getBallotData() {
		
		// Read in the vote statuses from the user-facing buttons
		String votePresident = null;
		votePresident = readButton(votePresident, "rows>row1>l");
		votePresident = readButton(votePresident, "rows>row1>r");
		votePresident = readButton(votePresident, "rows>row2>l");
		votePresident = readButton(votePresident, "rows>row2>r");
		
		String voteCleveland = null;
		voteCleveland = readButton(voteCleveland, "rows>row5>l");
		voteCleveland = readButton(voteCleveland, "rows>row5>r");
		voteCleveland = readButton(voteCleveland, "rows>row6>l");
		
		String voteProp1 = null;
		voteProp1 = readButton(voteProp1, "rows>row10>l");
		voteProp1 = readButton(voteProp1, "rows>row10>r");

		String voteProp2 = null;
		voteProp2 = readButton(voteProp2, "rows>row14>l");
		voteProp2 = readButton(voteProp2, "rows>row14>r");
		
		// Construct a json vote from those buttons
		return "{" + 
			   "\n\t\"vote\": {" + 
			   "\n\t\t\"president\": \"" + votePresident + "\"," + 
			   "\n\t\t\"cleveland\": \"" + voteCleveland + "\"," + 
			   "\n\t\t\"prop1\": \"" + voteProp1 + "\"," + 
			   "\n\t\t\"prop2\": \"" + voteProp2 + "\"" + 
			   "\n\t}" + 
			   "\n}";
	}

	public boolean isValid() {

		// Read in the vote statuses from the user-facing buttons
		String votePresident = null;
		votePresident = readButton(votePresident, "rows>row1>l");
		votePresident = readButton(votePresident, "rows>row1>r");
		votePresident = readButton(votePresident, "rows>row2>l");
		votePresident = readButton(votePresident, "rows>row2>r");
		
		String voteCleveland = null;
		voteCleveland = readButton(voteCleveland, "rows>row5>l");
		voteCleveland = readButton(voteCleveland, "rows>row5>r");
		voteCleveland = readButton(voteCleveland, "rows>row6>l");
		
		String voteProp1 = null;
		voteProp1 = readButton(voteProp1, "rows>row10>l");
		voteProp1 = readButton(voteProp1, "rows>row10>r");

		String voteProp2 = null;
		voteProp2 = readButton(voteProp2, "rows>row14>l");
		voteProp2 = readButton(voteProp2, "rows>row14>r");
		
		// Ensure that all votes have had a selection and the voter id number has been inserted
		return votePresident != null && 
			   voteCleveland != null && 
			   voteProp1 != null && 
			   voteProp2 != null && 
			   App.render.backend.voterId.equals(((UIPaneTextEditor)getChild("rows>row16>text")).getText());
	}
}
