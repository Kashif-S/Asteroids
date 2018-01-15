import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class Bullet extends Sprite {

    public Bullet(int x, int y, double a) {


        shape = new Polygon();
        shape.addPoint(0, 0);
        shape.addPoint(0, 3);
        shape.addPoint(3, 3);
        shape.addPoint(3, 0);

        drawShape = new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 1);
        drawShape.addPoint(1, 1);
        drawShape.addPoint(1, 0);

        active = true;
        Xposition = x;
        Yposition = y;
        angle = a;
        thrust = 10;
        Yspeed = Math.sin(angle + Math.PI / 2) * thrust;
        Xspeed = Math.cos(angle + Math.PI / 2) * thrust;




    }
}
