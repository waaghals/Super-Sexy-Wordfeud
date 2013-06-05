package nl.avans.min04sob.scrabble.misc;

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
	
	public int getColumnIndex(){
		return index;
	}
	
	public String getName(){
		return name;
	}
}
