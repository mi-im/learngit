package cn.smbms.pojo;

import java.util.List;

public class ShareFruit {
	private String fid;
	private List<Fruit> fruitList;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public List<Fruit> getFruitList() {
		return fruitList;
	}
	public void setFruitList(List<Fruit> fruitList) {
		this.fruitList = fruitList;
	}
	
	
}
