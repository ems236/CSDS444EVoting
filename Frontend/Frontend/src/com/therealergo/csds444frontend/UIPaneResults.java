package com.therealergo.csds444frontend;

import com.therealergo.main.gl.render.font.FontLayout.HorizontalAlign;
import com.therealergo.main.gl.render.font.FontLayout.Sizing;
import com.therealergo.main.gl.render.font.FontLayout.VerticalAlign;
import com.therealergo.main.gl.render.ui.UIPane;
import com.therealergo.main.gl.render.ui.pane.UIPaneWithText;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerIn;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerList;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerList.Direction;
import com.therealergo.main.gl.render.ui.positioner.UIPositionerOrigin;
import com.therealergo.main.gl.render.ui.sizer.UISizerFill;
import com.therealergo.main.gl.render.ui.sizer.UISizerPercent;

public class UIPaneResults extends UIPaneWithColorShadow {
	public UIPaneResults(String name) {
		super(name);
		
		float vSizeB = 0.082f;
		float vSizeS = 0.055f;
		
		addChild(
		new UISizerPercent(0.8f, 0.15f), 
		new UIPositionerIn.TopCenter(), 
		new UIPaneWithText("title")
		.setText("Election Results")
		.color.set(0.0f, 0.0f, 0.0f, 1.0f)
		);

		addChild(
		new UISizerPercent(1.0f, 0.85f), 
		new UIPositionerIn.BottomCenter(), 
		new UIPane("rows")
		
				.addChild(
				new UISizerPercent(0.9f, vSizeB), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row0")
				.setText("President of CWRU")
				.setTextSizing(Sizing.VerticalFit(0.7f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row1")
				.setText("Barack Obama")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row2")
				.setText("Craig Newmark")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row3")
				.setText("Barbara Snyder")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row4")
				.setText("Baker Mayfield")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
		
				.addChild(
				new UISizerPercent(0.9f, vSizeB), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row5")
				.setText("Mayor of Cleveland")
				.setTextSizing(Sizing.VerticalFit(0.7f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row6")
				.setText("Drew Carey")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row7")
				.setText("Jimmy Haslam")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row8")
				.setText("Kid Cudi")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
		
				.addChild(
				new UISizerPercent(0.9f, vSizeB), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row9")
				.setText("Prop 1: Pedestrian Bridge")
				.setTextSizing(Sizing.VerticalFit(0.7f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row10")
				.setText("Yes")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row11")
				.setText("No")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
		
				.addChild(
				new UISizerPercent(0.9f, vSizeB), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row12")
				.setText("Prop 2: Canteloupe Replacement")
				.setTextSizing(Sizing.VerticalFit(0.7f, 0.0f, 0.0f, HorizontalAlign.CENTER, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row13")
				.setText("Yes")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
				
				.addChild(
				new UISizerPercent(0.9f, vSizeS), 
				new UIPositionerList(Direction.TOP_TO_BOTTOM, 0.5f), 
				new UIPaneWithText("row14")
				.setText("No")
				.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.LEFT, VerticalAlign.CENTER))
				.color.set(0.0f, 0.0f, 0.0f, 1.0f)

						.addChild(
						new UISizerFill(), 
						new UIPositionerOrigin(), 
						new UIPaneWithText("count")
						.setTextSizing(Sizing.VerticalFit(0.9f, 0.0f, 0.0f, HorizontalAlign.RIGHT, VerticalAlign.CENTER))
						.color.set(1.0f, 0.0f, 0.0f, 1.0f)
						)
				)
		);
	}
	
	public void finish() {
		((UIPaneWithText)getChild("rows>row1>count")).setText(App.render.backend.results.getJSONObject("president").getInt("Barack Obama"  ) + " votes");
		((UIPaneWithText)getChild("rows>row2>count")).setText(App.render.backend.results.getJSONObject("president").getInt("Craig Newmark" ) + " votes");
		((UIPaneWithText)getChild("rows>row3>count")).setText(App.render.backend.results.getJSONObject("president").getInt("Barbara Snyder") + " votes");
		((UIPaneWithText)getChild("rows>row4>count")).setText(App.render.backend.results.getJSONObject("president").getInt("Baker Mayfield") + " votes");

		((UIPaneWithText)getChild("rows>row6>count")).setText(App.render.backend.results.getJSONObject("cleveland").getInt("Drew Carey"  ) + " votes");
		((UIPaneWithText)getChild("rows>row7>count")).setText(App.render.backend.results.getJSONObject("cleveland").getInt("Jimmy Haslam") + " votes");
		((UIPaneWithText)getChild("rows>row8>count")).setText(App.render.backend.results.getJSONObject("cleveland").getInt("Kid Cudi"    ) + " votes");

		((UIPaneWithText)getChild("rows>row10>count")).setText(App.render.backend.results.getJSONObject("prop1").getInt("Yes") + " votes");
		((UIPaneWithText)getChild("rows>row11>count")).setText(App.render.backend.results.getJSONObject("prop1").getInt("No" ) + " votes");

		((UIPaneWithText)getChild("rows>row13>count")).setText(App.render.backend.results.getJSONObject("prop2").getInt("Yes") + " votes");
		((UIPaneWithText)getChild("rows>row14>count")).setText(App.render.backend.results.getJSONObject("prop2").getInt("No" ) + " votes");
	}
}
