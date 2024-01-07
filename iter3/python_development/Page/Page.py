from abc import ABC, abstractmethod

class Page(ABC):
    pageType = None
    name = None
    content = None

    def __init__(self, content):
        self.setContent(content)


    @abstractmethod
    def runPage(self):
        pass


    def showContent(self):
        print("\u001B[31;1m____________________________________________________________________________________________________________\u001B[0m")
        print(self.content)


    def takeInput(self):
        inp = input()
        return inp
    
    
    ### GETTER SETTER METHODS
    def getType(self):
        return self.pageType
    
    def setType(self, pt):
        self.pageType = pt

    def getName(self):
        return self.name
    
    def setName(self, name):
        self.name = name

    def getContent(self):
        return self.content
    
    def setContent(self, content):
        self.content = content