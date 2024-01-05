import logging

from python_development.System.SystemClass import SystemClass
from python_development.UserInterface.UserInterface import UserInterface

if __name__ == "__main__":
    try:
        user_interface = UserInterface()
        system = SystemClass(user_interface)
        system.run()

    except Exception as e:
        logging.exception(f"Error found: {e}")
    
    except KeyboardInterrupt:
        pass
