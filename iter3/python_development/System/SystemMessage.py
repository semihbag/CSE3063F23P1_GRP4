from python_development.Page.PageType import PageType
from python_development.System.FunctionType import FunctionType

class SystemMessage:
    
    functionType = None
    nextPageType = None
    inputFromPAge = None

    def __init__(self, functionType, pageType, i):
        self.setFuncionType(functionType)
        self.setNextPageType(pageType)
        self.setInput(i)
        
    
    def getFunctionType(self):
        return self.functionType
    
    def setFuncionType(self, functionType):
        self.functionType = functionType

    def getNextPageType(self):
        return self.nextPageType
    
    def setNextPageType(self, nextPageType):
        self.nextPageType = nextPageType

    def getInput(self):
        return self.inputFromPAge
    
    def setInput(self, inputFromPAge):
        self.inputFromPAge = inputFromPAge