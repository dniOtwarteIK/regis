package ikifp.regis.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ikifp.regis.model.VerificationToken;
import ikifp.regis.model.Visitor;
import ikifp.regis.persistence.dto.VisitorDto;

@Component("visitorService")
public class VisitorService {

	DatabaseConnector connector;

	public VisitorService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<VisitorDto> getAll() {
		//return connector.getSession().createCriteria(Visitor.class).list();
		Collection<Visitor> visitors = connector.getSession().createCriteria(Visitor.class).list();
		Collection<VisitorDto> visitorsDto = new ArrayList<>();
		for (Visitor visitor : visitors) {
			VisitorDto visitorDto = new VisitorDto();
			visitorDto.setId(visitor.getId());
			visitorDto.setEmail(visitor.getEmail());
			visitorDto.setPhone(visitor.getPhone());
			visitorDto.setParticipantsNumber(visitor.getParticipantsNumber());
			visitorDto.setToken(visitor.getToken().getToken()); // get Verification token . get String token
			visitorDto.setEnable(visitor.isEnable());
			
			visitorsDto.add(visitorDto);
		}		
		
		return visitorsDto;
	}

	public boolean addVisitor(Visitor visitor) {
		boolean created = false;
		String hql = "FROM Visitor V WHERE V.email= :usrEmailParam";
		Query query = connector.getSession().createQuery(hql);
		query.setParameter("usrEmailParam", visitor.getEmail());
		if (query.list().size() != 0) {
			created = false;
		} else {
			Transaction transaction = connector.getSession().beginTransaction();
			connector.getSession().save(visitor);
			transaction.commit();
			created = true;
		}
		return created;
	}

	public Visitor findVisitorByEmail(String email) {
		String hql = "FROM Visitor V WHERE V.email= :usrEmailParam";
		Query query = connector.getSession().createQuery(hql);
		query.setParameter("usrEmailParam", email);
		if (query.list().size() == 0) {
			return null;
		} else {
			Visitor visitor = (Visitor) query.list().get(0);
			return visitor;
		}
	}

	public Visitor findVisitorByToken(String token) {
		String hql = "FROM VerificationToken V WHERE V.token= :usrTokenParam";
		Query query = connector.getSession().createQuery(hql);
		query.setParameter("usrTokenParam", token);
		VerificationToken verificationToken = (VerificationToken) query.list().get(0);
		return verificationToken.getVisitor();
	}

	public boolean confirmRegistration(Visitor visitor) {
		boolean isEnabledChanged = false;
		if (visitor.isEnable()) {
			return isEnabledChanged;
		} else {
			visitor.setEnable(true);
			Transaction transaction = connector.getSession().beginTransaction();
			connector.getSession().update(visitor);
			transaction.commit();
			return isEnabledChanged = true;
		}
	}

}
