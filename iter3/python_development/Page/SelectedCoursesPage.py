from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage

class SelectedCoursesPage(Page):

    numberOfDropableCourses = None

    def __init__(self, content):
        super().__init__(content)
        self.setType("SELECTED_COURSES_PAGE")
        self.setName("Selected Courses Page")


    def runPage(self):
        self.showContent()
        inputFromPage = None
        while(True):
            inputFromPage = self.takeInput()
            try:
                selection = int(inputFromPage)
                if (selection > self.numberOfDropableCourses or selection < 0):
                    print("Enter a valid number")
                else:
                    return SystemMessage("DROP_COURSE", "SELECTED_COURSES_PAGE", selection)
            except:
                if(inputFromPage.lower() == "a"):
                    return SystemMessage("SEND_APPROVE", "MAIN_MENU_PAGE", None)
                if(inputFromPage.lower() == "q"):
                    return SystemMessage("CHANGE_PAGE", "MAIN_MENU_PAGE", None)
                else:
                    print("Wrong Input")


    def getNumberOfDropableCourses(self):
        return self.numberOfDropableCourses
    
    def setNumberOfDropableCourses(self, numberOfDropableCourses):
        self.numberOfDropableCourses = numberOfDropableCourses
        