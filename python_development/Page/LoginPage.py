from python_development.Page import Page
from python_development.Page import PageType
from python_development.System import FunctionType
from python_development.System import SystemMessage


class LoginPage(Page):
    
    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.LOGIN_PAGE)
        self.setName("Login Page")

    
    def runPage(self):
        self.showContent()
        userInfo = [None, None]
        print("Username: ")
        userInfo[0] = self.takeInput()
        print("Password: ")
        userInfo[1] = self.takeInput()

        return SystemMessage(FunctionType.LOGIN, None,userInfo)