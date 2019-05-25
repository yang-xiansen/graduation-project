package cn.com.project.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class WebInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object o) throws Exception {
        Integer loginId = (Integer) Comm.getUserInfoId(request);//获取登录用户的id
        if (loginId == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            StringBuilder sb = new StringBuilder();
            sb.append(" <script type='text/javascript'> ");
            sb.append(" alert('请先登录');");
            sb.append(" window.top.location.href='");
            sb.append(request.getContextPath() + "/index/login.jsp");
            sb.append("';");
            sb.append(" </script> ");

            out.write(sb.toString());

            out.flush();
            out.close();

            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Object o,
            Exception e) throws Exception {

    }

}
