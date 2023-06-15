package com.taj.savetravels.controllers;

import com.taj.savetravels.models.Expense;
import com.taj.savetravels.services.ExpensesService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    BindingResult result
  ) {
    if (result.hasErrors()) {
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
}