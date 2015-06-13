package ru.silentz.objects.mechanics.listeners;

import ru.silentz.objects.mechanics.observables.KeyObservable;

import java.awt.event.KeyEvent;

public interface KeyListener {

    public KeyObservable getKeyObserver();

    public void onKeyPressed(KeyEvent e);

    public void onKeyReleased(KeyEvent e);

}
