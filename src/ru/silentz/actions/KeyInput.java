package ru.silentz.actions;

import ru.silentz.objects.mechanics.listeners.KeyListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    // TODO add key shortcuts

    private KeyListener keyListener;

    public KeyInput(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        keyListener.onKeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyListener.onKeyReleased(e);
    }
}
