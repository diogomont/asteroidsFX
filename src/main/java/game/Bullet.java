package game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import settings.Settings;

public class Bullet extends GameObj {

	private int framesAlive;
	private Settings s;


	public Bullet(double[] front, double[] shipSpeed, double angle, Settings s) {
		super(front[0], front[1], 0, 0, angle, s);
		this.framesAlive = 0;
		this.s = s;

		setSpeedX(s.getBulletSpeed() * cos + shipSpeed[0]);
		setSpeedY(s.getBulletSpeed() * sin + shipSpeed[1]);
	}

	public void draw(GraphicsContext gc, Settings s) {
		gc.setFill(Color.WHITE);
		gc.fillRect(x-1, y-1, 2, 2);
		gc.setFill(Color.BLACK);
		move(s);
	}

	public void move(Settings s) {
		x += speedX;
		y += speedY;

        if (x < 0) x = s.getFrameWidth();
        if (x > s.getFrameWidth()) x = 0;
        if (y < 0) y = s.getFrameHeight();
        if (y > s.getFrameHeight()) y = 0;

        framesAlive++;
	}

	public boolean isDead() {
		return framesAlive >= s.getBulletLifespan();
	}

	public int getAge() {
		return framesAlive;
	}
}