import java.util.*;

/**
 * @author Andy Ron
 */
public class LindedListTest {
    public static void main(String[] args) {

        Collection<String> c = ...;
        Iterator<String> iter = c.iterator();
        while (iter.hasNext()) {
            String e = iter.next();
        }
        iter.forEachRemaining();
    }

    public static <E> boolean cotains(Collection<E> c, Object obj) {
        for (E element : c) {
            if (element.equals(obj))
                return true;
        }
        return false;
    }


}
