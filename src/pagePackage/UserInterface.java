package pagePackage;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
	
	private Scanner consoleScanner;
	private ArrayList<Page> pages;
	private Page currentPage;

	
	// constructor
	public UserInterface() {
		this.consoleScanner = new Scanner(System.in);
		this.pages = new ArrayList<>();
	}
	
	// this function calls runPage func of the current page
	public void display() {
		currentPage.runPage();
	}

	// to change displayed page
	// if the pages list contains the selected page, replace the page and return true
	// if not return false
	public boolean setCurrentPage(PageType pageType) {
		
		
		
		return true;
	}
	
	//////
	//////
	//////
	public boolean addPage(Page page) {
		
		return true;
	}
}
