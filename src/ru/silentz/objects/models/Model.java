package ru.silentz.objects.models;

import ru.silentz.objects.elements.actors.Actor;
import ru.silentz.objects.elements.actors.BasicEnemy;
import ru.silentz.objects.elements.actors.Player;
import ru.silentz.objects.elements.userinterface.HUD;
import ru.silentz.objects.mechanics.Depth;
import ru.silentz.objects.mechanics.listeners.Collisionables;
import ru.silentz.objects.mechanics.listeners.KeyListener;
import ru.silentz.objects.mechanics.listeners.Renderable;
import ru.silentz.objects.mechanics.listeners.Tickable;
import ru.silentz.objects.mechanics.observables.CollisionObservable;
import ru.silentz.objects.mechanics.observables.KeyObservable;
import ru.silentz.objects.mechanics.observables.RenderObservable;
import ru.silentz.objects.mechanics.observables.TickObservable;
import ru.silentz.util.OrderedList;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Model implements
        RenderObservable,
        TickObservable,
        KeyObservable, KeyListener,
        CollisionObservable {

    private static Model instance = new Model();

    private List<KeyListener> keyListenerList = new ArrayList<>();
    private OrderedList<Renderable> renderableList = new OrderedList<>();
    private List<Tickable> tickableList = new ArrayList<>();
    private List<Collisionables> collisionablesList = new ArrayList<>();

    public static Model getInstance() {
        return instance;
    }

    private Model() {
    }

    @Override
    public void addRenderable(Renderable renderable) {
        renderableList.orderedAdd(renderable);
    }

    @Override
    public void removeRenderable(Renderable renderable) {
        renderableList.remove(renderable);
    }

    public void render(Graphics g) {
        notifyRenderListeners(g);
    }

    private void notifyRenderListeners(Graphics g) {
        for (int i = 0; i < renderableList.size(); i++) {
            Renderable renderable = renderableList.get(i);
            if (Depth.isVisible(renderable.getLayer()))
                renderable.onRender(g);
        }
    }

    @Override
    public void addTickable(Tickable tickable) {
        tickableList.add(tickable);
    }

    @Override
    public void removeTickable(Tickable tickable) {
        tickableList.remove(tickable);
    }

    public void tick() {
        notifyTickListeners();
    }

    private void notifyTickListeners() {
        for (int i = 0; i < tickableList.size(); i++) {
            tickableList.get(i).onTick();
        }

        notifyCollisionListeners();
    }

    @Override
    public void addCollisionable(Collisionables collisionable) {
        collisionablesList.add(collisionable);
    }

    @Override
    public void removeCollisionable(Collisionables collisionable) {
        collisionablesList.remove(collisionable);
    }

    private void notifyCollisionListeners() {
        // TODO обдумать что здесь происходит
        for (int i = 0; i < collisionablesList.size(); i++) {
            Collisionables collisionable1 = collisionablesList.get(i);
            for (int j = 0; j < collisionablesList.size(); j++) {
                Collisionables collisionable2 = collisionablesList.get(j);
                // TODO
//                if (collisionable1.hasReactionOn(collisionable2) && collisionable1.resolve(collisionable2))
//                    collisionable1.react(collisionable2);
            }
        }
    }

    @Override
    public void addKeyListener(KeyListener listener) {
        keyListenerList.add(listener);
    }

    @Override
    public void removeKeyListener(KeyListener listener) {
        keyListenerList.remove(listener);
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        for (int i = 0; i < keyListenerList.size(); i++) {
            keyListenerList.get(i).onKeyPressed(e);
        }
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        for (int i = 0; i < keyListenerList.size(); i++) {
            keyListenerList.get(i).onKeyReleased(e);
        }
    }

    public void addPlayer(int x, int y) {
        HUD hud = new HUD(this);
        Player player = new Player(Actor.ID.PLAYER, x, y, hud, this);
    }

    public void addSecondPlayer(int x, int y) {
//        HUD hud = new HUD(this);
//        Player player = new Player(Actor.ID.PLAYER_2, x, y, hud, this);
    }

    public void addEnemy(int x, int y) {
        new BasicEnemy(Actor.ID.ENEMY, x, y, this);
    }

    @Override
    public KeyObservable getKeyObserver() {
        return null;
    }
}
