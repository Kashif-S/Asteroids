import java.awt.Graphics;
import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class Asteroid extends Sprite {

    int size = 3;
    int life = 100;

    public void initializeAsteriod() {

        double a, h;
        a = Math.random() * Math.PI * 2;
        h = Math.random() * 5;
        shape = new Polygon();
        shape.addPoint(30 * size, 3 * size);
        shape.addPoint(5 * size, 35 * size);
        shape.addPoint(-25 * size, 10 * size);
        shape.addPoint(-17 * size, -15 * size);
        shape.addPoint(20 * size, -35 * size);
        drawShape = new Polygon();
        drawShape.addPoint(30 * size, 3 * size);
        drawShape.addPoint(5 * size, 35 * size);
        drawShape.addPoint(-25 * size, 10 * size);
        drawShape.addPoint(-17 * size, -15 * size);
        drawShape.addPoint(20 * size, -35 * size);
        active = true;

        rotation = Math.random();

        Yspeed = Math.sin(a) * (h * 2);
        Xspeed = Math.cos(a) * (h * 2);

        a = Math.random() * Math.PI * 2;
        h = Math.random() * 700 + 500;
        Xposition = (int) (Math.cos(a) * h) + 450;
        Yposition = (int) (Math.cos(a) * h) + 350;

    }

    public Asteroid() {

        initializeAsteriod();
    }

    public void UpdatePosition() {
        super.UpdatePosition(true);
        angle += rotation;
    }

    public Asteroid(int x, int y, int s) {
        size = s;
        initializeAsteriod();
        Xposition = x;
        Yposition = y;
    }
    public void paint(Graphics g){
    g.fillPolygon(drawShape);
}
}

