package settings;
public class Settings {

	private int frameWidth;
	private int frameHeight;
    private int menuHeight;
	private int playerSize;
	private double accel;
	private double bounce;
	private double friction;


    private static final int DEF_FRAME_WIDTH = 800;
    private static final int DEF_FRAME_HEIGHT = 800;
    private static final int DEF_MENU_HEIGHT = 100;
    private static final int DEF_PLAYER_SIZE = 30;
    private static final int DEF_MAX_BULLETS = 6;
    private static final double DEF_ACCEL_RATE = 0.05;
	private static final double DEF_BOUNCE = 1;
	private static final double DEF_FRICTION = 0.0035;
    private static final int BULLET_LIFESPAN = 80; //Move these to settings file?
    private static final double BULLET_SPEED = 5;




	public Settings() {
		frameWidth = DEF_FRAME_WIDTH;
		frameHeight = DEF_FRAME_HEIGHT;
        menuHeight = DEF_MENU_HEIGHT;
		playerSize = DEF_PLAYER_SIZE;
		accel = DEF_ACCEL_RATE;
		bounce = DEF_BOUNCE;
		friction = DEF_FRICTION;
	}

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getMenuHeight() {
        return menuHeight;
    }

    public int getPlayerSize() {
        return playerSize;
    }

    public double getAccel() {
        return accel;
    }

    public double getBounce() {
        return bounce;
    }

    public double getFriction() {
        return friction;
    }

    public int maxBullets() {
        return DEF_MAX_BULLETS;
    }

    public int getBulletLifespan() {
	    return BULLET_LIFESPAN;
    }

    public double getBulletSpeed() {
	    return BULLET_SPEED;
    }


}