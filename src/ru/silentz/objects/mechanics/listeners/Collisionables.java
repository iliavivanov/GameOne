package ru.silentz.objects.mechanics.listeners;

import ru.silentz.objects.elements.actors.Actor;
import ru.silentz.objects.mechanics.Bounded;
import ru.silentz.objects.mechanics.observables.CollisionObservable;

public interface Collisionables extends Bounded {

    public CollisionObservable getCollisionObserver();

    public abstract boolean resolve(Bounded bounded);

    public default boolean hasReactionOn(Actor actor) {
        // TODO проверить
        // Сам на себя не реагирует
        return this != actor;
    }

    public abstract void react(Actor actor);

}
