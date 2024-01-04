from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.System.FunctionType import FunctionType
from python_development.System.SystemMessage import SystemMessage

class ApprovedCoursesPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("APPROVED_COURSES_PAGE")
        self.setName("Approved Courses Page")

    
    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)