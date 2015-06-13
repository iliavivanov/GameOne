package ru.silentz.objects.mechanics.observables;

import ru.silentz.objects.mechanics.listeners.Tickable;

public interface TickObservable {

    public abstract void addTickable(Tickable tickable);

    public abstract void removeTickable(Tickable tickable);

}
