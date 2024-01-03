from Page import Page
from Page import PageType
from System import FunctionType
from System import SystemMessage

class MyStudentsPage(Page):

    def __init__(self, content):
        super().__init__(content)
        self.setType(PageType.MyStudentsPage)
        self.setName("All Students Page")


    def runPage(self):
        self.showContent()
        self.takeInput()
        return SystemMessage(FunctionType.CHANGE_PAGE, PageType.MAIN_MENU_PAGE, None)
