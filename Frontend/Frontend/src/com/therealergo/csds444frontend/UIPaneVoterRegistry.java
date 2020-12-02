package com.therealergo.csds444frontend;

import com.therealergo.main.gl.render.font.FontLayout.HorizontalAlign;
import com.therealergo.main.gl.render.font.FontLayout.Sizing;
import com.therealergo.main.gl.render.font.FontLayout.VerticalAlign;
import com.therealergo.main.gl.render.ui.UIPane;
import com.therealergo.main.gl.render.ui.pane.UIPaneToggleButton;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerList;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerList.Direction;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercent;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercentMin;

public class UIPaneVoterRegistry extends UIPaneWithColorShadow {
	public UIPaneVoterRegistry(String name) {
		super(name);
		
		float vSize = 0.195f;
		
		addChild(
		new UISizerPercent(0.8f, 0.2f), 
		new UIPositionerIn.TopCenter(), 
		new UIPaneWithText("title")
		.setText("Voter Registry")
		.color.set(0.0f, 0.0f, 0.0f, 1.0f)
		);

		addChild(
		new UISizerPercent(1.0f, 0.8f), 
		new UIPositionerIn.BottomCenter(), 
		new UIPane("rows")
				
				.addChild(
				new UISizerPercent(0.9f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row0")
				.setText("Alice Sue\n1234 Glencoe Ter.\nId: " + (int)(Math.random() * 65535))
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerPercentMin(0.5f), 
						new UIPositionerIn.CenterRight(), 
						new UIPaneToggleButton("button")      { protected void performAction() { manuallyToggle(); } }
						.manuallySetToggled(true)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row1")
				.setText("Ben Elder\n1234 Woodcrest Ln.\nId: 420")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerPercentMin(0.5f), 
						new UIPositionerIn.CenterRight(), 
						new UIPaneToggleButton("button")      { protected void performAction() { manuallyToggle(); } }
						.manuallySetToggled(true)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row2")
				.setText("Christine Harvey\n101 Oak Rd. Box #9\nId: " + (int)(Math.random() * 65535))
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerPercentMin(0.5f), 
						new UIPositionerIn.CenterRight(), 
						new UIPaneToggleButton("button")      { protected void performAction() { manuallyToggle(); } }
						.manuallySetToggled(true)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row3")
				.setText("Kristina Byrd (Tiphanie)\n1534 E. 45th St.\nId: " + (int)(Math.random() * 65535))
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerPercentMin(0.5f), 
						new UIPositionerIn.CenterRight(), 
						new UIPaneToggleButton("button")      { protected void performAction() { manuallyToggle(); } }
						.manuallySetToggled(true)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSize), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row4")
				.setText("John Smith\n1101 Example St.\nId: " + App.render.backend.voterId)
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerPercentMin(0.5f), 
						new UIPositionerIn.CenterRight(), 
						new UIPaneToggleButton("button")      { protected void performAction() { manuallyToggle(); } }
						.manuallySetToggled(false)
						)
				)
		);
	}
	
	public void finish() {
		((UIPaneToggleButton)getChild("rows>row4>button")).manuallyToggle();
	}
}
