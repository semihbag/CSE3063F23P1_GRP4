from Page import Page
from Page import PageType
from System import FunctionType
from System import SystemMessage

class SelectableCoursesPage(Page):

    numberOfSelectableCourses = None

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.SelectableCoursesPage)
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
                    return SystemMessage(FunctionType.SELECT_COURSE, PageType.SELECTABLE_COURSES_PAGE, selection)
            except:
                if (inputFromPage.lower() == "q"):
                    return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)
                else:
                    print("Wrong Input!")


    def getNumberOfSelectableCourses(self):
        return self.numberOfSelectableCourses
    
    def setNumberOfSelectableCourses(self, numberOfSelectableCourses):
        self.numberOfSelectableCourses = numberOfSelectableCourses