package ru.silentz.objects.mechanics.observables;

import ru.silentz.objects.mechanics.listeners.Renderable;

public interface RenderObservable {

    public abstract void addRenderable(Renderable renderable);

    public abstract void removeRenderable(Renderable renderable);

}
