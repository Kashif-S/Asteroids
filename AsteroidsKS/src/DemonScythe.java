import java.awt.Graphics;
import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class DemonScythe extends Sprite {



    public DemonScythe(int x, int y, double a) {
 
        shape =new Polygon();
        shape.addPoint(-2, -20);
        shape.addPoint(-2, 20);
        shape.addPoint(2, 20);
        shape.addPoint(2, -14);
        shape.addPoint(30, -16);
        shape.addPoint(2, -20);
        shape.addPoint(-2, -20);
        drawShape = new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 40);
        drawShape.addPoint(4, 40);
        drawShape.addPoint(4, 6);
        drawShape.addPoint(30, 2);
        drawShape.addPoint(4, 0);
        drawShape.addPoint(0, 0);


        active = true;
        Xposition = x;
        Yposition = y;
        angle += 0.0000000000000000001;

        thrust = 0.000000000000000000000000000000000001;

        Yspeed += Math.sin(angle + Math.PI / 2) * thrust;
        Xspeed += Math.cos(angle + Math.PI / 2) * thrust;





    }

    public void UpdatePosition() {
        super.UpdatePosition(true);

    }
        public void paint(Graphics g){
        g.fillPolygon(drawShape);
}
}
