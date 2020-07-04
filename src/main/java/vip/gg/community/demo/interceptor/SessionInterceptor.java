//package vip.gg.community.demo.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * Creat by GG
// * Date on 2020/2/18  3:41 下午
// */
//@Service
//public class SessionInterceptor implements HandlerInterceptor {
//
//    @Autowired(required = false)
//    private UserMapper userMapper;//默认要求依赖对象必须存在
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null && cookies.length!=0){
//            for(Cookie cookie:cookies){
//                if(cookie.getName().equals("token")){
//                    String token = cookie.getValue();//token保存cookies中找到token的值
//                    UserExample userExample =  new UserExample();
//                    userExample.createCriteria().andTokenEqualTo(token);
//                    List<User> users = userMapper.selectByExample(userExample);//数据库找到同token的记录，保存为user
//                    if(users.size()!=0){
//                        request.getSession().setAttribute("user",users.get(0));
//                    }
//                    break;
//                }
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}
