package ru.silentz.objects.elements.actors;

import ru.silentz.objects.elements.AbstractElement;
import ru.silentz.objects.mechanics.Depth;
import ru.silentz.objects.mechanics.observables.RenderObservable;
import ru.silentz.objects.mechanics.observables.TickObservable;

public abstract class Actor<T extends RenderObservable & TickObservable> extends AbstractElement {

    private final ID id;
    private int speedX = 0;
    private int speedY = 0;

    public Actor(ID id, int x, int y, Depth depth, T t) {
        super(x, y, depth, t);
        this.id = id;
    }

    protected ID getId() {
        return id;
    }

    protected int getSpeedX() {
        return speedX;
    }

    protected void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    protected int getSpeedY() {
        return speedY;
    }

    protected void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public static enum ID {
        PLAYER(),
        PLAYER_2(),
        ENEMY(),
        TRAIL()
    }
}
