package domain;

public class Subject extends DomainObject {
	private int code;
	private String name;
	private int program;
	private int semester;
	
	public Subject(){
		
	}
	public int getNumericId(){
		return 0;
	}
}