package ru.silentz.objects.mechanics.listeners;

import ru.silentz.objects.mechanics.observables.TickObservable;

public interface Tickable {

    public TickObservable getTickObserver();

    public void onTick();

}
