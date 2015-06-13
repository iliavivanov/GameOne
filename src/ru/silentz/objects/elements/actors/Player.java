package ru.silentz.objects.elements.actors;

import ru.silentz.main.Game;
import ru.silentz.util.Util;
import ru.silentz.objects.elements.userinterface.HUD;
import ru.silentz.objects.mechanics.Bounded;
import ru.silentz.objects.mechanics.listeners.Collisionables;
import ru.silentz.objects.mechanics.Depth;
import ru.silentz.objects.mechanics.listeners.KeyListener;
import ru.silentz.objects.mechanics.observables.CollisionObservable;
import ru.silentz.objects.mechanics.observables.KeyObservable;
import ru.silentz.objects.mechanics.observables.RenderObservable;
import ru.silentz.objects.mechanics.observables.TickObservable;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player<T extends RenderObservable & KeyObservable & CollisionObservable & TickObservable> extends Actor implements Collisionables, KeyListener {

    private final T keyObserver;
    private final T collisionObserver;

    private final HUD hud;

    public Player(ID id, int x, int y, HUD hud, T t) {
        super(id, x, y, Depth.DepthFactory.player(), t);
        this.hud = hud;

        setWidth(32);
        setHeight(32);

        keyObserver = t;
        collisionObserver = t;

        t.addKeyListener(this);
        t.addCollisionable(this);
    }

    @Override
    public boolean resolve(Bounded bounded) {
        return getBounds().intersects(bounded.getBounds());
    }

    @Override
    public boolean hasReactionOn(Actor actor) {
        return Collisionables.super.hasReactionOn(actor) && actor.getId() == ID.ENEMY;
    }

    @Override
    public void react(Actor actor) {
        hud.decrease(2);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (getId() == ID.PLAYER) {
            if (key == KeyEvent.VK_W) setSpeedY(-5);
            if (key == KeyEvent.VK_S) setSpeedY(5);
            if (key == KeyEvent.VK_A) setSpeedX(-5);
            if (key == KeyEvent.VK_D) setSpeedX(5);
        }
        if (getId() == ID.PLAYER_2) {
            if (key == KeyEvent.VK_UP) setSpeedY(-5);
            if (key == KeyEvent.VK_DOWN) setSpeedY(5);
            if (key == KeyEvent.VK_LEFT) setSpeedX(-5);
            if (key == KeyEvent.VK_RIGHT) setSpeedX(5);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (getId() == ID.PLAYER) {
            if (key == KeyEvent.VK_W) setSpeedY(0);
            if (key == KeyEvent.VK_S) setSpeedY(0);
            if (key == KeyEvent.VK_A) setSpeedX(0);
            if (key == KeyEvent.VK_D) setSpeedX(0);
        }
        if (getId() == ID.PLAYER_2) {
            if (key == KeyEvent.VK_UP) setSpeedY(0);
            if (key == KeyEvent.VK_DOWN) setSpeedY(0);
            if (key == KeyEvent.VK_LEFT) setSpeedX(0);
            if (key == KeyEvent.VK_RIGHT) setSpeedX(0);
        }
    }

    @Override
    public void onTick() {
        setX(Util.clamp(getX() + getSpeedX(), 0, Game.WIDTH - getWidth()));
        setY(Util.clamp(getY() + getSpeedY(), 0, Game.HEIGHT - getHeight()));

        new Trail(ID.TRAIL, getX(), getY(), getWidth(), getHeight(), 0.05f,
                Color.WHITE, Depth.DepthFactory.enemy(), getRenderObserver());
    }

    @Override
    public void onRender(Graphics g) {
        switch (getId()) {
            case PLAYER:
                g.setColor(Color.WHITE);
                break;
            case PLAYER_2:
                g.setColor(Color.BLUE);
                break;
        }
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public KeyObservable getKeyObserver() {
        return keyObserver;
    }

    @Override
    public CollisionObservable getCollisionObserver() {
        return collisionObserver;
    }
}
