package ru.silentz.objects.elements.actors;

import ru.silentz.objects.mechanics.Depth;
import ru.silentz.objects.mechanics.observables.RenderObservable;
import ru.silentz.objects.mechanics.observables.TickObservable;

import java.awt.*;

public class Trail<T extends RenderObservable & TickObservable> extends Actor {

    private final float life; // 0.001 - 0.1
    private float alpha = 1;
    private Color color;

    public Trail(ID id, int x, int y, int width, int height, float life, Color color, Depth depth, T t) {
        super(id, x, y, depth, t);
        this.color = color;
        this.life = life;

        setWidth(width);
        setHeight(height);
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    @Override
    public void onRender(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect(getX(), getY(), getWidth(), getHeight());

        g2d.setComposite(makeTransparent(1));
    }

    @Override
    public void onTick() {
        if (alpha > life) {
            alpha -= life - 0.01f;
            if (alpha < 0f)
                alpha = 0f;
        } else {
            getRenderObserver().removeRenderable(this);
            getTickObserver().removeTickable(this);
        }
    }
}
