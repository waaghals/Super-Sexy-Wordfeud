package nl.avans.min04sob.scrabble.core;

public class Column {

	private String name;
	private Class<?> classType;
	
	public Column(String name, Class<?> classType){
		this.classType = classType;
		this.name = name;
	}
	
	public Class<?> getClassType(){
		return classType;
	}
	
	public String getName(){
		return name;
	}
}
