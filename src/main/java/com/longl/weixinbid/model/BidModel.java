package com.longl.weixinbid.model;

public class BidModel {
	private int id;
	private String title;
	private String dtime;
	private String url;
	private String content;
	private String num;
	private String filenum;
	private String province;
	private String city;
	private String tablename;
	private String createtime;
	private int type;
	private int assortment;
	private int bidtype;
	
	
	public int getBidtype() {
		return bidtype;
	}
	public void setBidtype(int bidtype) {
		this.bidtype = bidtype;
	}
	private String endtime;
	
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getAssortment() {
		return assortment;
	}
	public void setAssortment(int assortment) {
		this.assortment = assortment;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDtime() {
		return dtime;
	}
	public void setDtime(String dtime) {
		this.dtime = dtime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getFilenum() {
		return filenum;
	}
	public void setFilenum(String filenum) {
		this.filenum = filenum;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	

}
