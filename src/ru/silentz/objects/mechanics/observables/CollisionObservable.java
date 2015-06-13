package ru.silentz.objects.mechanics.observables;

import ru.silentz.objects.mechanics.listeners.Collisionables;

public interface CollisionObservable {

    public abstract void addCollisionable(Collisionables collisionable);

    public abstract void removeCollisionable(Collisionables collisionable);

}
