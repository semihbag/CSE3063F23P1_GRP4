package SystemMessage;

import Page.PageType;

public class SystemMessage {
	private FunctionType functionType;
	private PageType nextPageType;
	private Object input;
	
	
	public SystemMessage(FunctionType ft, PageType pt, Object i) {
		setFunctionType(ft);
		setNextPageType(pt);
		setInput(i);
	}


	public FunctionType getFunctionType() {
		return functionType;
	}
	
	public void setFunctionType(FunctionType functionType) {
		this.functionType = functionType;
	}

	public PageType getNextPageType() {
		return nextPageType;
	}

	public void setNextPageType(PageType nextPageType) {
		this.nextPageType = nextPageType;
	}
	
	public Object getInput() {
		return input;
	}

	public void setInput(Object input) {
		this.input = input;
	}


	
	
}
