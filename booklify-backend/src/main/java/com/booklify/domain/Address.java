package com.booklify.domain;


import jakarta.persistence.*;

@Entity
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = true)
    protected String unitNumber;

    @Column(nullable = true)
    protected String street ;

    @Column(nullable = true)
    protected String suburb;

    @Column(nullable = false)
    protected String city;

    @Column(nullable = false)
    protected String province;

    @Column(nullable = false)
    protected String country ;

    @Column(nullable = false)
    protected String postalCode;

    protected Address(){

    }

    private Address(Builder builder){
        this.unitNumber = builder.unitNumber;
        this.street = builder.street;
        this.suburb= builder.suburb;
        this.city=builder.city;
        this.province=builder.province;
        this.country=builder.country;
        this.postalCode= builder.postalCode;

    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "unitNumber='" + unitNumber + '\'' +
                "street='" + street + '\'' +
                ", suburb='" + suburb + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", Country='" + country + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
    public static class Builder {
        protected String unitNumber ;
        protected String street ;
        protected String suburb;
        protected String province;
        protected String city;
        protected String country ;
        protected String postalCode;

        public Builder setUnitNumber(String unitNumber) {
            this.unitNumber = unitNumber;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }
        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }
        public Builder copy(Address address){
            this.unitNumber = address.unitNumber;
            this.street = address.street;
            this.suburb= address.suburb;
            this.city=address.city;
            this.province=address.province;
            this.country=address.country;
            this.postalCode= address.postalCode;
            return this;

        }

        public Address build(){return new Address(this);}

    }
}
