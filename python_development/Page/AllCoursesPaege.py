from python_development.Page import Page
from python_development.Page import PageType
from python_development.System import FunctionType
from python_development.System import SystemMessage

class AllCoursesPAge(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.ALL_cOURSES_PAGE)
        self.setName("All Courses Page")

    
    def runPage(self):
        self.showContent()
        self.takeInput()
        FunctionType.c
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)