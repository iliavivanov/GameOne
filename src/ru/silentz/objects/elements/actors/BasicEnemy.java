package ru.silentz.objects.elements.actors;

import ru.silentz.main.Game;
import ru.silentz.util.Util;
import ru.silentz.objects.mechanics.Bounded;
import ru.silentz.objects.mechanics.Depth;
import ru.silentz.objects.mechanics.listeners.Collisionables;
import ru.silentz.objects.mechanics.observables.CollisionObservable;
import ru.silentz.objects.mechanics.observables.RenderObservable;
import ru.silentz.objects.mechanics.observables.TickObservable;

import java.awt.*;

public class BasicEnemy<T extends RenderObservable & TickObservable & CollisionObservable> extends Actor implements Collisionables {

    private final T collisionObserver;

    public BasicEnemy(ID id, int x, int y, T t) {
        this(id, x, y, Depth.DepthFactory.enemy(), t);
    }

    protected BasicEnemy(ID id, int x, int y, Depth depth, T t) {
        super(id, x, y, depth, t);

        setWidth(32);
        setHeight(32);

        setSpeedX(-5);
        setSpeedY(-5);

        collisionObserver = t;
        t.addCollisionable(this);
    }

    @Override
    public void onRender(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void onTick() {
        setX(Util.clamp(getX() + getSpeedX(), 0, Game.WIDTH - getWidth()));
        setY(Util.clamp(getY() + getSpeedY(), 0, Game.HEIGHT - getHeight()));

        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());

        if (getX() <= 0 || getX() >= Game.WIDTH - getWidth())
            setSpeedX(-getSpeedX());
        if (getY() <= 0 || getY() >= Game.HEIGHT - getHeight())
            setSpeedY(-getSpeedY());

        new Trail(ID.TRAIL, getX(), getY(), getWidth(), getHeight(), 0.1f,
                Color.RED, Depth.DepthFactory.enemy(), getRenderObserver());
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
        setSpeedX(-getSpeedX());
        setSpeedY(-getSpeedY());
        actor.setSpeedX(-actor.getSpeedX());
        actor.setSpeedY(-actor.getSpeedY());
    }

    @Override
    public CollisionObservable getCollisionObserver() {
        return collisionObserver;
    }
}
