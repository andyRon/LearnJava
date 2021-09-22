import com.andyron.dao.TeacherMapper;
import com.andyron.pojo.Teacher;
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
        for (Teacher teacher : sqlSession.getMapper(TeacherMapper.class).getTeacher()) {
            System.out.println(teacher);
        }
        sqlSession.close();
    }

    @Test
    public void getTeacherPlus() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Teacher teacherPlus = sqlSession.getMapper(TeacherMapper.class).getTeacherPlus(1);
        System.out.println(teacherPlus);

        sqlSession.close();
    }

    @Test
    public void getTeacherPlus2() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        Teacher teacherPlus = sqlSession.getMapper(TeacherMapper.class).getTeacherPlus2(1);
        System.out.println(teacherPlus);
        sqlSession.close();
    }
}
