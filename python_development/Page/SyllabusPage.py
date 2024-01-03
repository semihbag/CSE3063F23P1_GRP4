from python_development.Page import Page
from python_development.Page import PageType
from python_development.System import FunctionType
from python_development.System import SystemMessage


class SyllabusPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.SYLLABUS_PAGE)
        self.setName("Syllabus Page Student")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)
