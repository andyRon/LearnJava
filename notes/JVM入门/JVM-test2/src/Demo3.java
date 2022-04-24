import java.util.ArrayList;

// -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
public class Demo3 {

    byte[] arr = new byte[1*1024*124];

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        int count = 0;

        try {
            while (true) {
                list.add(new Demo3());
                count++;
            }
        } catch (Error e) {
            System.out.println("count:" + count);
            e.printStackTrace();
        }
    }
}
