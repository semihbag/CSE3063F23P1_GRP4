from Page import Page
from Page import PageType
from System import FunctionType
from System import SystemMessage

class SelectedMyCoursePage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.SELECTED_MY_COURSE_PAGE)
        self.setName("Selected My Course Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MY_COURSES_PAGE, None)
