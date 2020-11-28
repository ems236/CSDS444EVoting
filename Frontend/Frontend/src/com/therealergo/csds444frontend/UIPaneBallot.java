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
import com.therealergo.main.gl.render.ui.positioner.UIPositionerPercentOffset;
import com.therealergo.main.gl.render.ui.sizer.UISizerFill;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercent;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercentMin;

public class UIPaneBallot extends UIPaneWithTexture {
	public UIPaneBallot(String name) {
		super(name);
		texture.set("packedtextures>ui>ballot.jpg");
		
		addChild(
		new UISizerPercent(0.45f, 0.05f), 
		new UIPositionerIn.Center().add(new UIPositionerPercentOffset(0.0f, 4.0f)), 
		new UIPane("row0")
				.addChild(
				new UISizerPercentMin(1.0f), 
				new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
				new UIPaneToggleButton("button")      //{ protected void performAction() { switchToTab(paneA, paneAPos); } }
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithColor("vis")
						.color.set(0.3f, 0.3f, 0.3f, 1.0f)
						)
				)
				.addChild(
				new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
				new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
				new UIPaneWithText("text")
				.setText(" Legalize Babs ----------")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
		);
		
		addChild(
		new UISizerPercent(0.45f, 0.05f), 
		new UIPositionerIn.Center().add(new UIPositionerPercentOffset(0.0f, 2.0f)), 
		new UIPane("row1")
				.addChild(
				new UISizerPercentMin(1.0f), 
				new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
				new UIPaneToggleButton("button")      //{ protected void performAction() { switchToTab(paneA, paneAPos); } }
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithColor("vis")
						.color.set(0.3f, 0.3f, 0.3f, 1.0f)
						)
				)
				.addChild(
				new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
				new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
				new UIPaneWithText("text")
				.setText(" Legalize Cocaine ------")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
		);
		
		addChild(
		new UISizerPercent(0.45f, 0.05f), 
		new UIPositionerIn.Center().add(new UIPositionerPercentOffset(0.0f, 0.0f)), 
		new UIPane("row2")
				.addChild(
				new UISizerPercentMin(1.0f), 
				new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
				new UIPaneToggleButton("button")      //{ protected void performAction() { switchToTab(paneA, paneAPos); } }
						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithColor("vis")
						.color.set(0.3f, 0.3f, 0.3f, 1.0f)
						)
				)
				.addChild(
				new UISizerFill().add(new UISizerPercentMin(1.0f, 0.0f)), 
				new UIPositionerList(Direction.LEFT_TO_RIGHT, 0.5f), 
				new UIPaneWithText("text")
				.setText(" Legalize Weed ---------")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
		);
		
		addChild(
		new UISizerPercent(0.45f, 0.05f), 
		new UIPositionerIn.Center().add(new UIPositionerPercentOffset(0.0f, -2.0f)), 
		new UIPane("row3")
				.addChild(
				new UISizerFill(), 
				new UIPositionerIn.Center(), 
				new UIPaneTextEditor("text")
				.setGhostText("Enter Voter Id Number")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
		);
	}
}
