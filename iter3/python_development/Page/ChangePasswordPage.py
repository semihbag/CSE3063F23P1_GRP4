from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.System.FunctionType import FunctionType
from python_development.System.SystemMessage import SystemMessage
class ChangePasswordPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("CHANGE_PASSWORD_PAGE")
        self.setName("Change Password Page")

    
    def runPage(self):
        self.showContent()
        passwords = [None, None]
        print("Enter your current password: ")
        passwords[0] = self.takeInput()
        print("Enter your new password: ")
        passwords[1] = self.takeInput()

        return SystemMessage("CHANGE_PASSWORD", "PROFILE_PAGE", passwords)