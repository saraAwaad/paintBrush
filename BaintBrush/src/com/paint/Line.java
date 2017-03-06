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
public class Line extends Pattern{
    private int x1, y1, x2 ,y2;
    Color fontColor;
   public Line(int x1,int y1, int x2,int y2,Color fontColor){
        super();
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.fontColor=fontColor;
    }
   
    public int getX1(){
		return x1;
    }
    public int getY1(){
            return y2;
    }
    public int getX2(){
            return x2;
    }
    public int getY2(){
            return y2;
    }
        
    public void drawShape(Graphics g){
      //  System.out.println("from line");
        g.setColor(fontColor);
        g.drawLine(x1,y1,x2,y2);
    
    }
    public void drawFilledShape(Graphics g){
        g.setColor(fontColor);
        g.drawLine(x1,y1,x2,y2);
    }
    
    
}
