package Page;

import java.util.Scanner;
import SystemMessage.*;

public abstract class Page {
	private PageType type;
	private String name;
	private String content; 
	
	
	// constructor
	public Page(String content) {
		setContent(content);
	}
	
	// the abstract method, this will be build later 
	public abstract SystemMessage runPage();
	
	// this function print the content
	public void showContent() {
		System.out.println(getContent());
	}

	public String takeInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}
	
	// GETTER - SETTER METHODS
	public PageType getType() {
		return type;
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
