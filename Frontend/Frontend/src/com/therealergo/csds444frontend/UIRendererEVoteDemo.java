package com.therealergo.csds444frontend;

import com.therealergo.main.gl.render.simple2D.GeometrySimple2D;
import com.therealergo.main.gl.render.ui.UIPane;
import com.therealergo.main.gl.render.ui.renderer.UIRendererBasic;

public class UIRendererEVoteDemo extends UIRendererBasic {
	@Override protected void renderPreInternal(
			GeometrySimple2D g, UIPane pane, Meta meta,
			float cr, float cg, float cb, float ca
	) {
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
		} else {
			super.renderPreInternal(g, pane, meta, cr, cg, cb, ca);
		}
	}
}
