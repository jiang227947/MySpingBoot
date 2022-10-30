package jiangziyi.dao;

import jiangziyi.pojo.User;
import jiangziyi.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 告诉springboot这是一个mybatis类
@Repository // 将userDao交由spring容器管理
public interface UserDao {
    // 查询所有用户
    public List<User> listUser();

    // 根据id查询用户
    public User queryUserById(Integer id);

    // 根据用户名查询用户 并分页展示
    public List<User> listUserByName(UserQuery userQuery);

    // 根据id删除用户
    public boolean deleteUserById(Integer id);
}
