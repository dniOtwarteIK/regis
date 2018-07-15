package ikifp.regis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lectures")
public class Lecture {

	private int maxPeople = 120;

	private boolean isAvailableForEnrollment = true;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String title;

	@Column
	private int time;

	@ManyToMany
	@JoinTable(name = "visitor_lecture", joinColumns = { @JoinColumn(name = "lecture_id") }, inverseJoinColumns = {
			@JoinColumn(name = "visitor_email") })
	Set<Visitor> visitorsList = new HashSet<>();

	// methods - getters and setters:
	public int getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
