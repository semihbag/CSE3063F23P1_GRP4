from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage

class MyRequestPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("MY_REQUESTS_PAGE")
        self.setName("My Request Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage("CHANGE_PAGE", "MAIN_MENU_PAGE", None)
    