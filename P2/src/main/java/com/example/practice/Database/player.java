package com.example.practice.Database;

import java.io.Serializable;

public class player implements Serializable {
    String name;
    String country;
    int age;
    double height;
    String club;
    String position;
    int jersey;
    int salary;
	boolean transferListed;
	
	public player(String name, String country, int age, double height, String club, String position, int jersey,
			int salary, boolean transferListed) {
		this.name = name;
		this.country = country;
		this.age = age;
		this.height = height;
		this.club = club;
		this.position = position;
		this.jersey = jersey;
		this.salary = salary;
		this.transferListed = transferListed;
	}

	public String getName() {
		return name;
	}

	public String getCountry() {
		return country;
	}

	public int getAge() {
		return age;
	}

	public double getHeight() {
		return height;
	}

	public String getClub() {
		return club;
	}

	public String getPosition() {
		return position;
	}

	public int getJersey() {
		return jersey;
	}

	public int getSalary() {
		return salary;
	}

	public void display()
	{
		System.out.print("[Name: "+name+"; Country: "+country+"; Age: "+age+"; Height: "+height+"; Club: "+club+"; Position: "+position+"; Jersey number: "+(jersey==-1?"N/A":jersey)+"; Weekly Salary: "+salary+"]\n");
	}
	public void setTransferListed(boolean transferListed) {
		this.transferListed = transferListed;
	}
	public boolean isTransferListed() {
		return transferListed;
	}
	public void setClub(String club) {
		this.club = club;
	}

    
}

