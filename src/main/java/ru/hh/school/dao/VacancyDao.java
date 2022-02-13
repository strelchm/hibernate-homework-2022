package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.employers.StatisticsDto;
import ru.hh.school.entity.Area;

public class VacancyDao extends GenericDao{
  public VacancyDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * https://vladmihalcea.com/the-best-way-to-map-a-projection-query-to-a-dto-with-jpa-and-hibernate/
   * @param area
   * @return ru.hh.school.employers.StatisticsDto
   */
  public StatisticsDto getSalaryStatistics(Area area){
    return getSession().createQuery(
      "SELECT new ru.hh.school.employers.StatisticsDto(COUNT(v.id), MIN(v.compensationFrom), MAX(v.compensationTo))" +
           " FROM Vacancy v WHERE v.area = :area", StatisticsDto.class)
      .setParameter("area", area)
      .getSingleResult();
  }
}
