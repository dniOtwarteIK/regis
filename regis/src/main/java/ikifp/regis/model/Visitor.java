package ikifp.regis.model;

import javax.persistence.*;

@Entity
@Table(name = "agents")
public class Visitor {
	
	@Id
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private int participantsNumber; // max 60
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
}
