from python_development.Page.Page import Page
from python_development.Page.PageType import PageType
from python_development.System.FunctionType import FunctionType
from python_development.System.SystemMessage import SystemMessage


class MyNotificationsPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType("MY_NOTIFICATIONS_PAGE")
        self.setName("My Notifications Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage("READ_NOTIFICATIONS", "MAIN_MENU_PAGE", None)
    

    