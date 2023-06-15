package com.taj.savetravels.services;

import com.taj.savetravels.models.Expense;
import com.taj.savetravels.repositories.ExpensesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService {
  private final ExpensesRepository expensesRepository;

  public ExpensesService(ExpensesRepository expensesRepository) {
    this.expensesRepository = expensesRepository;
  }

  public List<Expense> allExpenses() {
    return expensesRepository.findAll();
  }

  public Expense findExpense(Long id) {
    Optional<Expense> optionalExpense = expensesRepository.findById(id);

    return optionalExpense.orElse(null);
  }

  public Expense createExpense(Expense expense) {
    return expensesRepository.save(expense);
  }

  public Expense updateExpense(Expense expense) {
    Expense updatedExpense = findExpense(expense.getId());

    updatedExpense.setName(expense.getName());
    updatedExpense.setVendor(expense.getVendor());
    updatedExpense.setAmount(expense.getAmount());
    updatedExpense.setDescription(expense.getDescription());

    return expensesRepository.save(updatedExpense);
  }

  public void deleteExpense(Long id) {
    expensesRepository.delete(findExpense(id));
  }
}