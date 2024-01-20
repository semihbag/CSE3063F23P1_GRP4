from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage


class SelectedMyCoursePage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("SELECTED_MY_COURSE_PAGE")
        self.setName("Selected My Course Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage("CHANGE_PAGE", "MY_COURSES_PAGE", None)
