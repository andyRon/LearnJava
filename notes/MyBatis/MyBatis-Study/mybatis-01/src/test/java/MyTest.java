import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.type.JdbcType;
import org.junit.Test;

public class MyTest {
    @Test
    public void test() {
        JdbcType type = JdbcType.CLOB;
        for (JdbcType value : JdbcType.values()) {
            System.out.println(value.toString() + " " + value.ordinal() + " " +value.TYPE_CODE);
        }
    }
}
