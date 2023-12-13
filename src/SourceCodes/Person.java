package SourceCodes;

abstract class Person {
    private String firstName, lastName;
    private Password password;

    public Person(String firstName, String lastName,Password password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password=password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}

