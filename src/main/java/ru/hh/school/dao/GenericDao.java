package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

/**
 * GenericDao нужно для предоставления общих методов для работы с сущностями, например, можно описать
 * методы get или save, которые не часто будут различаться.
 */
public class GenericDao {
  private final SessionFactory sessionFactory;

  public GenericDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public <T> T get(Class<T> clazz, Serializable id) {
    return getSession().get(clazz, id);
  }

  public void save(Object object) {
    if (object == null) {
      return;
    }
    getSession().save(object);
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }


}
