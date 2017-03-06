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
public class FreeLine extends Pattern {
    Color fontColor;
     int xs[];
     int ys[];
     int polycount;
    public FreeLine(Color fontColor,int polycount,int xs[],int ys[]){
        super();
        this.fontColor=fontColor;
         this.xs=xs;
         this.ys=ys;
         this.polycount=polycount;
    }
     public void drawShape(Graphics go){
         go.drawPolyline(xs, ys, polycount);
     
     }
    public void drawFilledShape(Graphics g){
        System.out.println("from parent");
    }
    
    
}
