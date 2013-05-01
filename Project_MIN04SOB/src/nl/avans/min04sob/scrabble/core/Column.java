package nl.avans.min04sob.scrabble.core;

public class Column {

	private String name;
	private Class<?> classType;
	private int index;
	
	public Column(String name, Class<?> classType, int index){
		this.classType = classType;
		this.name = name;
		this.index = index;
	}
	
	public Class<?> getClassType(){
		return classType;
	}
	
	public String getName(){
		return name;
	}
	
	public int getColumnIndex(){
		return index;
	}
}
