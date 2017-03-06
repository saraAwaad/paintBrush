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
public class Circle extends Pattern{
       int x,y,width,hieght;
       Color fontColor;
       boolean filled;
    public Circle( int x1,int y1,int width1,int hieght1,Color fontColor,boolean filled){
        super();
        x=x1;
        y=y1;
        width=width1;
        hieght=hieght1;
        this.fontColor=fontColor;
        this.filled=filled;
    }
       @Override
     public void drawShape(Graphics g) {
         g.setColor(fontColor);
         if(filled)
             g.fillOval(x,y,width,hieght);
         else
             g.drawOval(x,y,width,hieght);
    }
    public void drawFilledShape(Graphics g){
        g.setColor(fontColor);
        g.fillOval(x,y,width,hieght);
    }
    
}
