package pagePackage;

import java.util.Scanner;

import helper.ReGSystem;

public abstract class Page {
	private ReGSystem system;
	private PageType type;
	private String name;
	private String content; 
	
	
	// constructor
	public Page(ReGSystem system, String content) {
		setContent(content);
		setSystem(system);
	}
	
	// the abstract method, this will be build later 
	public abstract void runPage();
	
	// this function print the content
	public void showContent() {
		System.out.println(getContent());
	}
	
	// 
	public String takeInput() {
		String inp = system.getScanner().nextLine();
		return inp;
	}

	

	
	
	// GETTER - SETTER METHODS

	public PageType getType() {
		return type;
	}

	public ReGSystem getSystem() {
		return system;
	}

	public void setSystem(ReGSystem system) {
		this.system = system;
	}

	public void setType(PageType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
	
	
	
}
