package ru.silentz.objects.mechanics.listeners;

import ru.silentz.objects.mechanics.Layer;
import ru.silentz.objects.mechanics.observables.RenderObservable;

import java.awt.*;

public interface Renderable extends Layer {

    public RenderObservable getRenderObserver();

    public void onRender(Graphics g);

}
