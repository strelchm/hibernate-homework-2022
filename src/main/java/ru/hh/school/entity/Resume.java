package ru.hh.school.entity;

import javax.persistence.*;

@Entity
public class Resume {
  // Если id берется из sequence-ов: мы сможем отправлять в бд запросы батчами.
  // Нужно учитывать, что описание sequence при создании таблицы также должно соответствовать
  // хиберовской сущности (см. create_resume.sql)
  //
  // Подробнее:
  // https://vladmihalcea.com/how-to-batch-insert-and-update-statements-with-hibernate/
  // https://vladmihalcea.com/from-jpa-to-hibernates-legacy-and-enhanced-identifier-generators/

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_id_seq")
  @SequenceGenerator(name = "resume_id_seq", sequenceName = "resume_id_seq", allocationSize = 10)
  private Integer id;

  private String description;

  public Resume() {}

  public Resume(String description) {
    this.description = description;
  }

}
