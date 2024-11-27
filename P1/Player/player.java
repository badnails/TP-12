package Player;
public class player {
    String name;
    String country;
    int age;
    double height;
    String club;
    String position;
    int jersey;
    int salary;
	
	public player(String name, String country, int age, double height, String club, String position, int jersey,
			int salary) {
		this.name = name;
		this.country = country;
		this.age = age;
		this.height = height;
		this.club = club;
		this.position = position;
		this.jersey = jersey;
		this.salary = salary;
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
		System.out.println("Name: "+name);
		System.out.println("Country: "+country);
		System.out.println("Age: "+age);
		System.out.println("Height: "+height);
		System.out.println("Club: "+club);
		System.out.println("Position: "+position);
		System.out.println("Jersey number: "+jersey);
		System.out.println("Weekly Salary: "+salary);
	}

    
}

