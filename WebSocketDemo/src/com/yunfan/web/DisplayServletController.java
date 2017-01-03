package com.yunfan.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/display")
public class DisplayServletController {

	/**
	 * 初始化数据地图
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="/datamap")
	public ModelAndView initDataMap(HttpServletRequest req, HttpServletResponse resp){
		ModelAndView view = new ModelAndView();
		view.setViewName("default.list");
		return view;
	}
	
	/**
	 * 进入地图数据页面
	 * @return
	 */
	@RequestMapping(value="/entryMap")
	public String entryDataMap(){
		return ".datamap";
	}
	
	/**
	 * 进入详情信息页面
	 * @param machineid
	 * @return
	 */
	@RequestMapping(value="/entryDetailInfo")
	public String entryDetailInfo(@RequestParam("machineId") String machineid){
		return ".devicedetail";
	}
	
	/**
	 * 进入故障统计页面
	 * @return
	 */
	@RequestMapping(value="/entryFatalStatics")
	public String entryFatalStatics(){
		return ".fatalstatics";
	}
	/**
	 * 进入维修信息
	 * @return
	 */
	@RequestMapping(value="/entryRepairInfo")
	public String entryRepairInfo(){
		return ".repairinfo";
	}
	/**
	 * 获取维修信息
	 * @param inputstr
	 * @return
	 */
	@RequestMapping(value="/searchRepairInfo",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String searchRepairInfo(HttpServletRequest req, HttpServletResponse response){
		String searchcontent = "";
		if(StringUtils.isNotEmpty(req.getParameter("searchcontent"))){
			searchcontent = req.getParameter("searchcontent");
		}
		JSONObject result = new JSONObject();
		JSONArray contents = new JSONArray();
		JSONObject item = new JSONObject();
		item.accumulate("num","1");
		item.accumulate("description", searchcontent);
		item.accumulate("guildline", "滚动轴承的振动诊断方法包括有效值和峰值判别法、峰值因数法、概率密度分析法（用峭度衡量）、低频信号接收法、中频带通滤波法、谐振动信号接收法、包络法、高通绝对值频率分析法，目前国际流行的滚动轴承故障诊断技术是美国ENTEK公司的g/SE技术和瑞典SPM公司的冲击脉冲法。");
		item.accumulate("match", "90%");
		contents.add(item);
		item = new JSONObject();
		item.accumulate("num","2");
		item.accumulate("description", searchcontent);
		item.accumulate("guildline", "齿轮故障诊断技术主要是啮合频率谐频分析和边频带分析。齿轮正常，齿 磨损，齿面点蚀，齿面剥落，齿轮偏心和齿隙游移，转轴上联轴节不平衡或不对中，齿轮不对中，齿断或齿裂，齿轮组合状态问题，齿摆动故障，轴承配合松动对齿轮影响等情况的特征.");
		item.accumulate("match", "80%");
		contents.add(item);
		item = new JSONObject();
		item.accumulate("num","3");
		item.accumulate("description", searchcontent);
		item.accumulate("guildline", "声音主要发生在传动部分，主轴箱、变速箱、进给箱等机构中的轴与轴承、互相啮合的齿轮、蜗轮与蜗杆、丝杠与螺母等都是噪音产生的主要部位。在一般情况下，噪音随着温度的升高、负荷和磨损的增大、润滑不良等而增大。");
		item.accumulate("match", "40%");
		contents.add(item);
		item = new JSONObject();
		item.accumulate("num","4");
		item.accumulate("description", searchcontent);
		item.accumulate("guildline", "声音主要发生在传动部分，主轴箱、变速箱、进给箱等机构中的轴与轴承、互相啮合的齿轮、蜗轮与蜗杆、丝杠与螺母等都是噪音产生的主要部位。在一般情况下，噪音随着温度的升高、负荷和磨损的增大、润滑不良等而增大。");
		item.accumulate("match", "40%");
		contents.add(item);
		item = new JSONObject();
		item.accumulate("num","5");
		item.accumulate("description", searchcontent);
		item.accumulate("guildline", "声音主要发生在传动部分，主轴箱、变速箱、进给箱等机构中的轴与轴承、互相啮合的齿轮、蜗轮与蜗杆、丝杠与螺母等都是噪音产生的主要部位。在一般情况下，噪音随着温度的升高、负荷和磨损的增大、润滑不良等而增大。");
		item.accumulate("match", "40%");
		contents.add(item);
		item = new JSONObject();
		item.accumulate("num","6");
		item.accumulate("description", searchcontent);
		item.accumulate("guildline", "声音主要发生在传动部分，主轴箱、变速箱、进给箱等机构中的轴与轴承、互相啮合的齿轮、蜗轮与蜗杆、丝杠与螺母等都是噪音产生的主要部位。在一般情况下，噪音随着温度的升高、负荷和磨损的增大、润滑不良等而增大。");
		item.accumulate("match", "40%");
		contents.add(item);
		item = new JSONObject();
		item.accumulate("num","7");
		item.accumulate("description", searchcontent);
		item.accumulate("guildline", "声音主要发生在传动部分，主轴箱、变速箱、进给箱等机构中的轴与轴承、互相啮合的齿轮、蜗轮与蜗杆、丝杠与螺母等都是噪音产生的主要部位。在一般情况下，噪音随着温度的升高、负荷和磨损的增大、润滑不良等而增大。");
		item.accumulate("match", "40%");
		contents.add(item);
		result.accumulate("contents", contents);
		response.setContentType("application/json;charset=UTF-8");
		return result.toString();
	}
}
