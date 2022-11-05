package jiangziyi.service;

import jiangziyi.pojo.User;
import jiangziyi.pojo.query.UserQuery;

import java.util.List;

public interface UserService {
    // 查询所有用户
    List<User> listUser();

    // select注解查询所有用户
    List<User> findALLUserMyBatis();

    // 根据id查询用户
    User queryUserById(Integer id);

    // 根据用户名查询用户 并分页展示
    List<User> getUserByUserName(UserQuery userQuery);

    // 根据name查询用户
    User getUserByName(String name);

    // 根据id删除用户
    int deleteUser(Integer id);

    // 新增用户
    boolean addUser(User user);

    // 根据id修改用户
    boolean updateUser(User user);

    // 根据id修改用户
    boolean updateUserMyBatis(User user);
}