package com.therealergo.csds444frontend;

import com.therealergo.main.math.Vector4F;

public class UIPaneUser_Counter extends UIPaneUser {
	public UIPaneUser_Counter(String name, Vector4F colorText, UIScreenEVoteDemo eVote) {
		super(name, colorText, eVote);
		
		color.set(0.0f, 0.0f, 0.3f, 1.0f);
		setTitleText("COUNTER");
		setNextText(null);
	}

	@Override public void advanceToState(int state) {
	}
}
