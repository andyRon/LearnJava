import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.type.JdbcType;
import org.junit.Test;

public class MyTest {
    @Test
    public void test() {
        JdbcType type = JdbcType.CLOB;
        System.out.println(type.ordinal());
//        for (JdbcType value : JdbcType.values()) {
//            System.out.println(value);
//        }
        System.out.println(type.TYPE_CODE);

        Environment.Builder dfdf = new Environment.Builder("dfdf");

    }
}
