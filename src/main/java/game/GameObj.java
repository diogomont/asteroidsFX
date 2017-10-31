package game;

import settings.Settings;

public class GameObj {

	double x;
	double y;
	double speedX;
	double speedY;
	double angle;
	double cos;
    double sin;

    Settings s;


    public GameObj(double x, double y, double speedX, double speedY,
    				double angle, Settings s) {
    	this.x = x;
    	this.y = y;
    	this.speedX = speedX;
    	this.speedY = speedY;
    	this.angle = angle;
    	this.cos = Math.cos(Math.toRadians(angle));
    	this.sin = Math.sin(Math.toRadians(angle));
    	this.s = s;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getAngle() {
        return angle;
    }

    public Settings getSettings() {
        return s;
    }



    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setAngle(double angle) {
        this.angle = angle;
        this.cos = Math.cos(Math.toRadians(angle));
        this.sin = Math.sin(Math.toRadians(angle));
    }

    public void setSettings(Settings s) {
        this.s = s;
    }

}