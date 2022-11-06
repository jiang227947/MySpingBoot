package jiangziyi.pojo;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /*用户信息*/
    private Integer id; // id
    private String name;    //登录名
    private String userName;    // 用户名
    private String password; // 密码
    private String role;    // 角色
    private String roleName;    // 角色名称
    private String lastLoginTime;    // 最后登录时间

    /*token信息*/
    private SaTokenInfo saTokenInfo;   // token数据
}

