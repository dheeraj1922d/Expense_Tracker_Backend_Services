package net.buildUi.ExpenseService.controllers;

import lombok.NonNull;
import net.buildUi.ExpenseService.models.ExpenseDto;
import net.buildUi.ExpenseService.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expense/v1")
public class ExpenseControllers {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping(path = "/getExpense")
    public ResponseEntity<List<ExpenseDto>> getExpense(@RequestHeader(value = "X-User-Id") @NonNull String userId){
        try{
            List<ExpenseDto> expenseDtoList = expenseService.getExpense(userId);
            return new ResponseEntity<>(expenseDtoList, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/addExpense")
    public ResponseEntity<?> addExpenses(@RequestHeader(value = "X-User-Id") @NonNull String userId,@RequestBody ExpenseDto expenseDto){
        try{
            expenseDto.setUserId(userId);
            return new ResponseEntity<>(expenseService.createExpense(expenseDto), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Boolean> health(){
        return new ResponseEntity<>(true , HttpStatus.OK);
    }
}
