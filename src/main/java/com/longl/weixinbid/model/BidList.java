package com.longl.weixinbid.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BidList {
	private String token;	
	private List<BidModel> data =new ArrayList<BidModel>();
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<BidModel> getData() {
		return data;
	}
	public void setData(List<BidModel> data) {
		this.data = data;
	}
	
	

  
}
