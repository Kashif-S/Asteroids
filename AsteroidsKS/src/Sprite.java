import java.awt.Graphics;
import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class Sprite {

    Polygon shape, drawShape;
    double Yposition;
    double Xposition;
    double Xspeed;
    double Yspeed;
    double angle,setangle;
    double rotation;
    double thrust;
    boolean active,treasureMagnet;
    int counter, powerup;

    /*constructor*/
    public Sprite() {
    }

    public void paint(Graphics g) {

        g.drawPolygon(drawShape);
    }

    public void UpdatePosition(boolean wrap) {
        if (wrap == true){
        wrapAround();
        }
        Xposition += Xspeed;
        Yposition += Yspeed;
        counter++;
        int tempx, tempy;
        for (int i = 0; i < shape.npoints; i++) {

            tempx = (int) Math.round(shape.xpoints[i] * Math.cos(angle) - shape.ypoints[i] * Math.sin(angle));
            tempy = (int) Math.round(shape.xpoints[i] * Math.sin(angle) + shape.ypoints[i] * Math.cos(angle));

            drawShape.xpoints[i] = tempx;
            drawShape.ypoints[i] = tempy;

        }
        drawShape.invalidate();
        drawShape.translate((int)Xposition, (int)Yposition);
    }

    public void wrapAround() {
        if (Xposition > 900) {
            Xposition = 0;
        }
        if (Yposition > 700) {
            Yposition = 0;
        }
        if (Xposition < 0) {
            Xposition = 900;
        }
        if (Yposition < 0) {
            Yposition = 700;
        }
    }
}
