package ru.hh.school.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Vacancy {

  @Id
  @Column(name = "vacancy_id", columnDefinition = "serial")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "employer_id")
  private Employer employer;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area_id")
  private Area area;

  private String title;

  private String description;

  private Integer compensationFrom;

  private Integer  compensationTo;

  private Boolean compensationGross;

  private LocalDateTime creationTime;

  private LocalDateTime archivingTime;

  public Vacancy() {
  }

  public Vacancy(Employer employer) {
    this.employer = employer;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCompensationFrom(Integer compensationFrom) {
    this.compensationFrom = compensationFrom;
  }

  public void setCompensationTo(Integer compensationTo) {
    this.compensationTo = compensationTo;
  }

  public LocalDateTime getArchivingTime() {
    return archivingTime;
  }

  public void setArchivingTime(LocalDateTime archivingTime) {
    this.archivingTime = archivingTime;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vacancy vacancy = (Vacancy) o;
    return Objects.equals(employer, vacancy.getEmployer()) && Objects.equals(creationTime, vacancy.getCreationTime());
  }

  @Override
  public int hashCode() {
    return Objects.hash(employer, creationTime); // https://vladmihalcea.com/hibernate-facts-equals-and-hashcode/
  }

}
