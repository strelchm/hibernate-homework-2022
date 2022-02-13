package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Employer;

public class EmployerDao extends GenericDao {

  public EmployerDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * Загрузка вакансий, связанных с работодателем (и в некоторых случаях
   * избежание org.hibernate.LazyInitializationException)
   * @param employerId
   * <p>
   * https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/
   */
  public Employer getEager(int employerId) {
    return getSession()
      .createQuery("SELECT empl FROM Employer empl LEFT JOIN FETCH empl.vacancies WHERE empl.id = :employerId", Employer.class)
      .setParameter("employerId", employerId)
      .getSingleResult();
  }

  /**
   * "merge" operation generates 1 select and 1 update. It can be optimized to 1 update when using the Hibernate-specific
   * "session update" operation
   * https://vladmihalcea.com/how-to-optimize-the-merge-operation-using-update-while-batching-with-jpa-and-hibernate/
   * getSession().merge(employer);
   */
  public void refresh(Employer employer) {
    getSession().update(employer);
  }
}
