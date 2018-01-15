import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class SpaceCraft extends Sprite {

    public SpaceCraft() {
        active = true;
        shape = new Polygon();
        shape.addPoint(0, 35);
        shape.addPoint(10, 0);
        shape.addPoint(15, -25);
        shape.addPoint(30, -35);
        shape.addPoint(10, 0);
        shape.addPoint(15, -25);
        shape.addPoint(-15, -25);
        shape.addPoint(-10, 0);
        shape.addPoint(-30, -35);
        shape.addPoint(-15, -25);
        shape.addPoint(-10, 0);
        shape.addPoint(0, 35);
        drawShape = new Polygon();
        drawShape.addPoint(0, 35);
        drawShape.addPoint(10, 0);
        drawShape.addPoint(15, -25);
        drawShape.addPoint(30, -35);
        drawShape.addPoint(10, 0);
        drawShape.addPoint(15, -25);
        drawShape.addPoint(-15, -25);
        drawShape.addPoint(-10, 0);
        drawShape.addPoint(-30, -35);
        drawShape.addPoint(-15, -25);
        drawShape.addPoint(-10, 0);
        drawShape.addPoint(0, 35);

        Xspeed = 0;
        Yspeed = 0;

        treasureMagnet = true;

        thrust = 1;
        rotation = 0.1;
        powerup = 20;
    }

    public void accelerate() {


        Yspeed += Math.sin(angle + Math.PI / 2) * thrust;
        Xspeed += Math.cos(angle + Math.PI / 2) * thrust;

    }

    public void rotateRight() {
        angle += rotation;
    }

    public void rotateLeft() {
        angle -= rotation;
    }
}
