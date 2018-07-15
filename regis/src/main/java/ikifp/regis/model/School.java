package ikifp.regis.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "schools")
public class School {
	
	@Id
	private int number;
	
	@Column
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Visitor> visitors;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
