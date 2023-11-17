package systemMessagePackage;

public enum FunctionType {
	// COMMEN
	NONE,
	LOGIN,
	EXIT,
	CHANGE_PAGE,
	LOGOUT,
	
	// STUDENT
	SELECT_COURSE,
	DROP_COURSE,
	SEND_APPROVE,
	
	// ADVISOR
	SELECET_STUDENT,
	APPROVE_REQUEST,
	DISAPPREOVE_REQUEST;
}
