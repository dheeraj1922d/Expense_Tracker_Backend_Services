package net.BuildUi.userservice.consumers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.BuildUi.userservice.entity.UserInfo;
import net.BuildUi.userservice.models.UserInfoDto;
import net.BuildUi.userservice.repository.UserRepository;
import net.BuildUi.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoConsumer {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "${spring.kafka.topic.name}" , groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto event){
        try{
            userService.createOrUpdate(event);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
