from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage


class MainMenuPageLecturer(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("MAIN_MENU_PAGE")
        self.setName("Main Menu Page Lecturer")


    def runPage(self):
        self.showContent()
        while(True):
            inputFromPage = self.takeInput()

            if (inputFromPage == "1"):
                return SystemMessage("CHANGE_PAGE", "PROFILE_PAGE", None)
            elif (inputFromPage == "2"):
                return SystemMessage("CHANGE_PAGE", "MY_COURSES_PAGE", None)
            elif (inputFromPage == "3"):
                return SystemMessage("CHANGE_PAGE", "SYLLABUS_PAGE", None)
            elif (inputFromPage == "4"):
                return SystemMessage("LOGOUT", "LOGIN_PAGE", None)
            elif (inputFromPage == "5"):
                return SystemMessage("EXIT", None, None)
            else:
               print("Wrong Input!")