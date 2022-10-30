package jiangziyi.service;

import com.github.pagehelper.PageInfo;
import jiangziyi.pojo.User;
import jiangziyi.pojo.query.UserQuery;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    // 查询所有用户
    public List<User> listUser();

    // 根据id查询用户
    public User queryUserById(Integer id);

    // 根据用户名查询用户 并分页展示
    public PageInfo<User> listUserByName(UserQuery userQuery);

    // 根据id删除用户
    public boolean deleteUserById(Integer id);
}