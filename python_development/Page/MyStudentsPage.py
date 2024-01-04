from python_development.Page.Page import Page
from python_development.Page import PageType
from python_development.System import FunctionType
from python_development.System import SystemMessage


class MyStudentsPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.MY_STUDENTS_PAGE)
        self.setName("All Students Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)
