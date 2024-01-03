from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.SystemPackage.FunctionType import FunctionType
from python_development.SystemPackage.SystemMessage import SystemMessage



class ProfilePage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.PROFILE_PAGE)
        self.setName("Profile Page")


    def runPage(self):
        self.showContent()
        inputFromPage = None
        while(True):
            inputFromPage = self.takeInput()
            if (inputFromPage.lower() == "c"):
                return SystemMessage(FunctionType.CHANGE_PAGE, PageType.CHANGE_PASSWORD_PAGE, None)
            elif (inputFromPage.lower() == "q"):
                return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)
            else:
                print("Wrong Input!")


