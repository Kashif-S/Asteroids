
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * @author Kashif Sayeed
 */

public class Asteroids extends Applet implements KeyListener, ActionListener, MouseListener {

    int lives, score, level = 1, HIGHSCORE;
    SpaceCraft ship;
    Timer timer;
    Image offscreen;
    Graphics offg;
    boolean up, right, left, bulletfire, spawn, levelComplete, gameover, rapidshot, rainbow, overheat, menuopen;
    long fpsCount;
    ArrayList<Asteroid> alist;
    ArrayList<Bullet> blist;
    ArrayList<SpaceDebris> dlist;
    ArrayList<HomingMissile> hlist;
    ArrayList<AoEshot> elist;
    ArrayList<SpaceDebris> slist;
    ArrayList<PowerUP> plist;
    ArrayList<DemonScythe> mlist;
    int levelcounter, recharge, mouseX, mouseY;
    AudioClip thruster, pewpew, asteroidhit, shiphit;
    Color[] colors;
    PowerUP p;
    Polygon shape, drawShape;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        // TODO start asynchronous download of heavy resources
        this.setSize(900, 700);
        lives = 5;

        timer = new Timer(20, this);

        ship = new SpaceCraft();

        this.addKeyListener(this);
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        ship.Xposition = 450;
        ship.Yposition = 350;

        alist = new ArrayList<Asteroid>();
        blist = new ArrayList<Bullet>();
        dlist = new ArrayList<SpaceDebris>();
        hlist = new ArrayList<HomingMissile>();
        elist = new ArrayList<AoEshot>();
        slist = new ArrayList<SpaceDebris>();
        plist = new ArrayList<PowerUP>();
        mlist = new ArrayList<DemonScythe>();

        for (int i = 0; i < 2; i++) {
            alist.add(new Asteroid());

        }
        thruster = getAudioClip(getCodeBase(), "thruster.wav");
        pewpew = getAudioClip(getCodeBase(), "laser80.wav");
        shiphit = getAudioClip(getCodeBase(), "explode1.wav");
        asteroidhit = getAudioClip(getCodeBase(), "explode0.wav");

        colors = new Color[6];
        colors[0] = Color.RED;
        colors[1] = Color.BLUE;
        colors[2] = Color.YELLOW;
        colors[3] = Color.GREEN;
        colors[4] = Color.ORANGE;
        colors[5] = Color.MAGENTA;

        p = new PowerUP(100, 100);

        menuopen = true;

        this.addMouseListener(this);

