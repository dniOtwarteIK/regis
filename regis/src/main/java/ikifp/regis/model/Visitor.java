package ikifp.regis.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "visitors")
public class Visitor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	@Size(max = 100)
	@NotNull
	private String email;

	@Column
	@NotNull
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Column
	private String phone;

	@Column
	@Max(60)
	private int participantsNumber; // max 60

	@OneToOne(mappedBy = "visitor", cascade = CascadeType.ALL)
	private VerificationToken token;

	@Column
	private boolean enable;

	/*
	 * @ManyToMany(mappedBy = "visitorsList") Set<Lecture> lectures = new
	 * HashSet<>();
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

	/*
	 * public Set<Lecture> getLectures() { return lectures; }
	 * 
	 * public void setLectures(Set<Lecture> lectures) { this.lectures = lectures; }
	 */

	public VerificationToken getToken() {
		return token;
	}

	public void setToken(VerificationToken token) {
		this.token = token;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
