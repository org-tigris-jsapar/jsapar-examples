package org.jsapar.examples.basics.b3;

public class Address {
    private String street;

    public Address() {
    }

    public Address(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" + "street='" + street + '\'' + '}';
    }
}
