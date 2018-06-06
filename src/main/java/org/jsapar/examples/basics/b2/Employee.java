package org.jsapar.examples.basics.b2;

import java.time.LocalDate;

/**
 * A bean class needs 1. A default constructor. 2. Getters and setters for each field.
 */
public class Employee {
    private String    name;
    private int       employeeNumber;
    private Address   address;
    private LocalDate birthDate;

    /**
     * It is important to always have a default constructor in each bean class.
     */
    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate localDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" + "name='" + name + '\'' + ", employeeNumber=" + employeeNumber + ", address=" + address
                + ", birthDate=" + birthDate + '}';
    }
}
