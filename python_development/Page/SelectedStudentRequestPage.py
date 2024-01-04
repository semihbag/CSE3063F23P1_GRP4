from python_development.Page.Page import Page
from python_development.Page import PageType
from python_development.System import FunctionType
from python_development.System import SystemMessage


class SelectedStudentRequestPage(Page):
     
    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.SELECTED_STUDENT_REQUEST_PAGE)
        self.setName("Selected Student Request Page")


    def runPage(self):
        self.showContent()
        inputFromPage = None
        while(True):
            inputFromPage = self.takeInput()
            if(inputFromPage.lower() == "a"):
                print("Type your message or press enter directly:")
                message = self.takeInput()
                return SystemMessage(FunctionType.APPROVE_REQUEST, PageType.EVALUATE_REQUESTS_PAGE, message)
            elif (inputFromPage.lower() == "r"):
                print("Type your message or press enter directly:")
                message = self.takeInput()
                return SystemMessage(FunctionType.DISAPPREOVE_REQUEST, PageType.EVALUATE_REQUESTS_PAGE, message)
            elif (inputFromPage.lower() == "q"):
                return SystemMessage(FunctionType.CHANGE_PAGE, PageType.EVALUATE_REQUESTS_PAGE, None)
            else:
                print("Wrong Input!")
