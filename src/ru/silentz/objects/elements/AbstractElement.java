package ru.silentz.objects.elements;

import ru.silentz.objects.mechanics.Depth;
import ru.silentz.objects.mechanics.Layer;
import ru.silentz.objects.mechanics.observables.RenderObservable;
import ru.silentz.objects.mechanics.observables.TickObservable;

import java.awt.*;

public abstract class AbstractElement<T extends RenderObservable & TickObservable> implements Element {

    private final T renderObserver;
    private final T tickObserver;

    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private final Depth depth;

    public AbstractElement(int x, int y, Depth depth, T t) {
        this.x = x;
        this.y = y;
        this.depth = depth;

        renderObserver = t;
        tickObserver = t;

        t.addRenderable(this);
        t.addTickable(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public final Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public int getLayer() {
        return depth.getLayer();
    }

    @Override
    public void setLayer(int layer) {
        this.depth.setLayer(layer);
    }

    @Override
    public int compareTo(Layer other) {
        return Integer.compare(this.getLayer(), other.getLayer());
    }

    @Override
    public RenderObservable getRenderObserver() {
        return renderObserver;
    }

    @Override
    public TickObservable getTickObserver() {
        return tickObserver;
    }

}
