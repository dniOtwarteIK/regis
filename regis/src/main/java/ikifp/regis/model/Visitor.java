package ikifp.regis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "visitors")
public class Visitor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String email;

	@Column
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Column
	private String phone;

	@Column
	private int participantsNumber; // max 60

/*	@ManyToMany(mappedBy = "visitorsList")
	Set<Lecture> lectures = new HashSet<>();
 */
	// methods - getters and setters:
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(int participantsNumber) {
		this.participantsNumber = participantsNumber;
	}

/*	public Set<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(Set<Lecture> lectures) {
		this.lectures = lectures;
	}
	*/

}
