package jiangziyi.controller;

import com.github.pagehelper.PageInfo;
import jiangziyi.pojo.User;
import jiangziyi.pojo.query.UserQuery;
import jiangziyi.service.UserService;
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
    public List<User> listUser() {
        return userService.listUser();
    }

    /*
     * 根据id查询用户
     * get
     * */
    @GetMapping("/queryUserById/{id}")
    public User queryUserById(@PathVariable("id") Integer id) {
        return userService.queryUserById(id);
    }

    /*
     * 根据name查询用户
     * post
     * */
    @PostMapping("/listUserByName")
    @ResponseBody
    public PageInfo<User> listUserByName(UserQuery userQuery) {
        return userService.listUserByName(userQuery);
    }

    /*
     * 根据id删除用户
     * */
    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable("id") Integer id) {
        boolean b = userService.deleteUserById(id);
        if (b) {
            return "删除用户成功";
        } else {
            return "删除用户失败";
        }
    }

}
