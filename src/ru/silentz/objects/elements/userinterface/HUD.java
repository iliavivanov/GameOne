package ru.silentz.objects.elements.userinterface;

import ru.silentz.util.Util;
import ru.silentz.objects.mechanics.Depth;
import ru.silentz.objects.mechanics.observables.RenderObservable;
import ru.silentz.objects.mechanics.observables.TickObservable;

import java.awt.*;

public class HUD<T extends RenderObservable & TickObservable> extends AbstractInterfaceElement {

    private static int HEALTH = 100;

    public HUD(T t) {
        super(t);
    }

    @Override
    public void onTick() {
        // Do nothing
    }

    @Override
    public void onRender(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, HEALTH * 2, 0)/*Color.GREEN*/);
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 200, 32);
    }

    public void increase(int delta) {
        HEALTH = Util.clamp(HEALTH + delta, 0, 100);
    }

    public void decrease(int delta) {
        HEALTH = Util.clamp(HEALTH - delta, 0, 100);
    }

    @Override
    public int getLayer() {
        return Depth.USER_INTERFACE;
    }

    @Override
    public void setLayer(int layer) {
        // Do nothing
    }
}
