package com.taj.savetravels.controllers;

import com.taj.savetravels.models.Expense;
import com.taj.savetravels.services.ExpensesService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExpensesController {
  private final ExpensesService expensesService;

  public ExpensesController(ExpensesService expensesService) {
    this.expensesService = expensesService;
  }

  @GetMapping("/")
  public String index(Model model, @ModelAttribute("expense") Expense expense) {
    List<Expense> allExpenses = expensesService.allExpenses();
    model.addAttribute("allExpenses", allExpenses);

    return "index.jsp";
  }

  @PostMapping("/expenses/new")
  public String createNewExpense(
    @Valid @ModelAttribute("expense") Expense expense,
    BindingResult result,
    Model model
  ) {
    if (result.hasErrors()) {
      List<Expense> allExpenses = expensesService.allExpenses();
      model.addAttribute("allExpenses", allExpenses);
      return "index.jsp";
    }

    Expense newExpense = expensesService.createExpense(expense);
    return String.format("redirect:/expenses/%d", newExpense.getId());
  }

  @GetMapping("/expenses/{id}")
  public String displayExpenseById(@PathVariable("id") Long id, HttpSession session) {
    session.setAttribute("expense", expensesService.findExpense(id));

    return "displayExpense.jsp";
  }

  @GetMapping("/expenses/edit/{id}")
  public String displayEditExpenseForm(@PathVariable("id") Long id, Model model) {
    model.addAttribute("expense", expensesService.findExpense(id));
    return "editExpenseForm.jsp";
  }

  @PutMapping("/expenses/update/{id}")
  public String updateExpense(
    @Valid @ModelAttribute("expense") Expense expense,
    BindingResult result,
    Model model
  ) {
    if (result.hasErrors()) {
      model.addAttribute("expense", expense);
      return "editExpenseForm.jsp";
    }
    expensesService.updateExpense(expense);
    return String.format("redirect:/expenses/%d", expense.getId());
  }

  @DeleteMapping("/expenses/delete/{id}")
  public String deleteExpense(@PathVariable("id") Long id) {
    expensesService.deleteExpense(id);
    return "redirect:/";
  }
}