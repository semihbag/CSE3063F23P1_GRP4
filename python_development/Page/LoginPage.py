from Page import Page
from Page import PageType
from System import FunctionType
from System import SystemMessage

class LoginPage(Page):
    
    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.LoginPage)
        self.setName("Login Page")

    
    def runPage(self):
        self.showContent()
        userInfo = [None, None]
        print("Username: ")
        userInfo[0] = self.takeInput()
        print("Password: ")
        userInfo[1] = self.takeInput()

        return SystemMessage(FunctionType.LOGIN, None,userInfo)