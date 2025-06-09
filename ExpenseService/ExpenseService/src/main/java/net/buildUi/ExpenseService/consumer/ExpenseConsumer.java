package net.buildUi.ExpenseService.consumer;

import net.buildUi.ExpenseService.models.ExpenseDto;
import net.buildUi.ExpenseService.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExpenseConsumer {
    @Autowired
    private ExpenseService expenseService;

    @KafkaListener(topics = "${spring.kafka.topic.name}" , groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto expenseDto){
        try{
            expenseService.createExpense(expenseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
