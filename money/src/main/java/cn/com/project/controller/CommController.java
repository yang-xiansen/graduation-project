package cn.com.project.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.project.domain.Gonggao;
import cn.com.project.domain.Lxr;
import cn.com.project.domain.Message;
import cn.com.project.domain.Order;
import cn.com.project.domain.Qtgs;
import cn.com.project.domain.User;
import cn.com.project.service.CommService;
import cn.com.project.service.UserService;
import cn.com.project.utils.Comm;
import cn.com.project.utils.DateUtils;

@Controller
@RequestMapping(value = "/comm")
public class CommController {
    @Autowired
    private CommService commService;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(CommController.class);
    
    /**
     * 
     * @描述: 经费申请统计
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selCountTc")
    public ModelAndView ddtj(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session, Order g) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Order> list = commService.select(g);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String,List<Integer>> maps = new HashMap<>();
        List<String> names = new ArrayList<>();
        for (Order row : list) {
            if (!names.contains(row.getXm())){
                names.add(row.getXm());
                maps.put(row.getXm(),new ArrayList<>());
            }
            maps.get(row.getXm()).add(Integer.parseInt(row.getMoney()));
        }
        for (Map.Entry<String,List<Integer>> entry:maps.entrySet()){
            Integer total = 0;
            for (Integer i:entry.getValue()){
                total+=i;
            }
            dataset.addValue(total, entry.getKey(), "");
        }
        JFreeChart chart = ChartFactory.createBarChart3D("经费申请统计", "项目名称",
            "金额（元）", dataset, PlotOrientation.VERTICAL, true, false, false);
        CategoryPlot plot = chart.getCategoryPlot();
        // 设置网格背景颜色
        plot.setBackgroundPaint(Color.white);
        // 设置网格竖线颜色
        plot.setDomainGridlinePaint(Color.pink);
        // 设置网格横线颜色
        plot.setRangeGridlinePaint(Color.pink);
        plot.setNoDataMessage("无数据显示");
        // 显示每个柱的数值，并修改该数值的字体属性
        BarRenderer3D renderer = new BarRenderer3D();
        renderer.setBaseItemLabelGenerator(
            new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);

        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
        renderer.setItemLabelAnchorOffset(10D);

        // 设置平行柱的之间距离
        renderer.setItemMargin(0.4);

        plot.setRenderer(renderer);
        String filename = ServletUtilities.saveChartAsPNG(chart, 800, 500, null,
            session);
        request.setAttribute("filename", filename);

        mav.setViewName("comm/count/selCount");
        return mav;
    }


    /**
     * 
     * @描述:添加公告
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addGg")
    public String add(Gonggao record,
            HttpServletRequest request) throws Exception {
        record.setFname(Comm.getUserInfoName(request));
        record.setFdate(DateUtils.GetNowDate());
        commService.insertSelective(record);
        return "redirect:/comm/selGg";
    }

    /**
     * 
     * @描述:查看公告信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selGg")
    public ModelAndView selFl(Integer page, Gonggao g,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        ModelAndView mav = new ModelAndView();
        List<Gonggao> list = commService.select(g);
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 10, true);
        PageInfo<Gonggao> pageInfo = new PageInfo<Gonggao>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("lastPage", pageInfo.getLastPage());
        if ("0".equals(role)) {
            mav.setViewName("comm/gg/sel");
        } else {
            mav.setViewName("comm/gg/sel1");
        }
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询公告
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showGg")
    public ModelAndView showGg(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Gonggao data = commService.selectByGg(id);

        mav.addObject("data", data);
        mav.setViewName("comm/gg/update");
        return mav;
    }

    /**
     * 
     * @描述:保存更新公告信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateGg")
    public String updateGg(Gonggao record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selGg";
    }

    /**
     * 
     * @描述:删除公告信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteGg")
    public String deleteGg(Integer id) throws Exception {

        commService.deleteByGg(id);

        return "redirect:/comm/selGg";
    }

    /**
     * 
     * @描述:添加消息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addXx")
    public String addXx(Message record,
            HttpServletRequest request) throws Exception {
        record.setFname(Comm.getUserInfoName(request));
        record.setFdate(DateUtils.GetNowDate());
        commService.insertSelective(record);
        return "redirect:/comm/selXx";
    }

    /**
     * 
     * @描述:查看消息信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selXx")
    public ModelAndView selXx(Integer page, Message g,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        ModelAndView mav = new ModelAndView();
        List<Message> list = commService.select(g);
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 10, true);
        PageInfo<Message> pageInfo = new PageInfo<Message>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("lastPage", pageInfo.getLastPage());
        if ("0".equals(role)) {
            mav.setViewName("comm/xx/sel");
        } else if ("1".equals(role)) {
            mav.setViewName("comm/xx/sel1");
        }
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询消息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showXx")
    public ModelAndView showXx(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Message data = commService.selectByXx(id);

        mav.addObject("data", data);
        mav.setViewName("comm/xx/update");
        return mav;
    }

    /**
     * 
     * @描述:保存更新消息信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateXx")
    public String updateGg(Message record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selXx";
    }

    /**
     * 
     * @描述:删除消息信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteXx")
    public String deleteXx(Integer id) throws Exception {

        commService.deleteByXx(id);

        return "redirect:/comm/selXx";
    }
    
    /**
     * 
     * @描述: 跳转页面
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/tz")
    public ModelAndView tzdc(User u, String type) throws Exception {
        ModelAndView mav = new ModelAndView();
        if ("dc".equals(type)) {
            u.setRole("2");
            List<User> userList = userService.selectUserList(u);
            mav.addObject("list", userList);
            mav.setViewName("comm/dc/add");
        } else if ("rc".equals(type)) {
            u.setRole("1");
            List<User> userList = userService.selectUserList(u);
            mav.addObject("list", userList);
            mav.setViewName("comm/rc/add");
        }
        return mav;
    }


    /**
     * 
     * @描述:添加联系人信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addLx")
    public String addLx(Lxr record,
            HttpServletRequest request) throws Exception {

        commService.insertSelective(record);
        return "redirect:/comm/selLx";
    }

    /**
     * 
     * @描述:查看联系人信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selLx")
    public ModelAndView selLx(Integer page, Lxr g,
            HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Lxr> list = commService.select(g);
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 10, true);
        PageInfo<Lxr> pageInfo = new PageInfo<Lxr>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("lastPage", pageInfo.getLastPage());
        mav.setViewName("comm/lxr/sel");
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询联系人
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showLx")
    public ModelAndView showLx(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Lxr data = commService.selectByLx(id);
        mav.addObject("data", data);
        mav.setViewName("comm/lxr/update");
        return mav;
    }

    /**
     * 
     * @描述:保存更新联系人信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateLx")
    public String updateLx(Lxr record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selLx";
    }

    /**
     * 
     * @描述:删除联系人信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteLx")
    public String deleteLx(Integer id) throws Exception {

        commService.deleteByLx(id);

        return "redirect:/comm/selLx";
    }

    /**
     * 
     * @描述:添加申报经费信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addDd")
    public String addLx(Order record,
            HttpServletRequest request) throws Exception {
        record.setAid(Comm.getUserInfoId(request));
        record.setState("0");
        commService.insertSelective(record);
        return "redirect:/comm/selDd";
    }

    /**
     * 
     * @描述:查看申报经费信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selDd")
    public ModelAndView selDd(Integer page, Order g,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        ModelAndView mav = new ModelAndView();
        if ("2".equals(role)) {
            g.setAid(Comm.getUserInfoId(request));
        }
        List<Order> list = commService.select(g);
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 10, true);
        PageInfo<Order> pageInfo = new PageInfo<Order>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("lastPage", pageInfo.getLastPage());
        mav.setViewName("comm/dd/sel");
        if ("0".equals(role) || "1".equals(role)) {
            mav.setViewName("comm/dd/sel");
        } else if ("2".equals(role)) {
            mav.setViewName("comm/dd/sel1");
        }
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询申报经费
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showDd")
    public ModelAndView showDd(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Order data = commService.selectByDd(id);
        mav.addObject("data", data);
        mav.setViewName("comm/dd/update");
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询申报经费
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showDd1")
    public void showDd1(Integer id) throws Exception {
        Order data = commService.selectByDd(id);
        data.setState(data.getMoney());
        data.setXm(null);
        data.setReason("0");
        commService.updateByPrimaryKeySelective(data);
    }

    @RequestMapping(value = "/rejected")
    public void rejected(Integer id,String reason) throws Exception {
        Order data = commService.selectByDd(id);
        data.setXm(null);
        data.setState("0");
        data.setReason(reason);
        commService.updateByPrimaryKeySelective(data);
    }

    /**
     * 
     * @描述:保存更新申报经费信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateDd")
    public String updateDd(Order record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selDd";
    }

    /**
     * 
     * @描述:删除申报经费信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteDd")
    public String deleteDd(Integer id) throws Exception {

        commService.deleteByDd(id);

        return "redirect:/comm/selDd";
    }


    /**
     * 
     * @描述:添加经费预算
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/addQt")
    public String addQt(Qtgs record,
            HttpServletRequest request) throws Exception {
        record.setLrsj(DateUtils.GetNowDate());
        record.setLname(Comm.getUserInfoName(request));
        commService.insertSelective(record);
        return "redirect:/comm/selQt";
    }

    /**
     * 
     * @描述:查看经费预算信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/selQt")
    public ModelAndView selQt(Integer page, Qtgs g,
            HttpServletRequest request) throws Exception {
        String role = Comm.getUserRole(request);
        ModelAndView mav = new ModelAndView();
        List<Qtgs> list = commService.select(g);
        for (Qtgs q:list){
            double total = 0.0;
            for (Order order:commService.selectByName(q.getGsmc())){
                total+=Double.parseDouble(order.getState() !=null?order.getState():"0");
            }
            q.setAmount(Double.parseDouble(q.getCjsl()) - total);
        }
        //分页
        if (page == null) {
            page = 1;
        }
        //分页        
        PageHelper.startPage(page, 10, true);
        PageInfo<Qtgs> pageInfo = new PageInfo<Qtgs>(list);

        mav.addObject("list", list);
        mav.addObject("page", new PageInfo(list));
        mav.addObject("count", pageInfo.getTotal());
        mav.addObject("countPage", pageInfo.getPages());
        mav.addObject("lastPage", pageInfo.getLastPage());
        if ("0".equals(role) || "1".equals(role)) {
            mav.setViewName("comm/sc/sel");
        } else {
            mav.setViewName("comm/sc/sel1");
        }
        return mav;
    }

    /**
     * 
     * @描述: 根据id查询经费预算
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/showQt")
    public ModelAndView showQt(Integer id) throws Exception {
        ModelAndView mav = new ModelAndView();
        Qtgs data = commService.selectByPrimaryKey(id);

        mav.addObject("data", data);
        mav.setViewName("comm/sc/update");
        return mav;
    }

    /**
     * 
     * @描述:保存更新经费预算信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: toadd
     * @返回值:ModelAndView
     */
    @RequestMapping(value = "/updateQt")
    public String updateQt(Qtgs record) throws Exception {

        commService.updateByPrimaryKeySelective(record);
        return "redirect:/comm/selQt";
    }

    /**
     * 
     * @描述:删除经费预算信息
     * @作者:
     * @时间 2019年2月13日 下午5:39:37
     * @获取一个: 
     * @返回值:
     */
    @RequestMapping(value = "/deleteQt")
    public String deleteQt(Integer id) throws Exception {

        commService.deleteByPrimaryKey(id);

        return "redirect:/comm/selQt";
    }

}
