package game;
import javafx.scene.input.KeyCode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import settings.Settings;

import java.util.ArrayList;

public class Ship extends GameObj {

    private double accX;
    private double accY;
    private double accel;

    private double[] shipPointsX;
    private double[] shipPointsY;

    private ArrayList<Bullet> bullets;

    private boolean turningLeft;
    private boolean turningRight;
    private boolean accelerating;
    private boolean shooting;

    private Settings s;


    public Ship(Settings s) {
        super(s.getFrameWidth()>>1, s.getFrameHeight()>>1, 0, 0, 0, s);
        this.s = getSettings();

        bullets = new ArrayList<>();

        accX = 0;
        accY = 0;

        shipPointsX = new double[4];
        shipPointsY = new double[4];
    }

    private void turn() {
        angle -= 3 * (turningLeft ? 1 : 0);
        angle += 3 * (turningRight ? 1 : 0);

        cos = Math.cos(Math.toRadians(angle));
        sin = Math.sin(Math.toRadians(angle));
    }


    private void accelerate() {
        accel = s.getAccel() * (accelerating ? 1 : 0);
    }



    private void move() {
        shoot();
        turn();
        accelerate();

        accX = accel * cos;
        accY = accel * sin;

        speedX += accX - speedX * s.getFriction();
        speedY += accY - speedY * s.getFriction();

        x += speedX;
        y += speedY;

        checkBounds();
        setShipPoints();
        removeDeadBullets();
    }

    private void checkBounds() {
        if (x < 0) x = s.getFrameWidth();
        if (x > s.getFrameWidth()) x = 0;
        if (y < 0) y = s.getFrameHeight();
        if (y > s.getFrameHeight()) y = 0;
    }

    private void setShipPoints() {
        shipPointsX[0] = x + (10 * cos);
        shipPointsX[1] = x - (10 * cos) - (5 * sin);
        shipPointsX[2] = x - (10 * cos) + (5 * sin);
        shipPointsX[3] = shipPointsX[0];

        shipPointsY[0] = y + (10 * sin);
        shipPointsY[1] = y + (5 * cos) - (10 * sin);
        shipPointsY[2] = y - (5 * cos) - (10 * sin);
        shipPointsY[3] = shipPointsY[0];
    }


    private void shoot() {
        if (!shooting) return;

        System.out.println("" + bullets.size());

        if (bullets.size() > 0) if (bullets.get(bullets.size()-1).getAge() > 19) { //move minimum age to settings
            if (bullets.size() < s.maxBullets()) {
                bullets.add(new Bullet(getFront(), getSpeed(), this.angle, s));
            } else {
                bullets.remove(0);
                bullets.add(new Bullet(getFront(), getSpeed(), this.angle, s));
            }
        }
        if (bullets.size() == 0) {
            bullets.add(new Bullet(getFront(), getSpeed(), this.angle, s));
        }
    }

    private void removeDeadBullets() {
        if (bullets.size() > 0) {
            if (bullets.get(0).isDead()) {
                bullets.remove(0);
            }
        }
    }

    public void keyPressed(KeyCode key) {
        if (key == KeyCode.LEFT) turningLeft = true;
        if (key == KeyCode.RIGHT) turningRight = true;
        if (key == KeyCode.UP) accelerating = true;
        if (key == KeyCode.SPACE) shooting = true;
    }

    public void keyReleased(KeyCode key) {
        if (key == KeyCode.LEFT) turningLeft = false;
        if (key == KeyCode.RIGHT) turningRight = false;
        if (key == KeyCode.UP) accelerating = false;
        if (key == KeyCode.SPACE) shooting = false;
    }

    public void draw(GraphicsContext gc) {
        move();

        gc.setStroke(Color.WHITE);
        for (int i = 0; i < shipPointsY.length - 1; i++) {
            gc.strokeLine(shipPointsX[i], shipPointsY[i], shipPointsX[i+1], shipPointsY[i+1]);
        }


        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(gc, s);
        }
    }


    public double[] getFront() {
        double[] front = {shipPointsX[0], shipPointsY[0]};
        return front;
    }

    public double[] getSpeed() {
        double[] speed = {speedX, speedY};
        return speed;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

}