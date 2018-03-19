import java.util.List;
import java.util.ArrayList;

/**
 * Class define tuple
 * @param <E> -- generic type of tuple
 * @author Ze Wang
 */
public class tuple<E> {
    /**Using ArrayList to Store tuple values*/
    private List<E> tuple;
    private int size;

    public tuple() {
        tuple = new ArrayList<>();
        size = 0;
    }

    public void addword(E s) {
        tuple.add(s);
        size++;
    }

    public E getWord(int n) {
        if (n < 0 || n >= size) {
            throw new IndexOutOfBoundsException();
        }
        return tuple.get(n);
    }

    public int getsize() {
        return size;
    }
}
