package com.paint;


import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sara
 */
public class Pattern {
    protected Graphics go;
   
    public Pattern(){
       // go=g;
    }
    public void drawShape(Graphics g){
        System.out.println("from parent");
    }
    public void drawFilledShape(Graphics g){
        System.out.println("from parent");
    }
    
}
