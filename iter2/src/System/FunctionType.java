package System;

public enum FunctionType {
	//COMMON
	LOGIN,
	EXIT,
	CHANGE_PAGE,
	LOGOUT,
	CHANGE_PASSWORD,
	
	// STUDENT
	READ_NOTIFICATIONS,
	SELECT_COURSE,
	DROP_COURSE,
	SEND_APPROVE,
	
	// ADVISOR
	SELECET_STUDENT,
	APPROVE_REQUEST,
	DISAPPREOVE_REQUEST,
	
	// LECTURER
	SELECT_MY_COURSE;
}
