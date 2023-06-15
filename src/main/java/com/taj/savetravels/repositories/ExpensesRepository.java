package com.taj.savetravels.repositories;

import com.taj.savetravels.models.Expense;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExpensesRepository extends CrudRepository<Expense, Long> {
  @NotNull
  List<Expense> findAll();
}