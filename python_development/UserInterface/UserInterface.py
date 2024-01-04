from python_development.Page import *
from python_development.System import *

class UserInterface:

    def __init__(self):
        self.pages = []
        self.currentPage = None
        self.systemMessage = None

    
    def display(self):
        self.setSystemMessage(self.currentPage.runPage())
        


    def addPage(self, page):

        if (len(self.pages) == 0):
            self.pages.append(page)
            return True
        else:
            for p in self.pages:
                if (p.getType() == page.getType()):
                    return False
            self.pages.append(page)
            return True
        
    
    def setCurrentPage(self, pageType):
        for p in self.pages:
            if (p.getType() == pageType):
                self.currentPage = p
                return True
        return False
    

    def selectPage(self, pageType):
        for p in self.pages:
            if (p.getType() == pageType):
                return p
        return None
    ############### BİR ÜST SATIRDA NULL DÖNDÜRMEK YERİNE EXCEPTİON KONULABİLİR UNUTMAAAAA

    # GETTER - SETTER

    def getPages(self):
        return self.pages
    
    def setPages(self, pages):
        self.pages = pages

    def getCurrentPage(self):
        return self.currentPage
    
    def getSystemMessage(self):
        return self.systemMessage
    
    def setSystemMessage(self, systemMessage):
        self.systemMessage = systemMessage



        





