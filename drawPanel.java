import java.awt.*;
import java.awt.event.*;

import java.lang.reflect.Array;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class drawPanel extends JPanel {
    //initializes list of curves (each curve = list of points)
    private ArrayList<ArrayList<Point>> curves = new ArrayList<>();
    private ArrayList<ArrayList<Point>> lines = new ArrayList<>();
    private ArrayList<Color> ccolorList = new ArrayList<>();
    private ArrayList<Color> lcolorList = new ArrayList<>();


    public drawPanel() {

        /* horrible documentation incoming
        probably gonna forget how this works soon so here it is

        colorlists are arrays with each color used per line drawn for each type of line (curve=ccolorlist, line=lcolorlist)
        when colorlist size is <= to the current element being drawn, new color needs to be chosen from input because there
        is no color matching the corresponding element. conversely, when colorlist size > element # being drawn, that means
        the element was rendered previously and already has a corresponding color, so color is taken from same index as the
        element.

        based on this, colorlist.size() should ALWAYS be larger than the last element in ArrayLists curves or lines (to force a new color)

        proof why this works
        base case: colorlist = {black}
                   curves = {curve1}
                   colorlist.size() = 1
                   curve1 = index of 0
                   colorlist.size() > curves.indexof(curve1), so new color is needed

        inductive step: colorlist = {color1,color2,color3,...} and colorlist.size() = n+1 (n+1 to compensate for default black)
                        curves = {curve1,curve2,curve3,...} and curves.size() = n
                        for all curves currently there is a corresponding color because n+1>n

                        curves.add(curve n); then curves.size() = n+1
                        so now colorlist.size() = curves.size()
                        as a result a new color value is taken from Controls class and appended to colorlist
                        finally, colorlist.size() > curves.size()


         */
        ccolorList.add(Color.BLACK);
        lcolorList.add(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //start curve on press
                if(!Controls.stoggled) {
                    ArrayList<Point> currentCurve = new ArrayList<Point>();
                    currentCurve.add(new Point(e.getX(), e.getY()));
                    curves.add(currentCurve);
                } else if(Controls.stoggled) {
                    ArrayList<Point> currentLine = new ArrayList<Point>();
                    currentLine.add(new Point(e.getX(), e.getY()));
                    lines.add(currentLine);
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //compensate for 0 curve and adds point to list of points in list of lists
                if(!Controls.stoggled) {
                    curves.get(curves.size()-1).add(new Point(e.getX(), e.getY()));
                    repaint();
                } else {
                    lines.get(lines.size()-1).add(new Point(e.getX(),e.getY()));
                    repaint();
                }
            }

        });
    }

    public void paint(Graphics g) {

        Image img = Toolkit.getDefaultToolkit().getImage("oof.jpg");
        g.drawImage(img, 0, 0, null);

        for(ArrayList<Point> curve : curves) {
            Point previousPoint = curve.get(0);
            for (Point point : curve) {
                //big sad
                if(ccolorList.size() <= curves.indexOf(curve)){
                    g.setColor(new Color(Controls.redInput, Controls.greenInput, Controls.blueInput));
                    ccolorList.add(new Color(Controls.redInput, Controls.greenInput, Controls.blueInput));
                }else{
                    g.setColor(ccolorList.get(curves.indexOf(curve)));
                }
                g.drawLine(previousPoint.x, previousPoint.y, point.x, point.y);
                previousPoint = point;
            }
        }
        for(ArrayList<Point> line : lines){
            if(lcolorList.size() <= lines.indexOf(line)){
                g.setColor(new Color(Controls.redInput, Controls.greenInput, Controls.blueInput));
                lcolorList.add(new Color(Controls.redInput, Controls.greenInput, Controls.blueInput));
            }else{
                g.setColor(lcolorList.get(lines.indexOf(line)));
            }
            Point startpoint = line.get(0);
            g.drawLine(startpoint.x, startpoint.y, (int) line.get(line.size() - 1).getX(), (int) line.get(line.size() - 1).getY());
        }
    }

}