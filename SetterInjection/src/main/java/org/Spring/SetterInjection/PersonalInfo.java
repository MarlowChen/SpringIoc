package org.Spring.SetterInjection;
public class PersonalInfo {

	private String name;
	private String gender;
	private String birth;
	private int height;
	private int weight;
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}	

	public void setGender(String gender){
		this.gender = gender;
	}
	public String getGender(){
		return gender;
	}
	
	public void setBirth(String birth){
		this.birth = birth;
	}
	public String getBirth(){
		return birth;
	}	
	
	public void setHeight(int height){
		this.height = height;
	}
	public int getHeight(){
		return height;
	}	
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	public int getWeight(){
		return weight;
	}
	
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\" , "  +
				"\"gender\":\"" + gender + "\" , "+
				"\"birth\":\"" + birth +"\" , " +
				"\"height\":\"" + height + "cm\" , " +
				"\"weight\":\"" + weight + "kg\" }";
	}
}
