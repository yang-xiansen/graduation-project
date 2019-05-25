package cn.com.project.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.project.domain.Log;
import cn.com.project.domain.User;
import cn.com.project.service.LoginService;
import cn.com.project.service.UserService;
import cn.com.project.utils.Comm;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

    @Autowired
    private LoginService loginService;
    /**
    * 
    * @描述:查看日志
    * @作者:
    * @时间 2018年3月15日 下午5:39:37
    * @获取一个: toadd
    * @返回值:ModelAndView
    */
    @RequestMapping(value = "/selLg")
    public ModelAndView selLg(Integer page, Log l) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Log> list = loginService.select(l);
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 50, true);
        PageInfo<Log> pageInfo = new PageInfo<Log>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("lastPage", pageInfo.getLastPage());
        mav.setViewName("admin/log/sel");
        return mav;
    }

    /**
     * 
     * @描述:删除日志
     * @作者:
     * @时间 2018年3月15日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/deleteLg")
    public String deleteLg(Integer id) throws Exception {

        loginService.deleteByLg(id);

        return "redirect:/user/selLg";
    }
	/**
	 * 
	 * @描述:保存用户
	 * @作者:
	 * @时间 2018年3月15日 下午5:39:37
	 * @获取一个: toadd
	 * @返回值:ModelAndView
	 */
	@RequestMapping(value="/saveUser")
    public String saveUser(MultipartFile photo,
			String account,
			String password,
			String age,
			String role,
			String address,
			String tel,
			String chexing,
			String jsnumber,
			String idk
	)throws Exception{
			User u = new User();
		//第一步：判断文件是否为空 ,MultipartFile photo
		if(photo!= null){
			InputStream is = photo.getInputStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int length = -1;
			while ((length=is.read(b))!=-1){
				os.write(b,0,length);
			}
			u.setPhoto(os.toByteArray());
			u.setAccount(account);
			u.setAddress(address);
			u.setAge(age);
			u.setChexing(chexing);
			u.setIdk(idk);
			u.setJsnumber(jsnumber);
			u.setPassword(password);
            u.setRole("1");
			u.setTel(tel);
			u.setState("0");
			//优化
			userService.insert(u);
			is.close();
			os.flush();
			os.close();
		}
        return "redirect:/user/selUser";
	}
	
    /**
     * 
     * @描述:保存用户
     * @作者:
     * @时间 2018年3月15日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/saveUser1")
    public ModelAndView saveUser1(MultipartFile photo, String account,
            String password, String age, String role, String address,
            String tel, String chexing, String jsnumber,
            String idk) throws Exception {
        User u = new User();
        //第一步：判断文件是否为空 ,MultipartFile photo
        if (photo != null) {
            InputStream is = photo.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int length = -1;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
            u.setPhoto(os.toByteArray());
            u.setAccount(account);
            u.setAddress(address);
            u.setAge(age);
            u.setChexing(chexing);
            u.setIdk(idk);
            u.setJsnumber(jsnumber);
            u.setPassword(password);
            u.setRole("2");
            u.setTel(tel);
            u.setState("0");
            //优化
            userService.insert(u);
            is.close();
            os.flush();
            os.close();
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("yh/myinfo/success");
        return mav;
    }

    /**
     * 
     * @throws Exception 
     * @描述:显示头像信息
     * @作者:
     * @时间 2015年11月28日 上午10:42:02
     * @获取一个: role
     * @返回值:ModelAndView
     */
	@RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	@ResponseBody
	public void toShowPhoto(@PathVariable Integer id,HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		OutputStream os = null;
		InputStream is = null;
		try {
			os = response.getOutputStream();//获取输出流
			//读取用户信息
			User u  = userService.selectByPrimaryKey(id);//映射文件中需要查询该条信息
			if(u.getPhoto()!=null){
				is = new ByteArrayInputStream(u.getPhoto());//字节数转换为输入流
				byte[] bytes = new byte[1024];//设置缓存区
				int length = -1;
				while ((length=is.read(bytes))!=-1){//读
					os.write(bytes,0,length);//写
				}
			}
			//关闭操作
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.flush();
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 
     * @描述:查看经费管理员信息
     * @作者:
     * @时间 2018年3月15日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selUser")
	public ModelAndView selHc(Integer page,User u)throws Exception{
		ModelAndView mav = new ModelAndView();
        u.setRole("1");
		List<User> userList = userService.selectUserList(u);
		//分页
		if (page == null) {
			page = 1;
		}
		//分页		
		PageHelper.startPage(page,10,true);
		PageInfo<User> pageInfo = new PageInfo<User>(userList); 
	  
		mav.addObject("userList",userList);
		mav.addObject("page", new PageInfo(userList));
		mav.addObject("count", pageInfo.getTotal());
		mav.addObject("countPage", pageInfo.getPages());
		mav.addObject("lastPage", pageInfo.getLastPage());
		mav.setViewName("admin/user/selUser");
		return mav;
	}

    /**
     * 
     * @描述:查看用户信息
     * @作者:
     * @时间 2018年3月15日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selUser1")
    public ModelAndView selUser1(Integer page, User u) throws Exception {
        ModelAndView mav = new ModelAndView();
        u.setRole("2");
        List<User> userList = userService.selectUserList(u);
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 10, true);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);

        mav.addObject("userList", userList);
        mav.addObject("page", new PageInfo(userList));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("lastPage", pageInfo.getLastPage());
        mav.setViewName("admin/user/selUser1");
        return mav;
    }

	/**
	 * 
	 * @描述:根据id显示
	 * @作者:
	 * @时间 2018年3月15日 下午5:39:37
	 * @获取一个: toadd
	 * @返回值:ModelAndView
	 */
	@RequestMapping(value="/showHc")
	public ModelAndView showHc(Integer id)throws Exception{
		ModelAndView mav = new ModelAndView();
		User u  = userService.selectByPrimaryKey(id);
		mav.addObject("user", u);
        mav.setViewName("user/myinfo/updateUser");
		return mav;
	}
	
    /**
     * 
     * @描述:根据id显示
     * @作者:
     * @时间 2018年3月15日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showHc1")
    public ModelAndView showHc1(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        User u = userService.selectByPrimaryKey(id);
        mav.addObject("user", u);
        mav.setViewName("admin/user/updateUser");
        return mav;
    }
    
    /**
     * 
     * @描述:根据id显示
     * @作者:
     * @时间 2018年3月15日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showHc2")
    public ModelAndView showHc2(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        User u = userService.selectByPrimaryKey(id);
        mav.addObject("user", u);
        mav.setViewName("user/myinfo/updateUser");
        return mav;
    }

    /**
     * 
     * @描述:删除
     * @作者:
     * @时间 2018年3月15日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
	@RequestMapping(value="/deleteHc")
	public String deleteUser(Integer id)throws Exception{
		
		userService.deleteByPrimaryKey(id);
		
        return "redirect:/user/selUser";
	}

    /**
    * 
    * @描述:删除
    * @作者:
    * @时间 2018年3月15日 下午5:39:37
    * @获取一个: toadd
    * @返回值:ModelAndView
    */
    @RequestMapping(value = "/deleteHc1")
    public String deleteUser1(Integer id) throws Exception {

        userService.deleteByPrimaryKey(id);

        return "redirect:/user/selUser1";
    }
	/**
	 * 
	 * @描述:查看个人信息
	 * @作者:
	 * @时间 2018年3月15日 下午5:39:37
	 * @获取一个: toadd
	 * @返回值:ModelAndView
	 */
    @RequestMapping(value = "/selUserXx")
	public ModelAndView selHcXx(User u ,HttpServletRequest request)throws Exception{
        String role = Comm.getUserRole(request);
		Integer id = Comm.getUserInfoId(request);
		u.setId(id);
		ModelAndView mav = new ModelAndView();
		List<User> userList = userService.selectUserList(u);
		mav.addObject("u", userList);
        if ("1".equals(role)) {
            mav.setViewName("user/myinfo/selUser");
        } else if ("2".equals(role)) {
            mav.setViewName("yh/myinfo/selUser");
        }

		return mav;
	}

	/**
	 * 
	 * @描述:保存更新个人信息
	 * @作者:
	 * @时间 2018年3月15日 下午5:39:37
	 * @获取一个: toadd
	 * @返回值:ModelAndView
	 */
	@RequestMapping(value="/saveUpdateUser")
	public String saveUser(User u)throws Exception{
		userService.updateByPrimaryKey(u);
        return "redirect:/user/selUserXx";
	}

    /**
     * 
     * @描述:管理员修改用户信息
     * @作者:
     * @时间 2018年3月15日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/saveUpdateUser1")
    public String saveUpdateUser1(User u) throws Exception {
        userService.updateByPrimaryKey(u);

        return "redirect:/user/selUser";
    }
}
