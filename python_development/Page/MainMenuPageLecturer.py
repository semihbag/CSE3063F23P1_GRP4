from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.System.FunctionType import FunctionType
from python_development.System.SystemMessage import SystemMessage


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
                return SystemMessage(FunctionType.CHANGE_PAGE, PageType.PROFILE_PAGE, None)
            elif (inputFromPage == "2"):
                return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MY_COURSES_PAGE, None)
            elif (inputFromPage == "3"):
                return SystemMessage(FunctionType.CHANGE_PAGE, PageType.SYLLABUS_PAGE, None)
            elif (inputFromPage == "4"):
                return SystemMessage(FunctionType.LOGOUT, PageType.LOGIN_PAGE, None)
            elif (inputFromPage == "5"):
                return SystemMessage(FunctionType.EXIT, None, None)
            else:
               print("Wrong Input!")