from iter3.python_development.Page.Page import Page
from iter3.python_development.Page.PageType import PageType
from iter3.python_development.System.FunctionType import FunctionType
from iter3.python_development.System.SystemMessage import SystemMessage


class LoginPage(Page):
    
    def __init__(self, content):
        super().__init__(content)
        self.setType("LOGIN_PAGE")
        self.setName("Login Page")
    
    def runPage(self):
        self.showContent()
        userInfo = [None, None]
        userInfo[0] = input("Username: ")
        userInfo[1] = input("Password: ")

        return SystemMessage("LOGIN", None,userInfo)