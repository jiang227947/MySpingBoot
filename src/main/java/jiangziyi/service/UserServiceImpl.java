package jiangziyi.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jiangziyi.dao.UserDao;
import jiangziyi.pojo.User;
import jiangziyi.pojo.query.UserQuery;
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

    // 根据id查询用户
    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    // 根据用户名查询用户 并分页展示
    @Override
    public PageInfo<User> listUserByName(UserQuery userQuery) {
        // 获取页码和展示数量
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        return new PageInfo<User>(userDao.listUserByName(userQuery));
    }

    // 根据id删除用户
    @Override
    public boolean deleteUserById(Integer id) {
        // i是受影响的数量
        boolean i = userDao.deleteUserById(id);
        return true;
    }
}
