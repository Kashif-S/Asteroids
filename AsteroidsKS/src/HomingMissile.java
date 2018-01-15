import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;


/*
 * @author Kashif Sayeed
 */

public class HomingMissile extends Sprite {

    int size = 3;
    ArrayList<SpaceDebris> dlist;

    public HomingMissile(int x, int y, double a) {
        shape = new Polygon();
        shape.addPoint(5 * size, 0 * size);
        shape.addPoint(3 * size, -2 * size);
        shape.addPoint(0 * size, -2 * size);
        shape.addPoint(-2 * size, -4 * size);
        shape.addPoint(-2 * size, 4 * size);
        shape.addPoint(0 * size, 2 * size);
        shape.addPoint(3 * size, 2 * size);


        drawShape = new Polygon();
        drawShape.addPoint(5 * size, 0 * size);
        drawShape.addPoint(3 * size, -2 * size);
        drawShape.addPoint(0 * size, -2 * size);
        drawShape.addPoint(-2 * size, -4 * size);
        drawShape.addPoint(-2 * size, 4 * size);
        drawShape.addPoint(0 * size, 2 * size);
        drawShape.addPoint(3 * size, 2 * size);

        dlist = new ArrayList<SpaceDebris>();

        active = true;
        Xposition = x;
        Yposition = y;
        angle = a;
        thrust = 10;


    }

    public void UpdatePosition(double a) {

        super.UpdatePosition(true);
        angle = a;
        Xspeed = Math.cos(a) * thrust;
        Yspeed = Math.sin(a) * thrust;
        dlist.add(new SpaceDebris(Xposition, Yposition, 0));
        for (int i = 0; i < dlist.size(); i++) {
            dlist.get(i).UpdatePosition(true);

            if (dlist.get(i).counter > 25) {
                dlist.remove(i);
            }
        }

    }

    public void paint(Graphics g) {
         g.fillPolygon(drawShape);
    super.paint(g);
    g.setColor(Color.yellow);
       for (int i = 0; i < dlist.size(); i++) {
     dlist.get(i).paint(g);
    } 
}
}
