from enum import Enum

class Mark(Enum):
    SUCCESSFUL = 1
    ERROR_ALREADY_SENDED = 2
    ERROR_CREDIT_LIMIT = 3
    ERROR_CONFLICT = 4
    ERROR_SAME_TYPE = 5
    SELECTED = 6