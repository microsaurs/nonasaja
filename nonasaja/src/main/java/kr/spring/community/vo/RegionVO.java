package kr.spring.community.vo;

public class RegionVO {
	private int region_num; //지역 식별번호
	private int si_num; //시,도 번호
	private String si_name; //시,도 이름
	private int gu_num; //시,군,구 번호
	private String gu_name; //시,군,구 이름
	
	public int getRegion_num() {
		return region_num;
	}
	public void setRegion_num(int region_num) {
		this.region_num = region_num;
	}
	public int getSi_num() {
		return si_num;
	}
	public void setSi_num(int si_num) {
		this.si_num = si_num;
	}
	public String getSi_name() {
		return si_name;
	}
	public void setSi_name(String si_name) {
		this.si_name = si_name;
	}
	public int getGu_num() {
		return gu_num;
	}
	public void setGu_num(int gu_num) {
		this.gu_num = gu_num;
	}
	public String getGu_name() {
		return gu_name;
	}
	public void setGu_name(String gu_name) {
		this.gu_name = gu_name;
	}
	
	@Override
	public String toString() {
		return "RegionVO [region_num=" + region_num + ", si_num=" + si_num + ", si_name=" + si_name + ", gu_num="
				+ gu_num + ", gu_name=" + gu_name + "]";
	}
	
	
}
