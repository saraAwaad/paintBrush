/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jets.paintv2;

import com.jets.paintv2.PaintApp;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 *
 * @author Sara
 */
public class PaintBrush extends Applet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        setLayout(new BorderLayout());
        this.setSize(new Dimension(800,500));
        add(new PaintApp());
    }

    // TODO overwrite start(), stop() and destroy() methods
}
