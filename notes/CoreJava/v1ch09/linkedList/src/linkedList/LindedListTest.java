package linkedList;

import java.util.*;

/**
 * 链表操作
 * @author Andy Ron
 */
public class LindedListTest {
    public static void main(String[] args) {

        List<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Jack");
        a.add("Tony");

        List<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) {
                aIter.next();
            }
            // 把b链表中元素，间隔添加到a链表中
            aIter.add(bIter.next());
        }
        //  [Amy, Bob, Jack, Doug, Tony, Frances, Gloria]
        System.out.println(a);

        bIter = b.iterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                // 间隔删除b中元素
                bIter.remove();
            }
        }
        // [Bob, Frances]
        System.out.println(b);

        // 删除a中包含在b中的元素
        a.removeAll(b);

        // [Amy, Jack, Doug, Tony, Gloria]
        System.out.println(a);
    }



}
