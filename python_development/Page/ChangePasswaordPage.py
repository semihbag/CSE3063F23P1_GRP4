from Page import Page
from Page import PageType
from System import FunctionType
from System import SystemMessage

class ChangePasswaordPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.CHANGE_PASSWORD_PAGE)
        self.setName("Change Password Page")

    
    def runPage(self):
        self.showContent()
        passwords = [None, None]
        print("Enter your current password: ")
        passwords[0] = self.takeInput()
        print("Enter your new password: ")
        passwords[1] = self.takeInput()

        return SystemMessage(FunctionType.CHANGE_PASSWORD, PageType.PROFILE_PAGE, passwords)