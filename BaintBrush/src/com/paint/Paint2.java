package com.paint;


import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.Vector;
import javax.swing.JColorChooser;
import javax.swing.undo.CannotRedoException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sara
 */
public class Paint2 extends java.applet.Applet {
   
    Checkbox fill;
    Color fontColor=Color.black ;
    Graphics go;
    int x,y,x2,y2;
    int pressedFlag=0;
    int i=0,index=0;
    int Erase=0,clear=0;
    int xErase,yErase;
    int undo=0;
    boolean filled=false;
    Vector<Pattern>  currentShape;
    Vector<Pattern> shapes;

    public Paint2() {
        this.shapes = new Vector<>();
        this.currentShape=new Vector<>();
    }
    /**
     * Initializes the applet Paint2
     */
    @Override
    public void init() {
       // MenuShortcut shortcut = new MenuShortcut(KeyEvent.VK_C,false);      
      //this.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    initComponents();
                    
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        choice1.add("Line");
        choice1.add("Circle");
        choice1.add("Rect");
        choice1.add("Free line");
        jPanel1.setOpaque(true);
      
        this.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getSource());
                System.out.println(e.VK_ADD );
            }

            @Override
            public void keyPressed(KeyEvent e) {
               System.out.println(e.getSource());
               // System.out.println(e.VK_ADD );
            }

            @Override
            public void keyReleased(KeyEvent e) {
               System.out.println(e.getSource());
                System.out.println(e.VK_ADD );
            }
        
        
        });
        this.addMouseMotionListener(
			new MouseAdapter(){
				public void mouseDragged(MouseEvent ev){	
					if(pressedFlag==1 && isInBoreder(ev) ){
						x2=ev.getX();
						y2=ev.getY();
						repaint();
					}
                                        if(index==3){
                                            shapes.add(new Points(x2, y2,fontColor));
                                            repaint();
                                        }
                                        if(Erase==1 && pressedFlag==1){
                                             shapes.add(new Rect(x2-8, y2-8,12,12,Color.white,true));
                                             repaint();
                                        }                                       
				}
                               public void mouseMoved(MouseEvent e){
                               
                                    if(Erase==1&&y>40){
                                            xErase=e.getX();
                                            yErase=e.getY();
                                             repaint();
                                        }
                               }
			}
		);
		this.addMouseListener(new Paint2.MypressedListener ());
    }
        
    @Override
    public void paint(Graphics g){
        go=g;
        DrawToolBar(g,1);
        for(Pattern s:shapes){
            s.drawShape(g);
	}
        if(Erase==1 &&y>40){
            g.setColor(Color.white);
            g.fillRect(x2-4, y2-4,8,8); 
            g.setColor(Color.BLACK);
            g.draw3DRect(xErase-8, yErase-8,12,12,true);
        }else if(y>40 &&undo==0&& pressedFlag==1){
                g.setColor(fontColor);
                switch(index){
                        case 0:
                             g.drawLine(x,y,x2,y2);                           
                            break;
                        case 1:
                            if(filled){
                                 g.fillOval(x>x2?x2:x, y>y2?y2:y,Math.abs(x2-x),Math.abs(y2-y));
                            }else{
                                 g.drawOval(x>x2?x2:x, y>y2?y2:y,Math.abs(x2-x),Math.abs(y2-y));
                            }
                            break;
                        case 2:
                            if(filled){
                                 g.fillRect(x>x2?x2:x, y>y2?y2:y,Math.abs(x2-x),Math.abs(y2-y));
                            }else{
                                 g.draw3DRect(x>x2?x2:x, y>y2?y2:y,Math.abs(x2-x),Math.abs(y2-y),true);
                            }

                            break;
                        case 3:
                          //  g.fillOval(x2-2, y2-2,3, 3);
                           g.fillRect(x2-1, y2-1,2,2);  
                          // currentShape=new Points(x2, y2,fontColor);
                            break;
                }
            undo=0;
        }
    }
  public void DrawToolBar(Graphics g,int selected ){
         int x=500,y=5;
         g.setColor(new Color(0,64,128));        
         g.fillRect(0, 0, 2000, 40);
         if(index==0)
            g.setColor(Color.yellow);
         else
            g.setColor(Color.white);
         g.fillRect(10+x, y, 30, 20);  
         if(index==1)
            g.setColor(Color.yellow);
         else
            g.setColor(Color.white);
         g.fillRect(50+x, y, 30, 20); 
         if(index==2)
            g.setColor(Color.yellow);
         else
            g.setColor(Color.white);
         g.fillRect(90+x, y, 30, 20); 
         if(index==3)
            g.setColor(Color.yellow);
         else
            g.setColor(Color.white);
         g.fillRect(130+x, y, 30, 20); 
        
         
         
         g.setColor(Color.blue);
         g.drawLine(13+x,y+2,35+x,22);        
         g.drawOval(53+x,y+2,25,15);
         g.draw3DRect(93+x,y+2,22,12,true);
         int xValues2[] = {150+x, 135+x, 160+x};
         int yValues2[] = {10, 15, 20};
         g.drawPolyline(xValues2,yValues2,3 ); 
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        checkbox1 = new java.awt.Checkbox();
        label1 = new java.awt.Label();
        choice1 = new java.awt.Choice();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(284, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(284, 30));
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel1KeyTyped(evt);
            }
        });

        checkbox1.setLabel("Fill");
        checkbox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkbox1ItemStateChanged(evt);
            }
        });

        label1.setBackground(new java.awt.Color(0, 0, 0));
        label1.setText("   .");
        label1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label1MouseClicked(evt);
            }
        });

        choice1.setName(""); // NOI18N
        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice1ItemStateChanged(evt);
            }
        });

        label2.setBackground(new java.awt.Color(255, 255, 255));
        label2.setText("Undo");
        label2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label2MouseExited(evt);
            }
        });

        label3.setBackground(new java.awt.Color(255, 255, 255));
        label3.setText("Redo");
        label3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label3MouseExited(evt);
            }
        });

        label4.setBackground(new java.awt.Color(255, 255, 255));
        label4.setText("Eraser");
        label4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label4MouseExited(evt);
            }
        });

        label5.setForeground(new java.awt.Color(255, 51, 51));
        label5.setText("Clear");
        label5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 321, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void label1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label1MouseClicked
        // choose color
         fontColor = JColorChooser.showDialog(Paint2.this, "Choose a color", Color.blue);
         label1.setBackground(fontColor);				
    }//GEN-LAST:event_label1MouseClicked

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged
        // choose shape
        Erase=0;
        undo=0;
        index=choice1.getSelectedIndex();
    }//GEN-LAST:event_choice1ItemStateChanged

    private void checkbox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkbox1ItemStateChanged
        // TODO add your handling code here:
      
        Erase=0;      
        filled=checkbox1.getState();
    }//GEN-LAST:event_checkbox1ItemStateChanged

    private void label4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label4MouseEntered

          label4.setBackground(Color.yellow);
    }//GEN-LAST:event_label4MouseEntered

    private void label4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label4MouseExited
     
        label4.setBackground(Color.white);
    }//GEN-LAST:event_label4MouseExited

    private void label3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label3MouseEntered
        // TODO add your handling code here:
         label3.setBackground(Color.yellow);
    }//GEN-LAST:event_label3MouseEntered

    private void label2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label2MouseEntered
        // TODO add your handling code here:
         label2.setBackground(Color.yellow);
    }//GEN-LAST:event_label2MouseEntered

    private void label3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label3MouseExited
        // TODO add your handling code here:
          label3.setBackground(Color.white);
    }//GEN-LAST:event_label3MouseExited

    private void label2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label2MouseExited
        // TODO add your handling code here:
          label2.setBackground(Color.white);
    }//GEN-LAST:event_label2MouseExited

    private void label4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label4MouseClicked
        // TODO add your handling code here:
        Erase=1;
    }//GEN-LAST:event_label4MouseClicked

    private void jPanel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1KeyTyped

    private void label2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label2MouseClicked
        // Undo
        Erase=0;
        if(shapes.size()>0){
            currentShape.add(shapes.lastElement());
            shapes.remove(currentShape.lastElement());
            undo=1;
            repaint();
        }
    }//GEN-LAST:event_label2MouseClicked

    private void label3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label3MouseClicked
        // Redo
        Erase=0;
        if(currentShape.size()>0){
            shapes.add(currentShape.lastElement());
            currentShape.remove(currentShape.lastElement());
            //undo=0;
            repaint();
        }
    }//GEN-LAST:event_label3MouseClicked

    private void label5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label5MouseClicked
        // TODO add your handling code here:
        clear=1;
        currentShape=shapes;
        shapes.clear();
        repaint();
    }//GEN-LAST:event_label5MouseClicked

        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Checkbox checkbox1;
    private java.awt.Choice choice1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    // End of variables declaration//GEN-END:variables

 class MypressedListener extends  MouseAdapter{	
                @Override
		public void mousePressed(MouseEvent ev) { 
                    int xm=500;
			x=ev.getX();
                        y=ev.getY();
                    
                        if(y>40){
                            pressedFlag=1;                     
                            undo=0;
                        }else if(x>=10+xm && x<=40+xm && y>=5 && y<=25){
                            //line
                            Erase=0;
                            index=0;
                            repaint();

                        }else if(x>=50+xm && x<=80+xm && y>=5 && y<=25){
                            //circle
                            Erase=0;
                            index=1;
                            repaint();
                        }else if(x>=90+xm && x<=120+xm && y>=5 && y<=25){
                            //Rect
                            Erase=0;
                            index=2;
                            repaint();
                        }else if(x>=130+xm && x<=160+xm && y>=5 && y<=25){
                            //FreeLine
                            Erase=0;
                            index=3;
                            repaint();
                        }
		}
                @Override
		public void mouseReleased(MouseEvent ev){
			  if(y>40){
				pressedFlag=0;
                                if(Erase==1){
                                    shapes.add(new Rect(x2-8, y2-8,12,12,Color.white,true));
                                 }else{
                                    switch(index){
                                      case 0:
                                           shapes.add(new Line(x, y, x2 ,y2,fontColor));
                                          break;
                                      case 1:
                                          shapes.add(new Circle(x>x2?x2:x, y>y2?y2:y,Math.abs(x2-x),Math.abs(y2-y),fontColor,filled));
                                          break;
                                      case 2:

                                          shapes.add(new Rect(x>x2?x2:x, y>y2?y2:y,Math.abs(x2-x),Math.abs(y2-y),fontColor,filled));

                                      case 3:

                                          break;
                                  }
                                }
                          }
			
                              
				i++;
			
			
		}
	}
    // TODO overwrite start(), stop() and destroy() methods
 public boolean isInBoreder(MouseEvent ev){
     return ev.getX()>2 && ev.getX()<getWidth()-2&& ev.getY()>35 &&ev.getY()<getHeight();
 }
}
