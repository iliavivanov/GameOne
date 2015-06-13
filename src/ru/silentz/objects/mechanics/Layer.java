package ru.silentz.objects.mechanics;

import java.util.Comparator;

public interface Layer extends Comparable<Layer> {

    public abstract int getLayer();

    public abstract void setLayer(int layer);

    public static final Comparator<Layer> ASC_COMPARATOR = new Comparator<Layer>() {
        @Override
        public int compare(Layer o1, Layer o2) {
            return Integer.compare(o1.getLayer(), o2.getLayer());
        }
    };

    public static final Comparator<Layer> DESC_COMPARATOR = new Comparator<Layer>() {
        @Override
        public int compare(Layer o1, Layer o2) {
            return Integer.compare(o2.getLayer(), o1.getLayer());
        }
    };
}
