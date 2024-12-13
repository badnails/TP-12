package com.example.practice.Database;

public class searchQuery {
    String name;
    String country;
    Integer age;
    Integer salary;
    String position;
    Integer jersey;

    public searchQuery(String name, String country, Integer age, Integer salary, String position, Integer jersey) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.jersey = jersey;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public Integer getJersey() {
        return jersey;
    }
}
