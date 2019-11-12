package com.longl.weixinbid.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.longl.weixinbid.model.Bid;
import com.longl.weixinbid.model.BidList;
import com.longl.weixinbid.model.BidModel;
import com.longl.weixinbid.model.BidNum;
import com.longl.weixinbid.model.MapData;
import com.longl.weixinbid.service.IBidsService;
import com.longl.weixinbid.util.Util;

@Controller
public class TestController {
	@Autowired
	private IBidsService bidsService;

	/*
	 * map
	 */
	@GetMapping("map")
	public String map(@RequestParam String starttime, @RequestParam String endtime, Model model) {
		try {
			if (Util.checkTime(starttime) && Util.checkTime(endtime)) {
				model.addAttribute("starttime", starttime);
				model.addAttribute("endtime", endtime);
				List<MapData> list = bidsService.getMapList(starttime, endtime);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(list);
				model.addAttribute("mapdata", json);
				return "map";
			} else {
				return "error";
			}
		} catch (Exception e) {
			return "400";
		}
	}

	@GetMapping("mapdata")
	@ResponseBody
	public String mapdata(@RequestParam String starttime, @RequestParam String endtime) {
		try {
			List<Bid> list = bidsService.getInfoList(starttime, endtime);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list);
			return json;
		} catch (Exception e) {
			return "json异常";
		}

	}

	/*
	 * GET请求
	 */
	@GetMapping("biddetail")
	public String biddetail(@RequestParam String starttime, @RequestParam String endtime, @RequestParam String province,
			Model model) {
		try {
			if (province == null || (province.length() < 2 && province.length() > 3)) {
				return "404";
			}
			if (Util.checkTime(starttime) && Util.checkTime(endtime)) {
				model.addAttribute("starttime", starttime);
				model.addAttribute("endtime", endtime);
				int day = Util.CompareDay(starttime, endtime);
				if (day < 0 || day > 10) {
					return "404";
				}
				if (!Util.checkProvince(province)) {
					return "404";
				}
				model.addAttribute("province", province);
				return "biddetail";
			} else {
				return "404";
			}
		} catch (Exception e) {
			return "404";
		}
	}

	/*
	 * GET请求
	 */
	@GetMapping("info")
	public String zhanshi(@RequestParam String starttime, @RequestParam String endtime, Model model) {
		try {
			if (Util.checkTime(starttime) && Util.checkTime(endtime)) {
				model.addAttribute("starttime", starttime);
				model.addAttribute("endtime", endtime);
				return "bidlist";
			} else {
				return "error";
			}
		} catch (Exception e) {
			return "404";
		}
	}

	@GetMapping("xinxi")
	@ResponseBody
	public String xinxi(@RequestParam String starttime, @RequestParam String endtime) {
		try {
			List<Bid> list = bidsService.getInfoList(starttime, endtime);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list);
			return json;
		} catch (Exception e) {
			return "404";
		}
	}

	@GetMapping("detail")
	@ResponseBody
	public String detail(@RequestParam String starttime, @RequestParam String endtime, @RequestParam String province) {
		try {
			if (!Util.checkTime(starttime) || !Util.checkTime(endtime)) {
				return "404";
			}
			if (!Util.checkProvince(province)) {
				return "404";
			}
			int day = Util.CompareDay(starttime, endtime);
			if (day < 0 || day > 10) {
				return "404";
			}
			List<Bid> list = bidsService.getDetailList(starttime, endtime, province);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list);
			return json;
		} catch (Exception e) {
			return "404";
		}
	}

	/*
	 * GET请求
	 */
	@GetMapping("jietu")
	public String jietu(@RequestParam String starttime, @RequestParam String endtime, Model model) {
		try {
			if (!Util.checkTime(starttime) || !Util.checkTime(endtime)) {
				return "404";
			}
			int day = Util.CompareDay(starttime, endtime);
			if (day < 0 || day > 10) {
				return "404";
			}
			List<BidNum> list = bidsService.getProvinceList(starttime, endtime);
			model.addAttribute("starttime", starttime);
			model.addAttribute("endtime", endtime);
			for (BidNum bn : list) {
				if (bn.getProvince().equalsIgnoreCase("黑龙江")) {
					model.addAttribute("hlj", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("辽宁")) {
					model.addAttribute("ln", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("吉林")) {
					model.addAttribute("jl", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("上海")) {
					model.addAttribute("sh", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("江苏")) {
					model.addAttribute("js", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("浙江")) {
					model.addAttribute("zj", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("安徽")) {
					model.addAttribute("ah", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("福建")) {
					model.addAttribute("fj", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("江西")) {
					model.addAttribute("jx", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("山东")) {
					model.addAttribute("sd", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("北京")) {
					model.addAttribute("bj", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("天津")) {
					model.addAttribute("tj", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("河北")) {
					model.addAttribute("heb", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("山西")) {
					model.addAttribute("sx", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("内蒙古")) {
					model.addAttribute("nmg", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("河南")) {
					model.addAttribute("hen", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("湖北")) {
					model.addAttribute("hb", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("湖南")) {
					model.addAttribute("hun", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("广东")) {
					model.addAttribute("gd", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("广西")) {
					model.addAttribute("gx", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("海南")) {
					model.addAttribute("hain", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("重庆")) {
					model.addAttribute("cq", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("四川")) {
					model.addAttribute("sc", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("贵州")) {
					model.addAttribute("gz", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("云南")) {
					model.addAttribute("yn", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("西藏")) {
					model.addAttribute("xz", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("陕西")) {
					model.addAttribute("shanx", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("甘肃")) {
					model.addAttribute("gs", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("青海")) {
					model.addAttribute("qh", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("宁夏")) {
					model.addAttribute("nx", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("新疆")) {
					model.addAttribute("xj", "" + bn.getNum());
				} else if (bn.getProvince().equalsIgnoreCase("中央")) {
					model.addAttribute("zy", "" + bn.getNum());
				}
			}

			return "zhanshi";
		} catch (Exception e) {
			return "404";
		}
	}

	@PostMapping("charu")
	@ResponseBody
	public String adddata(@RequestBody JSONObject jsonParam) {
		try {
			String result = "{\"result\":\"success\"}";
			String requestinfo = jsonParam.toJSONString();
			System.out.println(requestinfo);
			BidList blist = JSON.parseObject(requestinfo, new TypeReference<BidList>() {
			});
			if (blist.getToken() == null || blist.getToken().length() != 32) {
				result = "{\"result\":\"error\"}";
			} else {
				if (bidsService.getTokenNum(blist.getToken()) > 0) {
					for (BidModel b : blist.getData()) {
						bidsService.insert(b);
					}

				} else {
					result = "{\"result\":\"error\"}";
				}
			}
			return result;
		} catch (Exception e) {
			return "捕获异常";
		}

	}
	
	/*
	 * GET请求
	 */
	@GetMapping("getdevicetoken")
	@ResponseBody
	public String devicetoken(@RequestParam String token, @RequestParam String devicetype, @RequestParam String deviceid, @RequestParam String devicetoken, Model model) {
		try {
			if (bidsService.getTokenNum(token)>0&&bidsService.getDeviceToken(devicetoken)<=0) {

				bidsService.insertdevicetoken(devicetype,deviceid,devicetoken);
				return "ok";
			} else {
				return "error";
			}
		} catch (Exception e) {
			return "error";
		}
	}

}
