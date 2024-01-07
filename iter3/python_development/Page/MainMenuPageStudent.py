from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.System.FunctionType import FunctionType
from python_development.System.SystemMessage import SystemMessage

class MainMenuPageStudent(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("MAIN_MENU_PAGE")
        self.setName("Main Menu Page Student")


    def runPage(self):
        self.showContent()
        while(True):
            inputFromPage = self.takeInput()        
            
            if (inputFromPage == "1"):
                return SystemMessage("CHANGE_PAGE", "PROFILE_PAGE", None)
            elif (inputFromPage == "2"):
                return SystemMessage("READ_NOTIFICATIONS", "MY_NOTIFICATIONS_PAGE", None)
            elif (inputFromPage == "3"):
                return SystemMessage("CHANGE_PAGE", "TRANSCRIPT_PAGE", None)
            elif (inputFromPage == "4"):
                return SystemMessage("CHANGE_PAGE", "SYLLABUS_PAGE", None)
            elif (inputFromPage == "5"):
                return SystemMessage("CHANGE_PAGE", "ALL_cOURSES_PAGE", None)
            elif (inputFromPage == "6"):
                return SystemMessage("CHANGE_PAGE", "APPROVED_COURSES_PAGE", None)
            elif (inputFromPage == "7"):
                return SystemMessage("CHANGE_PAGE", "SELECTABLE_COURSES_PAGE", None)
            elif (inputFromPage == "8"):
                return SystemMessage("CHANGE_PAGE", "SELECTED_COURSES_PAGE", None)
            elif (inputFromPage == "9"):
                return SystemMessage("LOGOUT", "LOGIN_PAGE", None)
            elif (inputFromPage == "10"):
                return SystemMessage("EXIT", None, None)           
            else:
               print("Wrong Input!")