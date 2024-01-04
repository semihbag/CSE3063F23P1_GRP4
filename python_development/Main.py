from python_development.System.SystemClass import SystemClass
from python_development.UserInterface.UserInterface import UserInterface

if __name__ == "__main__":
    try:
        user_interface = UserInterface()
        system = SystemClass(user_interface)
        system.run()

    except Exception as e:
        print(f"Error found: {e}")