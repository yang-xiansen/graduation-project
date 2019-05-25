package cn.com.project.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 一些常用的公共方法
 * @author 
 * @ClassName: Comm
 * @Version 1.0
 * @ModifiedBy
 * @Copyright
 * @date 2015-7-7 下午07:14:04
 * @description
 */
public class Comm {
	/**
     * 获取登录用户的id
     * @author 
     * @title: getUserInfoId
     * @date 2019-2-7 下午07:13:45
     * @param request
     * @return String
     */
	public static Integer getUserInfoId(HttpServletRequest request) {
		return (Integer) request.getSession().getAttribute("id");
	}

    /**
    * 获取登录用户的name
    * @author 
    * @title: getUserInfoName
    * @date 2019-2-7 下午07:13:45
    * @param request
    * @return String
    */
	public static String getUserInfoName(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("account");
	}

    /**
    * 获取登录用户的角色
    * @author 
    * @title: getUserRole
    * @date 2019-2-7 下午07:13:45
    * @param request
    * @return String
    */
    public static String getUserRole(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("role");
    }
    
    /**
     * 获取登录用户的ip
     * @author 
     * @title: getUserRole
     * @date 2019-2-7 下午07:13:45
     * @param request
     * @return String
     */
    @SuppressWarnings("unused")
    public static String getIpAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }

    /**
     * 获取随机编号
     * @author 
     * @title: getBh
     * @date 2019-2-7 下午07:13:45
     * @param request
     * @return String
     */
    public static String getBh() {
        String[] beforeShuffle = new String[] { "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String number = afterShuffle.substring(1, 9);
        return number;
    }
}