from Page import Page
from Page import PageType
from System import FunctionType
from System import SystemMessage

class MyNotificationsPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.MY_NOTIFICATIONS_PAGE)
        self.setName("My Notifications Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage(FunctionType.READ_NOTIFICATIONS, PageType.MAIN_MENU_PAGE, None)
    

    