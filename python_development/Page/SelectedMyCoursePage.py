from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.SystemPackage.FunctionType import FunctionType
from python_development.SystemPackage.SystemMessage import SystemMessage



class SelectedMyCoursePage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.SELECTED_MY_COURSE_PAGE)
        self.setName("Selected My Course Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MY_COURSES_PAGE, None)
