from python_development.Page.Page import Page
from python_development.Page import PageType
from python_development.System import FunctionType
from python_development.System import SystemMessage

class MyRequestPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.MY_REQUESTS_PAGE)
        self.setName("My Request Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)
    