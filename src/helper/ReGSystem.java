package helper;

import java.util.Scanner;

import pagePackage.PageType;
import systemMessagePackage.FunctionType;
import systemMessagePackage.SystemMessage;
import userInfoPackage.UserInfo;
import userInterfacePackage.UserInterface;

public class ReGSystem {
	private UserInterface userInterface;
	
	public ReGSystem(UserInterface u) {
		this.userInterface = u;
		
	}
	
	public void run() {
		while (true) {
			userInterface.display();
			listenUserInterface(userInterface.getSystemMessage());
		}
		
	}
	
	public boolean login(UserInfo userInfo) {
		
		// true case
		//this.getUserInterface().setCurrentPage(PageType.MAIN_MENU_PAGE_STUDENT);
		this.getUserInterface().setCurrentPage(PageType.MAIN_MENU_PAGE_ADVISOR);
		return true;
		
		
		// false case
		//this.getUserInterface().setCurrentPage(PageType.LOGIN_PAGE);
		//return false;
	}
	
		
	
	public void listenUserInterface(SystemMessage sm) {
		FunctionType functionType = sm.getFunctionType();
		
		if (functionType == FunctionType.NONE) {
			return;
		}
		
		if (functionType == FunctionType.LOGIN) {
			UserInfo userInfo = (UserInfo)sm.getInput();
			this.login(userInfo);
			return;
		}
		
		if (functionType == FunctionType.LOGOUT) {
			// this.logout();
			this.userInterface.setCurrentPage(PageType.LOGIN_PAGE);
		}

		
		if (functionType == FunctionType.EXIT) {
			System.exit(0);
			return;
		}
		
		if (functionType == FunctionType.CHANGE_PAGE) {
			this.userInterface.setCurrentPage(sm.getNextPageType());
			return;

		}
		
		if (functionType == FunctionType.SELECT_COURSE ) {
			//Student student = (Student)this.getCurrentUser();
			
			// burada studentin içinden secilebilen derslere gidip gelen
			// input (index oluyor artık o) degerindeki course selected
			// listesine atacagız
			
			// ayrıca bu saatten sonra data değiştigi için sayfa contentini
			// tekrar ayarlamak lazım.
			// conten üreten fonksiyon (zenep yazıyor) tekrar cagırılıp üretildiği
			// string selecable page contentinin yeni değeri olacak
			// bu işlemi yapmazsak sürekli aynı görüntü print edilir
			// ayrıca sayfanın numberOdSelectableCourse değeri de güncellenmeli
			return;

		}
		
		if (functionType == FunctionType.DROP_COURSE ) {
			//Student student = (Student)this.getCurrentUser();
			
			// burada studentin içinden secilen derslere gidip gelen
			// input (index oluyor artık o) degerindeki course drop işlemi yapıcaz
			
			// ayrıca bu saatten sonra data değiştigi için sayfa contentini
			// tekrar ayarlamak lazım.
			// conten üreten fonksiyon (zenep yazıyor) tekrar cagırılıp üretildiği
			// string selected page contentinin yeni değeri olacak
			// bu işlemi yapmazsak sürekli aynı görüntü print edilir.
			// ayrıca sayfanın numberOdDropableCourse değeri de güncellenmeli
			return;
			
		}

		if (functionType == FunctionType.SELECET_STUDENT ) {
			// burada advisorun select fonksiyonu çağırılacak parametre olarak sm.getInput() verilecek
			
			//Advisor advisor = (Adivor)this.getCurrentUser();
			//advisor.selectStudent(sm.getInput);
			
			this.userInterface.setCurrentPage(sm.getNextPageType());
			return;

		}

		if (functionType == FunctionType.APPROVE_REQUEST ) {
			// burada seçilen req için onaylama istegi geliyor burada onaylamamız lazım.
			// ayrıca artık req sayısı değiştiği için EVALUATE_REQUESTS_PAGE sayfası contenti güncellenmeli
			// ayrıca SELECTED_STUDENT_REQUEST_PAGE contenti de değişecek
			// zenepin yazdıgı fonk tekrar cağırılacak ve settet ile content degiştirilecek
			
			//Advisor advisor = (Adivor)this.getCurrentUser();
			//advisor.approve();
			System.out.println("---req approved");
			this.userInterface.setCurrentPage(sm.getNextPageType());
			return;

		}

		if (functionType == FunctionType.DISAPPREOVE_REQUEST ) {
			// burada secilen req istegi reddedildi
			// yani listeden kaldırldı
			// yine yıkarıda oldugu gibi EVALUATE_REQUESTS_PAGE contenti değişecek
			// ayrıca SELECTED_STUDENT_REQUEST_PAGE contenti de değişecek
			// zenepin yazdıgı fonk tekrar cağırılacak ve settet ile content degiştirilecek

			//Advisor advisor = (Advisor)this.getCurrentUser();
			//advisor.disapprove();
			System.out.println("---req disapproved");
			this.userInterface.setCurrentPage(sm.getNextPageType());
			return;

		}

	}
	
	
	public UserInterface getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	
	
	
	
}
