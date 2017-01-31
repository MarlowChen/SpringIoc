package org.Spring.ConstructorInject;
public class PersonalInfo {

	private String name;
	private String gender;
	private String birth;
	private int height;
	private int weight;
	
	public PersonalInfo(String name,String gender,
			String birth,int height,int weight){
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.height = height;
		this.weight = weight;
	}

	public String getName(){
		return name;
	}	

	public String getGender(){
		return gender;
	}
	
	public String getBirth(){
		return birth;
	}	
	
	public int getHeight(){
		return height;
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
