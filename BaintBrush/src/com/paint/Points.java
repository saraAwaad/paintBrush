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
public class Points extends Pattern{
    int x,y;
    Color fontColor;
    public Points(int x1,int y1,Color fontColor){
        super();
        this.x=x1;
        this.y=y1;
        this.fontColor=fontColor;
    }
   
 
        
    public void drawShape(Graphics g){
      //  System.out.println("from line");
        g.setColor(fontColor);
        //g.drawLine(x,y,x,y);
          //g.fillOval(x-2, y-2,3, 3);
          g.fillRect(x-1, y-1,2, 2);
    }
}
