from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.System.FunctionType import FunctionType
from python_development.System.SystemMessage import SystemMessage


class ProfilePage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("MY_STUDENTS_PAGE")
        self.setName("Profile Page")


    def runPage(self):
        self.showContent()
        inputFromPage = None
        while(True):
            inputFromPage = self.takeInput()
            if (inputFromPage.lower() == "c"):
                return SystemMessage("CHANGE_PAGE", "CHANGE_PASSWORD_PAGE", None)
            elif (inputFromPage.lower() == "q"):
                return SystemMessage("CHANGE_PAGE", "MAIN_MENU_PAGE", None)
            else:
                print("Wrong Input!")


