import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class AoEshot extends Sprite {

boolean blast;

    public AoEshot(int x, int y, double a) {
        
        
        shape = new Polygon();
        shape.addPoint(0, 5);
        shape.addPoint(5, 0);
        shape.addPoint(10, 5);
        shape.addPoint(10, 10);
        shape.addPoint(0, 10);

        drawShape = new Polygon();
        drawShape.addPoint(0, 5);
        drawShape.addPoint(5, 0);
        drawShape.addPoint(10, 5);
        drawShape.addPoint(10, 10);
        drawShape.addPoint(0, 10);
        blast = false;
        active = true;
        Xposition = x;
        Yposition = y;
        angle = a;
        thrust = 2;
        Yspeed = Math.sin(angle + Math.PI / 2) * thrust;
        Xspeed = Math.cos(angle + Math.PI / 2) * thrust;
    }
}
