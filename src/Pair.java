import java.util.Objects;

/**
 * A simple class that holds pairs of objects
 *
 * @param <T> first class
 * @param <J> second class
 * @author ctremblay
 */
public class Pair<T,J> {
    private T x;
    private J y;
    public Pair(T x, J y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return x.equals(pair.x) && y.equals(pair.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public J getY() {
        return y;
    }

    public void setY(J y) {
        this.y = y;
    }
}
