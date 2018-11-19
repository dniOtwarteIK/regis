package ikifp.regis.persistence;

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

@Component("visitorService")
public class VisitorService {

	DatabaseConnector connector;

	public VisitorService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Visitor> getAll() {
		return connector.getSession().createCriteria(Visitor.class).list();
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
		String hql = "FROM Visitor V WHERE V.token= :usrTokenParam";
		Query query = connector.getSession().createQuery(hql);
		query.setParameter("usrTokenParam", token);
		Visitor visitor = (Visitor) query.list().get(0);
		return visitor;
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
