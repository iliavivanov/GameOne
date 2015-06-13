package ru.silentz.objects.elements.userinterface;

import ru.silentz.objects.elements.AbstractElement;
import ru.silentz.objects.mechanics.Depth;
import ru.silentz.objects.mechanics.observables.RenderObservable;
import ru.silentz.objects.mechanics.observables.TickObservable;

public abstract class AbstractInterfaceElement<T extends RenderObservable & TickObservable> extends AbstractElement {

    public AbstractInterfaceElement(T t) {
        super(0, 0, Depth.DepthFactory.userInterface(), t);
    }
}
