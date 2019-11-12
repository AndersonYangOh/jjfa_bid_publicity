package com.longl.weixinbid.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import com.longl.weixinbid.model.Bid;
import com.longl.weixinbid.model.BidModel;
import com.longl.weixinbid.model.BidNum;
import com.longl.weixinbid.model.MapData;

/**
 * 获取招投标信息
 * 
 * @author longl
 */
@Mapper
public interface IBidsService {
	//添加来源
	@Select("SELECT d.id,d.title,d.url,d.dtime,c.source,d.province FROM\r\n" + 
			"(select id,title,dtime,url,province FROM bid WHERE dtime>=#{starttime}  and dtime<=#{endtime} and `status`=1 AND bidtype=1 AND province=#{province}) d\r\n" + 
			"LEFT JOIN\r\n" + 
			"(SELECT a.id,b.source FROM\r\n" + 
			"(select id,url FROM bid WHERE dtime>=#{starttime}  and dtime<=#{endtime}  and `status`=1 AND bidtype=1 and  province=#{province}) a,url_info b\r\n" + 
			"WHERE LOCATE(b.url,a.url)>0) c\r\n" + 
			"ON d.id=c.id ORDER BY d.id desc")
	public List<Bid> getDetailList(@Param("starttime") String starttime, @Param("endtime") String endtime,
			@Param("province") String province);

	// 获取招投标信息
	@Select("SELECT b.title,b.dtime,b.url,b.province,b.type,b.assortment,y.name,h.name as aname FROM bid b,ywtype y,hytype h \r\n"
			+ "WHERE b.bidtype=1 AND b.`status`=1 AND b.type=y.`code` AND b.assortment=h.`code` and dtime>=#{starttime} and dtime<=#{endtime} ORDER BY province;")
	public List<Bid> getInfoList(@Param("starttime") String starttime, @Param("endtime") String endtime);

	// 获取招投标数量信息
	@Select("SELECT province,count(*) as num FROM bid WHERE bidtype=1 AND `status`=1 and dtime>=#{starttime} and dtime<=#{endtime} GROUP BY province;")
	public List<BidNum> getProvinceList(@Param("starttime") String starttime, @Param("endtime") String endtime);

	// token是否存在
	@Select("SELECT count(*) FROM token WHERE token=#{token};")
	public int getTokenNum(@Param("token") String token);

	@Insert("INSERT INTO  `bid` (`id`, `title`, `dtime`, `url`, `content`, `num`, `filenum`, `province`, `city`, `status`, `type`, `assortment`, `bidtype`) VALUES ( #{id}, #{title}, #{dtime}, #{url}, #{content}, #{num}, #{filenum},  #{province}, #{city}, '1', #{type}, #{assortment}, #{bidtype});")
	int insert(BidModel model);

	// 获取地图招投标数量信息
	@Select("SELECT province.province as name,if(isnull(a.v),0,a.v) as value FROM province \r\n"
			+ "LEFT JOIN (SELECT province,count(*) as v FROM bid WHERE bidtype=1 and province<>'' and province<>'中央' AND `status`=1 and dtime>=#{starttime} and dtime<=#{endtime} GROUP BY province) a on province.province=a.province;")
	public List<MapData> getMapList(@Param("starttime") String starttime, @Param("endtime") String endtime);
	
	@Insert("INSERT INTO  `devicetoken` (`devicetype`, `deviceid`, `devicetoken`, `creattime`) VALUES ( #{devicetype}, #{deviceid}, #{devicetoken}, now());")
	int insertdevicetoken(@Param("devicetype")  String devicetype, @Param("deviceid")  String deviceid, @Param("devicetoken")  String devicetoken);
	
	// devicetoken是否存在
		@Select("SELECT count(*) FROM devicetoken WHERE devicetoken=#{devicetoken};")
		public int getDeviceToken(@Param("devicetoken") String devicetoken);

}
