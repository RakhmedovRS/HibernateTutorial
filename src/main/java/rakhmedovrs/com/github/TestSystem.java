package rakhmedovrs.com.github;

import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 13-Nov-19
 */
public class TestSystem
{
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("HibernateTutorial");

	public static void main(String[] args)
	{
		try
		{
			addCustomer(1, "Edd1", "Smith1");
			addCustomer(2, "Edd2", "Smith2");
			addCustomer(3, "Edd3", "Smith3");
			addCustomer(4, "Edd4", "Smith4");
			addCustomer(5, "Edd5", "Smith5");
			System.out.println("----------------");
			getCustomer(3);
			System.out.println("----------------");
			getCustomers();
			System.out.println("----------------");
			deleteCustomer(4);
		}
		finally
		{
			ENTITY_MANAGER_FACTORY.close();
		}
	}

	public static void addCustomer(int id, String firstName, String lastName)
	{
		EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction entityTransaction = null;
		try
		{
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Customer customer = new Customer();
			customer.setId(id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			entityManager.persist(customer);
			entityTransaction.commit();
		}
		catch (Exception exception)
		{
			if (entityTransaction != null)
			{
				entityTransaction.rollback();
			}
			exception.printStackTrace();
		}
		finally
		{
			entityManager.close();
		}
	}

	public static void getCustomer(int id)
	{
		EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Customer c WHERE c.id = :ID";
		TypedQuery<Customer> typedQuery = entityManager.createQuery(query, Customer.class);
		typedQuery.setParameter("ID", id);
		Customer customer = null;
		try
		{
			customer = typedQuery.getSingleResult();
			System.out.println(customer);
		}
		catch (NoResultException exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			entityManager.close();
		}
	}

	public static void getCustomers()
	{
		EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Customer c where c.id IS NOT NULL";
		TypedQuery<Customer> typedQuery = entityManager.createQuery(query, Customer.class);

		try
		{
			typedQuery.getResultList().forEach(System.out::println);
		}
		catch (NoResultException exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			entityManager.close();
		}
	}

	public static void changeFirstName(int id, String firstName)
	{
		EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction entityTransaction = null;
		Customer customer = null;
		try
		{
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.find(Customer.class, id);
			customer.setFirstName(firstName);
			entityTransaction.commit();
		}
		catch (Exception exception)
		{
			if (entityTransaction != null)
			{
				entityTransaction.rollback();
			}
			exception.printStackTrace();
		}
		finally
		{
			entityManager.close();
		}
	}

	public static void deleteCustomer(int id)
	{
		EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction entityTransaction = null;
		Customer customer = null;
		try
		{
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			customer = entityManager.find(Customer.class, id);
			entityManager.remove(customer);
			entityManager.persist(customer);
			entityTransaction.commit();
		}
		catch (Exception exception)
		{
			if (entityTransaction != null)
			{
				entityTransaction.rollback();
			}
			exception.printStackTrace();
		}
		finally
		{
			entityManager.close();
		}
	}
}
