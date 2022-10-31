package jiangziyi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jiangziyi.dao.UserDao;
import jiangziyi.pojo.User;
import jiangziyi.pojo.query.UserQuery;
import jiangziyi.sys.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 交由spring容器管理
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    // 查询所有用户
    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    // select注解查询所有用户
    @Override
    public List<User> findALLUserMyBatis() {
        return userDao.findALLUserMyBatis();
    }

    // 根据id查询用户
    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    // 根据用户名查询用户 并分页展示
    @Override
    public List<User> listUserByName(UserQuery userQuery) {
        return userDao.listUserByName(userQuery);
    }

    // 根据id删除用户
    @Override
    public int deleteUser(Integer id) {
        return this.userDao.deleteUser(id);
    }

    // 新增用户
    @Override
    public boolean addUser(User user) {
        return this.userDao.addUser(user);
    }

    // 根据id修改用户
    @Override
    public boolean updateUser(User user) {
        return this.userDao.updateUser(user);
    }

    // 根据id修改用户
    @Override
    public boolean updateUserMyBatis(User user) {
        return this.userDao.updateUserMyBatis(user);
    }
}
