package ru.silentz.objects.mechanics.observables;

import ru.silentz.objects.mechanics.listeners.KeyListener;

public interface KeyObservable {

    public abstract void addKeyListener(KeyListener listener);

    public abstract void removeKeyListener(KeyListener listener);

}
