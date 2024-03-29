from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage

class MyCoursesPage(Page):

    numberOfCourses = None

    def __init__(self, content):
        super().__init__(content)
        self.setType("MY_COURSES_PAGE")
        self.setName("My Courses Page")


    def runPage(self):
        self.showContent()
        inputFromPage = None
        while(True):
            inputFromPage = self.takeInput()
            try:
                selection = int(inputFromPage)
                if (selection > self.numberOfCourses or selection < 0):
                    print("Enter a valid number")
                else:
                    return SystemMessage("SELECT_MY_COURSE", "SELECTED_MY_COURSE_PAGE", selection)
            except:
                if(inputFromPage.lower() == "q"):
                    return SystemMessage("CHANGE_PAGE", "MAIN_MENU_PAGE", None)
                else:
                    print("Wrong Input")


    def getNumberOfCourses(self):
        return self.numberOfCourses
    
    def setNumberOfCourses(self, numberOfCourses):
        self.numberOfCourses = numberOfCourses