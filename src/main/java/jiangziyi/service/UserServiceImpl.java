package jiangziyi.service;

import jiangziyi.dao.UserDao;
import jiangziyi.pojo.User;
import jiangziyi.pojo.query.PageParams;
import jiangziyi.pojo.query.UserQuery;
import jiangziyi.sys.ResultList;
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
    public ResultList listUser(PageParams pageParams) {
        Integer begin = (pageParams.getPageNum() - 1) * pageParams.getPageSize();
        pageParams.setPageNum(begin);
        // 查询所有用户的数量
        Integer count = countQueryUser(pageParams);
        Integer totalPage = null;
        if (pageParams.getPageSize() == 0) {
            totalPage = 0;
        } else {
            totalPage = ((count + pageParams.getPageSize() - 1) / pageParams.getPageSize());
        }
        return new ResultList(200,
                "查询成功",
                userDao.listUser(pageParams),
                pageParams.getPageNum() + 1,
                pageParams.getPageSize(),
                totalPage,
                count);
    }

    //查询所有用户的数量
    @Override
    public Integer countQueryUser(PageParams pageParams) {
        return userDao.countQueryUser(pageParams);
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
    public List<User> getUserByUserName(UserQuery userQuery) {
        return userDao.getUserByUserName(userQuery);
    }

    // 根据name查询用户
    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
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

    // 根据id修改用户最后登录时间
    @Override
    public void updateUserLastLoginTime(Integer id, String lastLoginTime) {
        this.userDao.updateUserLastLoginTime(id, lastLoginTime);
    }

    // 根据id修改用户
    @Override
    public boolean updateUserMyBatis(User user) {
        return this.userDao.updateUserMyBatis(user);
    }
}
