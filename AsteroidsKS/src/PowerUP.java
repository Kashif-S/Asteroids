import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class PowerUP extends Sprite {

    Sprite s;

    public PowerUP(int x, int y){
        Xposition = x;
        Yposition = y;

        s = new Sprite();
        s.Xposition = x;
        s.Yposition = y;

        shape = new Polygon();
        shape.addPoint(-20, -20);
        shape.addPoint(20, -20);
        shape.addPoint(20, 20);
        shape.addPoint(-20, 20);
        drawShape = new Polygon();
        drawShape.addPoint(-20, -20);
        drawShape.addPoint(20, -20);
        drawShape.addPoint(20, 20);
        drawShape.addPoint(-20, 20);

        s.shape = new Polygon();
        s.shape.addPoint(-20, -20);
        s.shape.addPoint(20, -20);
        s.shape.addPoint(20, 20);
        s.shape.addPoint(-20, 20);
        s.drawShape = new Polygon();
        s.drawShape.addPoint(-20, -20);
        s.drawShape.addPoint(20, -20);
        s.drawShape.addPoint(20, 20);
        s.drawShape.addPoint(-20, 20);

        active = true;
    }

        public void UpdatePosition(double a){
        super.UpdatePosition(false);
        s.angle += 0.1;
        s.UpdatePosition(false);

        Xspeed = Math.cos(a) * thrust;
            Yspeed = Math.sin(a) * thrust;
            s.Xspeed = Xspeed;
            s.Yspeed = Yspeed;

    }

}
