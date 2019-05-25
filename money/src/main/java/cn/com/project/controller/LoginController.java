package cn.com.project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.project.domain.Log;
import cn.com.project.domain.User;
import cn.com.project.service.LoginService;
import cn.com.project.service.UserService;
import cn.com.project.utils.Comm;
import cn.com.project.utils.DateUtils;

/**
 * 
 * @描述:登陆操作
 * @作者:
 * @时间 2018年3月14日 下午1:42:56
 * @获取一个: login
 * @返回值:ModelAndView
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

    @RequestMapping("/index")
    public ModelAndView a() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
	/**
	 * 
	 * @描述:后台管理员登陆
	 * @作者:
	 * @时间 2018年3月14日 下午1:42:56
	 * @获取一个: login
	 * @返回值:ModelAndView
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(Log l, String account, String password,
            String role, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		User u = loginService.login(account, password);
        if (u != null) {
            l.setDlr(u.getAccount());
            l.setDlip(Comm.getIpAddress());
            l.setDlsj(DateUtils.GetNowDate());
        }
		//获取session
		HttpSession session = request.getSession();
		if(u == null){//登陆失败
			mav.addObject("message","用户名或密码错误");
            mav.setViewName("login");
		}else if("0".equals(u.getRole())&&"0".equals(role)){//系统管理员
            loginService.insertSelective(l);
			session.setAttribute("id",u.getId());
			session.setAttribute("account", u.getAccount());
			session.setAttribute("password", u.getPassword());
            session.setAttribute("role", u.getRole());
			mav.setViewName("admin/index");
        } else if ("1".equals(u.getRole()) && "1".equals(role)) {//经费管理员
            loginService.insertSelective(l);
            session.setAttribute("id", u.getId());
            session.setAttribute("account", u.getAccount());
            session.setAttribute("password", u.getPassword());
            session.setAttribute("role", u.getRole());
            mav.setViewName("user/index");
        } else if ("2".equals(u.getRole()) && "2".equals(role)) {//用户
            loginService.insertSelective(l);
            session.setAttribute("id", u.getId());
            session.setAttribute("account", u.getAccount());
            session.setAttribute("password", u.getPassword());
            session.setAttribute("role", u.getRole());
            mav.setViewName("yh/index");
		}else{
			request.setAttribute("message", "用户所属角色不统一");
            mav.setViewName("login");
		}
		return mav;
	}
	
	/**
	 * 
	 * @描述:修改密码
	 * @作者:
	 * @时间 2018年3月15日 下午5:39:37
	 * @获取一个: toadd
	 * @返回值:ModelAndView
	 */
	@RequestMapping(value="/updatePassword")
	public ModelAndView updatePassword(HttpServletRequest request,String gpassword)throws Exception{
		ModelAndView mav = new ModelAndView();
		Integer id = Comm.getUserInfoId(request) ;
		User a = new User();
		a.setId(id);
		a.setPassword(gpassword);
		userService.updatePassword(a);
		mav.setViewName("admin/password/updatePassword");
		return mav;
	}
}