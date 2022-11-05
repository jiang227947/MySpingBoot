package jiangziyi.controller;

import cn.dev33.satoken.stp.StpUtil;
import jiangziyi.pojo.LoginUser;
import jiangziyi.pojo.User;
import jiangziyi.service.UserService;
import jiangziyi.sys.ResultObj;
import jiangziyi.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Slf4j
@RestController
//@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * StpUtil.checkLogin()；    // 如果当前会话未登录，这句代码会抛出 `NotLoginException` 异常
     * StpUtil.login(10001);    // 标记当前会话登录的账号id
     * StpUtil.getLoginId();    // 获取当前会话登录的账号id
     * StpUtil.isLogin();    // 获取当前会话是否已经登录, 返回true或false
     * StpUtil.logout();    // 当前会话注销登录
     * StpUtil.kickout(10001);    // 将账号为10001的会话踢下线
     * StpUtil.hasRole("super-admin");    // 查询当前账号是否含有指定角色标识, 返回true或false
     * StpUtil.hasPermission("user:add");    // 查询当前账号是否含有指定权限, 返回true或false
     * StpUtil.getSession();    // 获取当前账号id的Session
     * StpUtil.getSessionByLoginId(10001);    // 获取账号id为10001的Session
     * StpUtil.getTokenValueByLoginId(10001);    // 获取账号id为10001的token令牌值
     * StpUtil.login(10001, "PC");    // 指定设备标识登录，常用于“同端互斥登录”
     * StpUtil.kickout(10001, "PC");    // 指定账号指定设备标识踢下线 (不同端不受影响)
     * StpUtil.openSafe(120);    // 在当前会话开启二级认证，有效期为120秒
     * StpUtil.checkSafe();    // 校验当前会话是否处于二级认证有效期内，校验失败会抛出异常
     * StpUtil.switchTo(10044);    // 将当前会话身份临时切换为其它账号
     */

    // 登录
    @PostMapping("/login")
    public ResultObj login(@RequestBody LoginUser loginUser) {
        log.info("login === " + loginUser.getName());
        // 匹配账号密码
//        return passwordError(loginUser.getName(), loginUser.getPassword());
        return passwordError(loginUser.getName(), loginUser.getPassword());
    }

    // 退出登录
    @PostMapping("/loginOut")
    public ResultObj loginOut(@RequestBody User user) {
        StpUtil.logout(user.getId());
        return new ResultObj(200, "退出成功", null);
    }

    /**
     * 密码验证
     * todo 暂时直接比对数据库明文密码 2022/11/3
     */
    private ResultObj passwordError(String name, String password) {
        // 查询用户
        User user = userService.getUserByName(name);
        if (user == null) {
            // 用户不存在
            return new ResultObj(-1, "用户不存在", null);
        }
        // 根据前端输入的密码（明文），和加密的密码进行比较，判断输入的密码是否正确
        // 私钥加密
        // password = RSAUtil.encrypt(password);
        // 判断name是否跟密码匹配
        if (Objects.equals(name, user.getName()) && Objects.equals(user.getPassword(), password)) {
            // 密码验证成功
            StpUtil.login(user.getId());
            // 设置返回的用户信息和token
            user.setSaTokenInfo(StpUtil.getTokenInfo());
            return new ResultObj(200, "登录成功", user);
        } else {
            // 密码错误
            return new ResultObj(-1, "密码错误", null);
        }
    }

}
