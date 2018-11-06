/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textioassignment;

/**
 *
 * @author 348848128
 */
import java.util.*;
import java.awt.*;

import javax.swing.*;

public class Captcha extends JPanel {
 private String[] token = new String[]{"3", "r", "c", "t", "9",
                                       "n", "x", "a", "j", "8", 
                                       "d", "p", "f", "y", "7", 
                                       "h", "k"};
 private String captcha = new String();
 
 private Font bigFont;
 private FontMetrics fm;
 
 public Captcha() {
  super();
  
  initData();
  initUI();
 }
 
 private void initData() {
  java.util.List l = Arrays.asList(token);
  Collections.shuffle(l);
  
  captcha = token[0] + token[1] + token[2] + token[3];
 }
 
 private void initUI() {
  setDoubleBuffered(true);
  
  bigFont = new Font("Monospaced",Font.BOLD, 20);
  fm = getFontMetrics(bigFont);
  
  setBorder(null);
  setPreferredSize(new Dimension((fm.getWidths()[0]*4) + 27, 
                                  fm.getHeight() + fm.getMaxDescent() + 2));
  setMaximumSize(new Dimension((fm.getWidths()[0]*4) + 27, 
                                  fm.getHeight() + fm.getMaxDescent() + 2));
  setMinimumSize(new Dimension((fm.getWidths()[0]*4) + 27, 
                                fm.getHeight() + fm.getMaxDescent() + 2));
  setBackground(Color.WHITE);
 }
 
 public void paintComponent(Graphics g) {
  super.paintComponent(g);
  
  Random r = new Random();
  
  Graphics2D g2d = (Graphics2D) g.create();
  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                       RenderingHints.VALUE_ANTIALIAS_ON);
  g2d.setFont(bigFont);
  g2d.translate(0, 0);
  
  for (int x = 10; x < getWidth(); x+=10) {
   g2d.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
   g2d.drawLine(x, 0, x, getHeight());
  }
  
  for (int y = 10; y < getHeight(); y+=10) {
   g2d.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
   g2d.drawLine(0, y, getWidth(), y);
  }
  
  g2d.setColor(Color.BLACK);
  g2d.drawString(token[0], 7, fm.getHeight() + 2);
  g2d.drawString(token[1], 7 + fm.getWidths()[0], fm.getHeight() + 2);
  g2d.drawString(token[2], 7 + (fm.getWidths()[0]*2), fm.getHeight() + 2);
  g2d.drawString(token[3], 7 + (fm.getWidths()[0]*3), fm.getHeight() + 2);
  
  g2d.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
  g2d.drawString(token[0], 5, fm.getHeight());
  g2d.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
  g2d.drawString(token[1], 5 + fm.getWidths()[0], fm.getHeight());
  g2d.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
  g2d.drawString(token[2], 5 + (fm.getWidths()[0]*2), fm.getHeight());
  g2d.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
  g2d.drawString(token[3], 5 + (fm.getWidths()[0]*3), fm.getHeight());
 }
 
 public boolean match(String input) {
  return (input.equalsIgnoreCase(captcha));
 }
}