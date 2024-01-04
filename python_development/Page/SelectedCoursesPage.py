from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.System.FunctionType import FunctionType
from python_development.System.SystemMessage import SystemMessage

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
                    return SystemMessage(FunctionType.DROP_COURSE, PageType.SELECTED_COURSES_PAGE, selection)
            except:
                if(inputFromPage.lower() == "a"):
                    return SystemMessage(FunctionType.SEND_APPROVE, PageType.MAIN_MENU_PAGE, None)
                if(inputFromPage.lower() == "q"):
                    return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)
                else:
                    print("Wrong Input")


    def getNumberOfDropableCourses(self):
        return self.numberOfDropableCourses
    
    def setNumberOfDropableCourses(self, numberOfDropableCourses):
        self.numberOfDropableCourses = numberOfDropableCourses
        