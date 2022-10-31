package jiangziyi.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jiangziyi.pojo.User;
import jiangziyi.pojo.query.UserQuery;
import jiangziyi.service.UserService;
import jiangziyi.sys.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
     * 查询所有用户
     * */
    @GetMapping("/listUser")
    @ResponseBody
    public ResultObj listUser() {
        return new ResultObj(200, "查询成功", userService.listUser());
    }

    /*
     * select注解查询所有用户
     * */
    @GetMapping("/findALLUserMyBatis")
    @ResponseBody
    public ResultObj findALLUserMyBatis() {
        return new ResultObj(200, "查询成功", userService.findALLUserMyBatis());
    }

    /*
     * 根据id查询用户
     * get
     * */
    @GetMapping("/queryUserById/{id}")
    public ResultObj queryUserById(@PathVariable("id") Integer id) {
        return new ResultObj(200, "查询成功", userService.queryUserById(id));
    }

    /*
     * 根据name查询用户
     * post
     * */
    @PostMapping("/listUserByName")
    @ResponseBody
    public PageInfo<User> listUserByName(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        return new PageInfo<User>(userService.listUserByName(userQuery));
    }

    /*
     * 根据id删除用户
     * */
    @GetMapping("/deleteUser/{id}")
    public ResultObj deleteUser(@PathVariable("id") Integer id) {
        int i = userService.deleteUser(id);
        if (i > 0) {
            return new ResultObj(200, "删除用户成功", null);
        } else {
            return new ResultObj(-1, "删除用户失败", null);
        }
    }

    /*
     * 新增用户
     * */
    @PostMapping("/addUser")
    public ResultObj addUser(User user, UserQuery userQuery) {
        Integer id = user.getId();
        userQuery.setName(user.getName());
        List<User> userList = userService.listUserByName(userQuery);
        if (id != null) {
            if (userList.size() == 0) {
                boolean b = userService.updateUser(user);
                if (b) {
                    return new ResultObj(200, "修改用户成功", null);
                } else {
                    return new ResultObj(-1, "修改用户失败", null);
                }
            } else {
                return new ResultObj(-1, "该用户名已存在", null);
            }
        } else {
            if (userList.size() == 0) {
                boolean b = userService.addUser(user);
                if (b) {
                    return new ResultObj(200, "新增用户成功", null);
                } else {
                    return new ResultObj(-1, "新增用户失败", null);
                }
            } else {
                return new ResultObj(-1, "该用户名已存在", null);
            }
        }
    }

    /*
     * 根据id修改用户
     * */
    @PostMapping("/updateUser")
    public ResultObj updateUser(User user) {
        boolean i = this.userService.updateUser(user);
        if (i) {
            return new ResultObj(200, "修改用户成功", null);
        } else {
            return new ResultObj(-1, "修改用户失败", null);
        }
    }

    /*
     * 根据id修改用户
     * */
    @PostMapping("/updateUserMyBatis")
    public ResultObj updateUserMyBatis(User user) {
        user.setName(user.getName());
        boolean i = this.userService.updateUserMyBatis(user);
        if (i) {
            return new ResultObj(200, "修改用户成功", null);
        } else {
            return new ResultObj(-1, "修改用户失败", null);
        }
    }
}
