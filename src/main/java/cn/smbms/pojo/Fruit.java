package cn.smbms.pojo;

public class Fruit {
	private String fid;
	private String name;    //果树品种
	private String price;     //单额价格
	private String time;      //截止时间
	
	public Fruit() {
	}
	
	public String getFid() {
		return fid;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public String getTime() {
		return time;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Fruit [fid=" + fid + ", name=" + name + ", price=" + price + ", time=" + time + "]";
	}
}
