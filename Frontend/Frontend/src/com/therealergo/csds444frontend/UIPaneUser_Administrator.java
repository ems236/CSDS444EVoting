package com.therealergo.csds444frontend;

import com.therealergo.main.math.Vector4F;

public class UIPaneUser_Administrator extends UIPaneUser {
	public UIPaneUser_Administrator(String name, Vector4F colorText, UIScreenEVoteDemo eVote) {
		super(name, colorText, eVote);
		
		color.set(0.0f, 0.3f, 0.0f, 1.0f);
		setTitleText("ADMINISTRATOR");
		setNextText(null);
	}

	@Override public void advanceToState(int state) {
	}
}