        readScore();

    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {

        offg.setColor(Color.black);
        offg.fillRect(0, 0, 900, 700);

        if (menuopen) {
            offg.setColor(Color.GRAY);
            offg.fillRect(50, 50, 800, 600);
            offg.setColor(Color.red);
            offg.fillRect(200, 100, 500, 110);
            offg.fillRect(200, 500, 500, 110);
            offg.setColor(Color.GREEN);
            offg.drawString("PLAY", 350, 155);

            offg.drawString("     //\\\\           //|||\\\\     ______  ____      ____    ______      ", 900 / 2, 700 / 2);
            offg.drawString("    //  \\\\         \\\\     \\\\    ----------  ||------    ||-------||  ||---------||", 900 / 2, 700 / 2 + 10);
            offg.drawString("   //__\\\\        \\\\               ||         ||___     ||____|| ||             ||", 900 / 2, 700 / 2 + 20);
            offg.drawString("  //----\\\\    //   \\\\               ||        ||------    ||\\\\           ||             ||", 900 / 2, 700 / 2 + 30);
            offg.drawString(" //        \\\\   \\\\   \\\\              ||        ||___     || \\\\          ||______||", 900 / 2, 700 / 2 + 40);
            offg.drawString("//          \\\\   \\\\|||//             ||        ||------    ||   \\\\        ||-----------||", 900 / 2, 700 / 2 + 50);

        }
        if (ship.active) {
            offg.setColor(Color.GREEN);
            ship.paint(offg);
        }

        offg.setColor(Color.RED);
        for (int i = 0; i < alist.size(); i++) {
            alist.get(i).paint(offg);
        }
        offg.setColor(Color.BLUE);
        for (int i = 0; i < blist.size(); i++) {
            blist.get(i).paint(offg);
        }
        offg.setColor(colors[(int) (Math.random() * 6)]);
        for (int i = 0; i < dlist.size(); i++) {
            dlist.get(i).paint(offg);
        }

        for (int i = 0; i < slist.size(); i++) {
            slist.get(i).paint(offg);
        }
        offg.setColor(Color.ORANGE);
        for (int i = 0; i < hlist.size(); i++) {
            hlist.get(i).paint(offg);
        }

        for (int i = 0; i < elist.size(); i++) {
            elist.get(i).paint(offg);
        }

        for (int i = 0; i < plist.size(); i++) {
            offg.setColor(Color.green);
            plist.get(i).s.paint(offg);
            offg.setColor(Color.blue);
            plist.get(i).paint(offg);
        }
        offg.setColor(Color.magenta);
        for (int i = 0; i < mlist.size(); i++) {
            mlist.get(i).paint(offg);
        }

        if (rainbow && alist.isEmpty() == false) {
            offg.setColor(colors[(int) (Math.random() * 6)]);

            offg.drawLine((int) ship.Xposition, (int) ship.Yposition, (int) alist.get(findclosestasteroid()).Xposition, (int) alist.get(findclosestasteroid()).Yposition);

        }
        offg.setColor(Color.CYAN);
        if (gameover) {
            offg.drawString("Gameover! U Suk!  ", 900 / 2, 700 / 2);
        }
        offg.drawString("Lives:  " + lives, 450, 10);
        offg.drawString("Score:  " + score, 450, 20);
        offg.setColor(Color.white);
        offg.drawString("Level  " + level, 900 / 2, 500);
        offg.drawString("HIGH SCORE: " + HIGHSCORE, 900 / 2, 700);

        g.drawImage(offscreen, 0, 0, this);

        repaint();

    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            right = true;
        }
        if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            left = true;
        }

        if (KeyEvent.VK_UP == e.getKeyCode()) {
            up = true;

        }
        if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            bulletfire = true;

        }
        if (KeyEvent.VK_D == e.getKeyCode()) {
            homingshot();
        }
        if (KeyEvent.VK_D == e.getKeyCode()) {
            spawn = true;
        }
        if (KeyEvent.VK_A == e.getKeyCode()) {
            AoEgun();
        }
        if (KeyEvent.VK_S == e.getKeyCode()) {
            DemonShot();
        }
        if (KeyEvent.VK_F == e.getKeyCode()) {
            waveShot();
        }
        if (KeyEvent.VK_W == e.getKeyCode()) {
            rainbow = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
            right = false;

        }
        if (KeyEvent.VK_LEFT == e.getKeyCode()) {
            left = false;
        }

        if (KeyEvent.VK_UP == e.getKeyCode()) {
            up = false;
        }
        if (KeyEvent.VK_SPACE == e.getKeyCode()) {
            bulletfire = false;
        }
        if (KeyEvent.VK_D == e.getKeyCode()) {
            homingshot();
        }
        if (KeyEvent.VK_D == e.getKeyCode()) {
            spawn = false;
        }
        if (KeyEvent.VK_W == e.getKeyCode()) {
            rainbow = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (System.currentTimeMillis() > fpsCount) {
            fpsCount = System.currentTimeMillis() + (1000 / 60);
            ship.UpdatePosition(true);

            for (int i = 0; i < alist.size(); i++) {
                if (alist.get(i).active == true) {
                    alist.get(i).UpdatePosition();

                }
                if (alist.get(i).life <= 0) {
                    alist.get(i).active = false;
                }
            }
            if (mouseX >= 200 && mouseX <= 700 && mouseY >= 100 && mouseY <= 210) {

                menuopen = false;
            }
            for (int i = 0; i < blist.size(); i++) {
                blist.get(i).UpdatePosition(true);

                if (blist.get(i).counter > 100 || blist.get(i).active == false) {
                    blist.remove(i);
                }
            }
            for (int i = 0; i < elist.size(); i++) {
                elist.get(i).UpdatePosition(true);

                if (elist.get(i).counter > 200 || elist.get(i).active == false) {
                    elist.get(i).blast = true;
                    explosion();
                    elist.remove(i);

                }
            }
            for (int i = 0; i < plist.size(); i++) {
                if (plist.get(i).active == true) {

                    if (ship.treasureMagnet = true) {
                        plist.get(i).thrust = 2;
                        plist.get(i).s.thrust = 2;

                        plist.get(i).UpdatePosition(homingAngle(ship, plist.get(i)));

                    } else {
                        plist.get(i).UpdatePosition(false);
                        plist.get(i).s.UpdatePosition(false);
                    }

                } else {
                    plist.remove(i);
                }

            }

            if (rainbow) {
                for (int i = 0; i < alist.size(); i++) {
                    alist.get(i).life -= 0.1;
                    recharge += 0.1;
                    if (recharge > 150) {
                        overheat = true;
                        rainbow = false;
                    }
                }

            } else {
                if (overheat = false) {
                    recharge -= 0.1;
                } else {
                    recharge -= 0.01;
                    rainbow = false;

                }

            }
            if (recharge <= 0) {
                overheat = false;
            }

            for (int i = 0; i < dlist.size(); i++) {
                dlist.get(i).UpdatePosition(true);

                if (dlist.get(i).counter > 10) {
                    dlist.remove(i);
                }

            }
            for (int i = 0; i < mlist.size(); i++) {
                mlist.get(i).UpdatePosition();
                mlist.get(i).angle += 0.1;
                mlist.get(i).thrust += 0.001;
                //mlist.get(i).thrust *= 2;
                mlist.get(i).Yspeed += mlist.get(i).thrust;
                mlist.get(i).Xspeed += mlist.get(i).thrust;
                if (mlist.get(i).counter > 500 || mlist.get(i).active == false) {
                    mlist.remove(i);
                }

            }
            for (int i = 0; i < slist.size(); i++) {
                slist.get(i).UpdatePosition(true);

                if (slist.get(i).counter > 100) {
                    slist.remove(i);
                }
            }

            for (int i = 0; i < hlist.size(); i++) {
                if (alist.isEmpty() == false) {
                    if (hlist.get(i).active == true) {
                        hlist.get(i).UpdatePosition(homingAngle(alist.get(findclosestasteroid()), hlist.get(i)));
                    } else {
                        hlist.remove(i);

                    }

                } else {
                    hlist.clear();
                }
            }
            if (alist.isEmpty()) {
                levelComplete = true;
            }
            if (levelComplete) {
                levelcounter++;
                if (levelcounter > 200) {
                    levelComplete = false;
                    level++;
                    levelcounter = 0;
                    for (int i = 0; i < 2 + (level / 3); i++) {
                        alist.add(new Asteroid());

                    }
                }
            }

            keyCheck();
            if (lives > 0) {
                respawnShip();
            }
            handleCollisions();
            checkAsteroidDestruction();

        }
        if (lives <= 0) {
            gameover = true;
        }

    }

    public void checkAsteroidDestruction() {
        for (int i = 0; i < alist.size(); i++) {
            if (alist.get(i).active == false) {
                if (alist.get(i).size > 1) {
                    alist.add(new Asteroid((int) alist.get(i).Xposition, (int) alist.get(i).Yposition, alist.get(i).size - 1));
                    alist.add(new Asteroid((int) alist.get(i).Xposition, (int) alist.get(i).Yposition, alist.get(i).size - 1));
                }
                alist.remove(i);
            }
        }

    }

    public void keyCheck() {
        if (up && ship.active == true) {
            ship.accelerate();
            dlist.add(new SpaceDebris(ship.Xposition - Math.cos(ship.angle + Math.PI / 2) * 15f, ship.Yposition - Math.sin(ship.angle + Math.PI / 2) * 15f, ship.angle - Math.PI / 2));
            thruster.play();
        }

        if (left) {
            ship.rotateLeft();
        }

        if (right) {
            ship.rotateRight();
        }

        if (bulletfire) {
            bulletshoot();
        }

    }

    public boolean collision(Sprite thing1, Sprite thing2) {

        int x, y;
        for (int i = 0; i < thing1.shape.npoints; i++) {
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];

            if (thing2.drawShape.contains(x, y)) {
                return true;
            }
        }

        for (int i = 0; i < thing2.shape.npoints; i++) {
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];

            if (thing1.drawShape.contains(x, y)) {
                return true;
            }
        }
        return false;

    }

    public void handleCollisions() {
        double rnd;
        for (int i = 0; i < alist.size(); i++) {
            if (collision(alist.get(i), ship)) {

                if (ship.active) {
                    ship.counter = 0;
                    ship.active = false;
                    alist.get(i).active = false;
                    score -= 50;
                    lives -= 1;
                    shiphit.play();

                    rnd = Math.random() * 10 + 10;
                    for (int r = 0; r < rnd; r++) {
                        dlist.add(new SpaceDebris((int) ship.Xposition, (int) ship.Yposition));

                    }

                }

            }

            for (int a = 0; a < blist.size(); a++) {
                if (collision(blist.get(a), alist.get(i))) {
                    blist.get(a).active = false;
                    alist.get(i).active = false;
                    score += 10;
                    asteroidhit.play();

                    rnd = Math.random() * 10 + 10;
                    for (int r = 0; r < rnd; r++) {
                        dlist.add(new SpaceDebris((int) blist.get(a).Xposition, (int) blist.get(a).Yposition));
                    }
                    if (Math.random() * 10 > 9) {
                        plist.add(new PowerUP((int) alist.get(i).Xposition, (int) alist.get(i).Yposition));
                    }
                }
            }
            for (int a = 0; a < mlist.size(); a++) {
                if (collision(mlist.get(a), alist.get(i))) {
                    mlist.get(a).active = false;
                    alist.get(i).active = false;
                    score += 10;
                    asteroidhit.play();

                    rnd = Math.random() * 10 + 10;
                    for (int r = 0; r < rnd; r++) {
                        dlist.add(new SpaceDebris((int) mlist.get(a).Xposition, (int) mlist.get(a).Yposition));
                    }
                    if (Math.random() * 10 > 9) {
                        plist.add(new PowerUP((int) alist.get(i).Xposition, (int) alist.get(i).Yposition));
                    }
                }
            }
            for (int a = 0; a < hlist.size(); a++) {
                if (collision(hlist.get(a), alist.get(i))) {
                    hlist.get(a).active = false;
                    alist.get(i).active = false;
                    rnd = Math.random() * 10 + 10;
                    for (int r = 0; r < rnd; r++) {
                        dlist.add(new SpaceDebris((int) hlist.get(a).Xposition, (int) hlist.get(a).Yposition));
                        if (Math.random() * 10 > 9) {
                            plist.add(new PowerUP((int) alist.get(i).Xposition, (int) alist.get(i).Yposition));
                        }
                    }
                    hlist.remove(a);
                    score += 10;
                }
            }

            for (int a = 0; a < elist.size(); a++) {
                if (collision(elist.get(a), alist.get(i))) {
                    elist.get(a).active = false;
                    alist.get(i).active = false;
                    elist.get(a).blast = true;
                    explosion();
                    asteroidhit.play();
                    if (Math.random() * 10 > 9) {
                        plist.add(new PowerUP((int) alist.get(i).Xposition, (int) alist.get(i).Yposition));
                    }
                }
            }
            for (int a = 0; a < slist.size(); a++) {
                if (collision(slist.get(a), alist.get(i))) {
                    slist.get(a).active = false;
                    alist.get(i).active = false;
                    asteroidhit.play();
                    rnd = Math.random() * 10 + 10;
                    for (int r = 0; r < rnd; r++) {
                        dlist.add(new SpaceDebris((int) slist.get(a).Xposition, (int) slist.get(a).Yposition));
                    }
                    if (Math.random() * 10 > 9) {
                        plist.add(new PowerUP((int) alist.get(i).Xposition, (int) alist.get(i).Yposition));
                    }
                    slist.remove(a);

                    score += 10;
                }
            }
        }
        for (int a = 0; a < plist.size(); a++) {
            if (collision(plist.get(a), ship)) {
                plist.get(a).active = false;
                ship.powerup += 5;
            }
        }
    }

    public void respawnShip() {
        if (ship.active == false && ship.counter > 75 && isRespawnSafe()) {
            ship.Xposition = 450;
            ship.Yposition = 350;
            ship.Xspeed = 0;
            ship.Yspeed = 0;
            ship.angle = -Math.PI / 2;
            ship.active = true;
            ship.counter = 0;
        }
    }

    public boolean isRespawnSafe() {
        int a, b, c = 0;
        for (int i = 0; i < alist.size(); i++) {
            a = (int) alist.get(i).Xposition - 450;
            b = (int) alist.get(i).Yposition - 350;
            c = (int) Math.sqrt(a * a + b * b);
        }
        return c > 100;
    }

    public void bulletshoot() {

        if (ship.counter > 10) {
            blist.add(new Bullet((int) ship.Xposition + (int) ship.Xspeed, (int) ship.Yposition + (int) ship.Yspeed, ship.angle));
            ship.counter = 0;
            pewpew.play();

        }

    }

    public double homingAngle(Sprite a, Sprite b) {

        double y = a.Yposition - b.Yposition;
        double x = a.Xposition - b.Xposition;
        double ang;
        ang = Math.atan(y / x);
        if (a.Xposition < b.Xposition) {
            ang += Math.PI;
        }
        return ang;
    }

    public void homingshot() {
        if (ship.powerup > 10) {
            if (ship.counter > 15) {
                hlist.add(new HomingMissile((int) ship.Xposition + (int) ship.Xspeed, (int) ship.Yposition + (int) ship.Yspeed, ship.angle));
                ship.counter = 0;
                ship.powerup -= 10;

            }
        }
    }

    public void explosion() {
        double rnd = 0;
        for (int a = 0; a < elist.size(); a++) {
            rnd = Math.random() * 10 + 10;
            for (int r = 0; r < rnd; r++) {
                if (elist.get(a).blast == true) {
                    slist.add(new SpaceDebris((int) elist.get(a).Xposition, (int) elist.get(a).Yposition));
                    asteroidhit.play();
                }
            }
        }
    }

    public int findclosestasteroid() {
        double smallest = 0;
        int apos = 0;
        for (int i = 0; i < alist.size(); i++) {
            double x = alist.get(i).Xposition - ship.Xposition;
            double y = alist.get(i).Yposition - ship.Yposition;
            double c = Math.sqrt(x * x + y * y);
            if (i == 0) {
                smallest = c;
            }

            if (smallest > c) {

                smallest = c;
                apos = i;

            }
        }
        return apos;
    }

    public void AoEgun() {
        elist.add(new AoEshot((int) ship.Xposition + (int) ship.Xspeed, (int) ship.Yposition + (int) ship.Yspeed, ship.angle));
        ship.counter = 0;
    }

    public void DemonShot() {
        if (ship.counter > 10) {
            if (ship.powerup > 1) {
                mlist.add(new DemonScythe((int) ship.Xposition + (int) ship.Xspeed, (int) ship.Yposition + (int) ship.Yspeed, ship.angle));
                ship.powerup -= 1;
                ship.counter = 0;
            }
        }
    }

    public void waveShot() {
        if (ship.counter > 50) {
            for (int i = -1000; i < 1000; i++) {

                blist.add(new Bullet((int) ship.Xposition, (int) ship.Yposition, ship.angle + i));
                ship.counter = 0;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void readScore() {
        try {
            BufferedReader br;
            BufferedWriter bw;
            br = new BufferedReader(new FileReader("HighScore.txt"));
            String line = br.readLine();
            while (line != null) {
                HIGHSCORE = Integer.parseInt(line);

                line = br.readLine();
            }
            br.close();
            if (score > HIGHSCORE) {
                bw = new BufferedWriter(new FileWriter("HighScore.txt"));

                while (line != null) {
                    HIGHSCORE = Integer.parseInt(line);

                }
                bw.close();
            }

        } catch (IOException e) {

        }

    }

}
