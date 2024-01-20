from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage


class SelectableCoursesPage(Page):

    numberOfSelectableCourses = None

    def __init__(self, content):
        super().__init__(content)
        self.setType("SELECTABLE_COURSES_PAGE")
        self.setName("Selectable Courses Page")


    def runPage(self):
        self.showContent()
        inputFromPage = None
        while(True):
            inputFromPage = self.takeInput()
            try:
                selection = int(inputFromPage)
                if (selection > self.numberOfSelectableCourses or selection < 0):
                    print("Enter a valid number")
                else: 
                    return SystemMessage("SELECT_COURSE", "SELECTABLE_COURSES_PAGE", selection)
            except:
                if (inputFromPage.lower() == "q"):
                    return SystemMessage("CHANGE_PAGE", "MAIN_MENU_PAGE", None)
                else:
                    print("Wrong Input!")


    def getNumberOfSelectableCourses(self):
        return self.numberOfSelectableCourses
    
    def setNumberOfSelectableCourses(self, numberOfSelectableCourses):
        self.numberOfSelectableCourses = numberOfSelectableCourses