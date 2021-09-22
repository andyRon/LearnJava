import com.andyron.dao.UserMapper;
import com.andyron.pojo.User;
import com.andyron.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author Andy Ron
 */
public class MyTest {


    @Test
    public void test() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user1 = mapper.queryUserById(1);
        System.out.println(user1);

//        sqlSession.clearCache();
        System.out.println("======================");

        User user2 = mapper.queryUserById(1);
        System.out.println(user2);


        System.out.println(user1 == user2);
        sqlSession.close();
    }

    @Test
    public void test2() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        SqlSession sqlSession2 = MyBatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);
        sqlSession.close();

        System.out.println("================");

        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.queryUserById(1);
        System.out.println(user2);
        sqlSession2.close();

        System.out.println(user == user2);
    }
}
