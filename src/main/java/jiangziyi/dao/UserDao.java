package jiangziyi.dao;

import jiangziyi.pojo.User;
import jiangziyi.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 告诉springboot这是一个mybatis类
@Repository // 将userDao交由spring容器管理
public interface UserDao {
    // 查询所有用户
    List<User> listUser();

    //select注解查询所有用户
    @Select("select * from mybatis.user")
    List<User> findALLUserMyBatis();

    // 根据id查询用户
    User queryUserById(Integer id);

    // 根据name查询用户 并分页展示
    List<User> listUserByName(UserQuery userQuery);

    // 根据id删除用户
    int deleteUser(Integer id);

    // 新增用户
    boolean addUser(User user);

    // 根据id修改用户
    boolean updateUser(User user);

    // 根据id修改用户
    @Insert("insert into mybatis.user(name,pwd,perms)values(#{name},#{pwd},#{perms})")
    boolean updateUserMyBatis(User user);
}
