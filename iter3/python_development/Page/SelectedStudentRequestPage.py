from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage


class SelectedStudentRequestPage(Page):
     
    def __init__(self, content):
        super().__init__(content)
        self.setType("SELECTED_STUDENT_REQUEST_PAGE")
        self.setName("Selected Student Request Page")


    def runPage(self):
        self.showContent()
        inputFromPage = None
        while(True):
            inputFromPage = self.takeInput()
            if(inputFromPage.lower() == "a"):
                print("Type your message or press enter directly:")
                message = self.takeInput()
                return SystemMessage("APPROVE_REQUEST", "EVALUATE_REQUESTS_PAGE", message)
            elif (inputFromPage.lower() == "r"):
                print("Type your message or press enter directly:")
                message = self.takeInput()
                return SystemMessage("DISAPPREOVE_REQUEST", "EVALUATE_REQUESTS_PAGE", message)
            elif (inputFromPage.lower() == "q"):
                return SystemMessage("CHANGE_PAGE", "EVALUATE_REQUESTS_PAGE", None)
            else:
                print("Wrong Input!")
