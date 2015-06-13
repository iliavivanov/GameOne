package ru.silentz.main;

import ru.silentz.actions.KeyInput;
import ru.silentz.objects.models.Model;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        new Window(WIDTH, HEIGHT, "Let's build a game!", this);
        addKeyListener(new KeyInput(Model.getInstance()));

        final Random r = new Random();

        Model.getInstance().addPlayer(WIDTH / 2 - 32, HEIGHT / 2 - 32);
//        Model.getInstance().addSecondPlayer(r.nextInt(WIDTH), r.nextInt(HEIGHT));

        for (int i = 0; i < 10; i++) {
            Model.getInstance().addEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT));
        }

        this.requestFocus();
    }

    public synchronized void start() {
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        Model.getInstance().render(g);

        g.dispose();
        bs.show();
    }

    private void tick() {
        Model.getInstance().tick();
    }
}
