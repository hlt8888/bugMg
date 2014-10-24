package cn.moart.bugMg;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/hello.do")
public class HelloWorldController {
	@RequestMapping
	public @ResponseBody Map<String, Object> abcde(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		ModelAndView modelView = new ModelAndView();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        /*if(status) {
            modelMap.put("status", true);
        } else {
            modelMap.put("status", false);
        }*/
                modelMap.put("status","444");//感谢二楼的指正，已修改
                modelMap.put("date", new Date());
        modelView.addAllObjects(modelMap);
        System.out.println("aaaaaa");
        return modelMap;
	}
	
//	@RequestMapping
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		// 1、收集参数、验证参数
		// 2、绑定参数到命令对象
		// 3、将命令对象传入业务对象进行业务处理
		// 4、选择下一个页面
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		Map mp = new HashMap();
		mp.put("name", "Zhangsan");
		mp.put("age", "44");
		mv.addObject("user",mp);
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("hello");
		return mv;
	}
}