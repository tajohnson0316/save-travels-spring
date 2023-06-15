package com.taj.savetravels.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(min = 2, message = "Please provide the name of the expense [min. (2) characters]")
  private String name;

  @NotBlank
  @Size(min = 2, message = "Please provide the name of the vendor [min. (2) characters]")
  private String vendor;

  @NotNull
  @DecimalMin(value = "0.01", message = "Please provide the expense's total value [min. $0.01]")
  private Double amount;

  @NotBlank
  @Size(min = 2, message = "Please provide a brief description of the expense (or put 'N/A')")
  private String description;

  @Column(updatable = false)
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createdAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date updatedAt;

  public Expense() {
  }

  public Expense(
    String name,
    String vendor,
    @NotNull Double amount,
    String description
  ) {
    this.name = name;
    this.vendor = vendor;
    this.amount = amount;
    this.description = description;
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = new Date();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public @NotNull Double getAmount() {
    return amount;
  }

  public void setAmount(@NotNull Double amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}