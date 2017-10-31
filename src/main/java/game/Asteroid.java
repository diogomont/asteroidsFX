package game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import settings.Settings;

import java.util.ArrayList;
import java.util.Random;

public class Asteroid extends GameObj {

	private double[] asteroidPointsX;
	private double[] asteroidPointsY;
	private double rotationSpeed;
    private int level;
    private double size;

    public Asteroid(double x, double y, double speedX, double speedY, 
    				double angle, double rotationSpeed, int level, Settings s) {
    	super(x, y, speedX, speedY, angle, s);
    	this.rotationSpeed = rotationSpeed;
    	this.level = level;
        size = 25 - level * 5;
    	asteroidPointsX = new double[7];
    	asteroidPointsY = new double[7];
    }

    public void draw(GraphicsContext gc) {
    	move();

        gc.setStroke(Color.WHITE);
        for (int i = 0; i < asteroidPointsX.length - 1; i++) {
            gc.strokeLine(asteroidPointsX[i], asteroidPointsY[i], asteroidPointsX[i+1], asteroidPointsY[i+1]);
        }    	
    }
    


    private void move() {
    	turn();

    	x += speedX;
    	y += speedY;

    	checkBounds();
    	setAsteroidPoints();
    }

    private void turn() {
        angle += rotationSpeed;
        cos = Math.cos(Math.toRadians(angle));
        sin = Math.sin(Math.toRadians(angle));
    }

    private void checkBounds() {
        if (x < 0) x = s.getFrameWidth();
        if (x > s.getFrameWidth()) x = 0;
        if (y < 0) y = s.getFrameHeight();
        if (y > s.getFrameHeight()) y = 0;
    }


    private void setAsteroidPoints() {
        for (int i = 0; i < asteroidPointsX.length; i++) {
        	asteroidPointsX[i] = x + (size * Math.cos(Math.toRadians(angle + 60*i)));
           	asteroidPointsY[i] = y + (size * Math.sin(Math.toRadians(angle + 60*i)));
        }
    }


    public void isHit(ArrayList<Asteroid> asteroids, int index, Random r) {
        if (this.level < 3) {
            for (int j = 0; j < 2; j++) { //Move these into settings as well.
                asteroids.add(new Asteroid(x, y, (r.nextDouble() * 4) - 2, 
                            (r.nextDouble() * 4) - 2, r.nextDouble() * 360, 
                            (r.nextDouble() * 4) - 2, this.level + 1, s));
            }
        }

        asteroids.remove(index);
        
    }


    public double getSize() {
        return size;
    }

}