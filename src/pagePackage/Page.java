package pagePackage;

import java.util.Scanner;

public abstract class Page {
 
	private Scanner consoleScanner;
	private PageType type;
	private String name;
	private String content; 
	
	
	// methodsaaa
	// constructor
	public Page(Scanner consoleScanner, String content) {
		setConsoleScanner(consoleScanner);
		setContent(content);
	}
	
	// the abstract method, this will be build later 
	public abstract void runPage();
	
	// this function print the content
	private void showContent() {
		System.out.println(getContent());
	}
	
	// 
	private String takeInput() {
		String inp = consoleScanner.nextLine();
		return inp;
	}

	public Scanner getConsoleScanner() {
		return consoleScanner;
	}

	public void setConsoleScanner(Scanner consoleScanner) {
		this.consoleScanner = consoleScanner;
	}

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
	
	
	
	
	
	// GETTER - SETTER METHODS
	
	
	
}
