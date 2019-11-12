package rakhmedovrs.com.github;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author RakhmedovRS
 * @created 12-Nov-19
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", unique = true)
	private int id;

	@Column(name = "firstName", nullable = false)
	private int firstName;

	@Column(name = "lastName", nullable = false)
	private int lastName;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getFirstName()
	{
		return firstName;
	}

	public void setFirstName(int firstName)
	{
		this.firstName = firstName;
	}

	public int getLastName()
	{
		return lastName;
	}

	public void setLastName(int lastName)
	{
		this.lastName = lastName;
	}
}
