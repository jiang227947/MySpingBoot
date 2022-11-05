package jiangziyi.config;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.stp.StpUtil;
import jiangziyi.sys.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 拦截器
 *
 * @author lqd
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", (request).getHeader("*"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 获取当前token（这个token获取的是请求头的token，也可以用 request 获取）
        String tokenValue = StpUtil.getTokenValue();
        // 根据token获取用户id（这里如果找不到id直接返回null，不会报错）
        String loginId = (String) StpUtil.getLoginIdByToken(tokenValue);
        log.info("getRequestURL === " + request.getRequestURL());
        //判断token是否过期：如果token的有效期还没到，但是activity-timeout已经过了，那么token就会失效
        if (!StpUtil.isLogin()) {
            response.setStatus(401);
            return false;
        }
        /**  记：2022-06-24 修改  开始  */
        //判断token的创建时间是否大于2小时，如果是的话则需要刷新token
        long time = System.currentTimeMillis() - StpUtil.getSession().getCreateTime();
        long hour = time / 1000 / (60 * 60);
        if (hour > 1) {
            //这里要生成新的token的话，要先退出再重新登录
            //根据当前登录id（和设备）退出登录
            //StpUtil.logout(loginId,loginDevice);
            StpUtil.logout(loginId);
            //然后再重新登录，生成新的token
            //StpUtil.login(loginId,loginDevice);
            StpUtil.login(loginId);
            String newToken = StpUtil.getTokenValue();
            System.err.println("生成的新的token：" + newToken);
            response.setHeader("sa-token-authorization", newToken);
        }
        /**  记：2022-06-24 修改  结束  */
        // 获取过期时间
        long tokenTimeout = StpUtil.getTokenTimeout();
        //token没过期，过期时间不是-1的时候，每次请求都刷新过期时间
        if (tokenTimeout != -1) {
            SaTokenDao saTokenDao = SaManager.getSaTokenDao();
            saTokenDao.updateSessionTimeout(StpUtil.getSession().getId(), 3600);
            saTokenDao.updateTimeout(tokenValue, 3600);
        }
        // 检查通过后继续续签
        //StpUtil.updateLastActivityToNow();
        return true;
    }

    /**
     * 目标方法执行后
     * 该方法在控制器处理请求方法调用之后、解析视图之前执行
     * 可以通过此方法对请求域中的模型和视图做进一步修改
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 页面渲染后
     * 该方法在视图渲染结束后执行
     * 可以通过此方法实现资源清理、记录日志信息等工作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
