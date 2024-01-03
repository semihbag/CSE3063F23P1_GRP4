from Page import Page
from Page import PageType
from System import FunctionType
from System import SystemMessage

class AllCoursesPAge(Page):
    pass

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.AllCoursesPAge)
        self.setName("All Courses Page")

    
    def runPage(self):
        self.showContent()
        self.takeInput()
        FunctionType.c
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)