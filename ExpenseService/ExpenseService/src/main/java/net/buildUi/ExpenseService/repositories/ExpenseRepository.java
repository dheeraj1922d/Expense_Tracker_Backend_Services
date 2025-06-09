package net.buildUi.ExpenseService.repositories;

import net.buildUi.ExpenseService.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByUserIdAndCreatedAtBetween(String userId , Timestamp last , Timestamp curr);
    Optional<Expense> findByUserIdAndExpenseId(String userId , String expenseId);
}
