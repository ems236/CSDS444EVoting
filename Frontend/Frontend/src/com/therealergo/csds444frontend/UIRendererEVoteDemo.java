package com.therealergo.csds444frontend;

import com.therealergo.main.Main;
import com.therealergo.main.gl.render.simple2D.GeometrySimple2D;
import com.therealergo.main.gl.render.simple2D.GeometrySimple2D.ES2DTextureMode;
import com.therealergo.main.gl.render.ui.UIPane;
import com.therealergo.main.gl.render.ui.pane.UIPaneToggleButton;
import com.therealergo.main.gl.render.ui.renderer.UIRendererBasic;

public class UIRendererEVoteDemo extends UIRendererBasic {
	@Override protected void renderPreInternal(
			GeometrySimple2D g, UIPane pane, Meta meta,
			float cr, float cg, float cb, float ca
	) {
		// Fancy rounded-rectangle-with-drop-shadow renderer for the big buttons
		if (pane instanceof UIPaneWithColorRounded) {
			UIPaneWithColorRounded paneReal = (UIPaneWithColorRounded)pane;
			g.helper.addRectangleRoundedEdge(
					14.0f, 30.0f, 
					pane.getAreaLeft () + 10.0f, pane.getAreaBottom() - 10.0f, 
					pane.getAreaRight() + 10.0f, pane.getAreaTop   () - 10.0f, 
					0.0f, 0.0f, 0.0f, 0.6f,
					0.0f, 0.0f, 0.0f, 0.0f,
					10000.0f
			);
			g.helper.addRectangleRounded(
					null, null, null, null, 
					pane.getAreaLeft (), pane.getAreaBottom(), 0.0f, 0.0f, 
					pane.getAreaRight(), pane.getAreaTop   (), 1.0f, 1.0f, 
					paneReal.color.getR()*cr, paneReal.color.getG()*cg, paneReal.color.getB()*cb, paneReal.color.getA()*ca,
					10000.0f
			);
		
		// Fancy rendering for togglebuttons as checkboxes for the ballot
		} else if (pane instanceof UIPaneToggleButton) {
			UIPaneToggleButton paneReal = (UIPaneToggleButton)pane;
			float interp = (float) Math.pow(ANIM_DECAY_RATE, Main.gl.getLastFrameRenderTimeMS());
			float state = paneReal.isToggled() ? 1.0f : 0.0f;
			meta.state1 = meta.state1 * interp + state * (1.0f - interp);
			
			super.renderPreInternal(g, pane, meta, cr, cg, cb, ca);
			
			g.helper.addRectangle(
					"packedtextures>ui>setting_check_0.png", ES2DTextureMode.SINGLE, null, 
					pane.getAreaLeft (), pane.getAreaBottom(), 0.0f, 0.0f, 
					pane.getAreaRight(), pane.getAreaTop   (), 1.0f, 1.0f, 
					0.3f * cr, 0.3f * cg, 0.3f * cb, ca
			);
			g.helper.addRectangle(
					"packedtextures>ui>setting_check_1.png", ES2DTextureMode.SINGLE, null, 
					pane.getAreaLeft (), pane.getAreaBottom(), 0.0f, 0.0f, 
					pane.getAreaRight(), pane.getAreaTop   (), 1.0f, 1.0f, 
					1.0f * cr, 0.3f * cg, 0.3f * cb, ca * meta.state1
			);
		
		// Otherwise, normal "basic" rendering
		} else {
			super.renderPreInternal(g, pane, meta, cr, cg, cb, ca);
		}
	}
}
