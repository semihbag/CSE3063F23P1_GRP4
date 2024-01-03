from Page import Page
from Page import PageType
from System import FunctionType
from System import SystemMessage

class ApprovedCoursesPage(Page):
    pass

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.ApprovedCoursesPage)
        self.setName("Approved Courses Page")

    
    def runPage(self):
        self.showContent()
        self.takeInput()
        FunctionType.c
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)