package UserInterface;

import Page.*;
import SystemMessage.*;
import java.util.ArrayList;


public class UserInterface {
	
	private ArrayList<Page> pages;
	private Page currentPage;
	private SystemMessage systemMessage;


	// constructor
	public UserInterface() {
		pages = new ArrayList<>();
		currentPage = null;
	}
	
	// this function calls runPage func of the current page
	public void display() {
		setSystemMessage(currentPage.runPage());
	}

	
	// this function add page into 
	// if page already in list return false
	// if not add and return true
	public boolean addPage(Page page) {
		int len = pages.size();
		
		if (len == 0) {
			pages.add(page);
			return true;
		}
		else {
			Page tempPage;
			for(int i = 0; i < len; i++) {
				tempPage = pages.get(i);
				
				if (tempPage.getType() == page.getType()) {
					// this page already added to list
					return false;
				}
			}
			pages.add(page);
			return true;	
		}
	}
	
		
	// to change displayed page
	// if the pages list contains the selected page, replace the page and return true
	// if not return false
	public boolean setCurrentPage(PageType pageType) {
		int len = pages.size();

		Page tempPage;
		for(int i = 0; i < len; i++) {
			tempPage = pages.get(i);
			
			if (tempPage.getType() == pageType) {
				currentPage = tempPage;
				return true;
			}
		}
		return false;
	}
	
	
	// this function return the selected page from page list if it in list
	// if not return null	
	public Page selectPage(PageType pageType) {
		int len = pages.size();

		Page tempPage;
		for(int i = 0; i < len; i++) {
			tempPage = pages.get(i);
			
			if (tempPage.getType() == pageType) {
				return tempPage;
			}
		}
		return null;
	}

	public void resetPages() {
		pages.clear();
	}

	
	
	
	
	/////////////////
	
	public ArrayList<Page> getPages() {
		return pages;
	}

	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}
	
	public Page getCurrentPage() {
		return currentPage;
	}

	public SystemMessage getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(SystemMessage systemMessage) {
		this.systemMessage = systemMessage;
	}

	
	
}
