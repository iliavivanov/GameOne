package ru.silentz.objects.mechanics;

public class Depth implements Layer {
    public static final int INVISIBLE = -1;
    public static final int BACKGROUND = 0;
    public static final int PLAYER = 1;
    public static final int ENEMY = 2;
    public static final int USER_INTERFACE = 100;

    private int depth;

    private Depth(int depth) {
        this.depth = depth;
    }

    @Override
    public int getLayer() {
        return depth;
    }

    @Override
    public void setLayer(int layer) {
        this.depth = layer;
    }

    public static boolean isVisible(Depth depth) {
        return depth.getLayer() >= 0 && depth.getLayer() <= 100;
    }

    public static boolean isVisible(int layer) {
        return layer >= 0 && layer <= 100;
    }

    @Override
    public int compareTo(Layer other) {
        return Integer.compare(this.getLayer(), other.getLayer());
    }

    public static class DepthFactory {
        public static Depth invisible() {
            return newDepth(INVISIBLE);
        }

        public static Depth background() {
            return newDepth(BACKGROUND);
        }

        public static Depth player() {
            return newDepth(PLAYER);
        }

        public static Depth enemy() {
            return newDepth(ENEMY);
        }

        public static Depth userInterface() {
            return newDepth(USER_INTERFACE);
        }

        public static Depth newDepth(int depth) {
            return new Depth(depth);
        }
    }

}
