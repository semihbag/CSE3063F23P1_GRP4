from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage

class MyStudentsPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("MY_STUDENTS_PAGE")
        self.setName("All Students Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage("CHANGE_PAGE", "MAIN_MENU_PAGE", None)
