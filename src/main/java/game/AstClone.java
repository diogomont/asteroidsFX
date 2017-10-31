package game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import settings.Settings;

import java.util.ArrayList;
import java.util.Random;


public class AstClone extends Application {

    private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer timer;

    private Random r;
    private Settings s;
    private Ship ship;
    private ArrayList<Asteroid> asteroids;

    private long previousTime;

    public static void main(String[] args) {
    	launch(args);
    }

    @Override
    public void start(Stage stage) {
        s = new Settings();
        r = new Random();

        ship = new Ship(s);
        asteroids = new ArrayList<Asteroid>();

        for (int i = 0; i < 6; i++) { //Move all these parameters to settings file.
            asteroids.add(new Asteroid(r.nextDouble() * s.getFrameWidth(), 
                            r.nextDouble() * s.getFrameHeight(),
                            (r.nextDouble() * 4) - 2, (r.nextDouble() * 4) - 2, 
                            r.nextDouble() * 360, (r.nextDouble() * 4) - 2,
                            0, s));
        }
        



        canvas = new Canvas(s.getFrameWidth(),s.getFrameHeight());
        gc = canvas.getGraphicsContext2D();



        previousTime = System.currentTimeMillis();

        timer = new AnimationTimer() {
                @Override
                public void handle(long now) {

                    while (System.currentTimeMillis() - previousTime < 15) {;}

                    //System.out.println(System.currentTimeMillis()-previousTime);
                    
                    update();
                    
                    previousTime = System.currentTimeMillis();
                }
        };
        timer.start();


        root = new Group(canvas);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,s.getFrameWidth(),s.getFrameHeight());
    }


    private void update() {
        scene.setOnKeyPressed(event -> {
            ship.keyPressed(event.getCode());
        });

        scene.setOnKeyReleased(event -> {
            ship.keyReleased(event.getCode());
        });

        clear();
        checkCollisions();

        ship.draw(gc);

        for (Asteroid ast : asteroids) {
            ast.draw(gc);
        }
    }

    private void clear() {
        gc.setStroke(Color.BLACK);
        gc.fillRect(0,0,s.getFrameWidth(),s.getFrameHeight());

    }

    private void checkCollisions() {
        for (int i = 0; i < asteroids.size(); i++) {
            for (int j = 0; j < ship.getBullets().size(); j++) {
                if ((Math.pow(ship.getBullets().get(j).getX() - asteroids.get(i).getX(),2) +
                    Math.pow(ship.getBullets().get(j).getY() - asteroids.get(i).getY(),2)) < Math.pow(asteroids.get(i).getSize(),2)) {

                    System.out.println("COLLISION: " + ship.getBullets().get(j).getX() + " " + 
                            ship.getBullets().get(j).getY() + " " + asteroids.get(i).getX() + 
                            " " + asteroids.get(i).getY());

                    ship.getBullets().remove(j);
                    

                    asteroids.get(i).isHit(asteroids, i, r);
                    return;
                }


            }
        }
    }

}