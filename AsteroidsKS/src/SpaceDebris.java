import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class SpaceDebris extends Sprite {

    public SpaceDebris(int x, int y) {
        double a;
        shape = new Polygon();
        shape.addPoint(3, 3);
        shape.addPoint(-3, -3);
        shape.addPoint(-3, 3);
        shape.addPoint(3, -3);
        drawShape = new Polygon();
        drawShape.addPoint(3, 3);
        drawShape.addPoint(-3, -3);
        drawShape.addPoint(-3, 3);
        drawShape.addPoint(3, -3);
        active = true;
        Xposition = x;
        Yposition = y;
        double thrust;

        a = Math.random() * Math.PI * 2;
        thrust = Math.random() * 7 + 3;
        Yspeed = Math.sin(a) * thrust;
        Xspeed = Math.cos(a) * thrust;


    }

    public SpaceDebris(double x, double y, double a) {
        shape = new Polygon();
        shape.addPoint(3, 3);
        shape.addPoint(-3, -3);
        shape.addPoint(-3, 3);
        shape.addPoint(3, -3);
        drawShape = new Polygon();
        drawShape.addPoint(3, 3);
        drawShape.addPoint(-3, -3);
        drawShape.addPoint(-3, 3);
        drawShape.addPoint(3, -3);

        shape = new Polygon();
        shape.addPoint(3, 3);
        shape.addPoint(-3, -3);
        shape.addPoint(-3, 3);
        shape.addPoint(3, -3);
        drawShape = new Polygon();
        drawShape.addPoint(3, 3);
        drawShape.addPoint(-3, -3);
        drawShape.addPoint(-3, 3);
        drawShape.addPoint(3, -3);
        active = true;
        Xposition = x;
        Yposition = y;
        double thrust;


        thrust = 7;
        Yspeed = Math.sin(a) * thrust;
        Xspeed = Math.cos(a) * thrust;

    }
}
