package classes;

public class Person {
    String city, name, surname, pesel;

    //Settery
    public Person(String city, String name, String surname, String pesel) {
        this.city = city;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    //Gettery
    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPesel() {
        return pesel;
    }
}
