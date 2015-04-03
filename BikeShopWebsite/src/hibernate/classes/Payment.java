package hibernate.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
	@NamedQuery(name=Payment.QUERY_ALL, query="from Payment")
})
@Entity
@Table(name = "payment", catalog = "hibernate_test_database")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final String QUERY_ALL = "Payment.All";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;
	
	@Column(nullable = false)
	private String cardNumber;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date expiryDate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable = false)
	private boolean successful;

	// default constructor for hibernate
	public Payment() {
		super();
	}
	
	public Payment(String cardNumber, Date expiryDate, User user, boolean successful) {
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
		this.user = user;
		this.successful = successful;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public int getId() {
		return id;
	}
}
