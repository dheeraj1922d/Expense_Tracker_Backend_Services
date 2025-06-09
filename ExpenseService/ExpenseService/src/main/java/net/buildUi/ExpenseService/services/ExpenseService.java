package net.buildUi.ExpenseService.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.buildUi.ExpenseService.entities.Expense;
import net.buildUi.ExpenseService.models.ExpenseDto;
import net.buildUi.ExpenseService.repositories.ExpenseRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Boolean createExpense(ExpenseDto expenseDto) throws Exception {
        if(Objects.isNull(expenseDto.getCurrency())){
            expenseDto.setCurrency("inr");
        }

        try{
            expenseRepository.save(objectMapper.convertValue(expenseDto , Expense.class));
            return true;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public Boolean updateExpense(ExpenseDto expenseDto){
        Optional<Expense> expenseOpt = expenseRepository.findByUserIdAndExpenseId(expenseDto.getUserId() , expenseDto.getExpenseId());
        if(expenseOpt.isEmpty()){
            return false;
        }

        Expense expense = expenseOpt.get();

        expense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency()) ? expenseDto.getCurrency(): expense.getCurrency());
        expense.setAmount(Strings.isNotBlank(expenseDto.getAmount()) ? expenseDto.getAmount(): expense.getAmount());
        expense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant()) ? expenseDto.getMerchant(): expense.getMerchant());
        expenseRepository.save(expense);
        return true;
    }

    public List<ExpenseDto> getExpense(String userId){
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenses, new TypeReference<List<ExpenseDto>>() {});
    }
}
