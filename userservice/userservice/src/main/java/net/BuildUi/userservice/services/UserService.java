package net.BuildUi.userservice.services;

import net.BuildUi.userservice.entity.UserInfo;
import net.BuildUi.userservice.models.UserInfoDto;
import net.BuildUi.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserInfoDto createOrUpdate(UserInfoDto userInfoDto){
        //use functional interface
        Function<UserInfo , UserInfo> updatedUser = user ->{
            user.setUserId(userInfoDto.getUserId());
            user.setFirstName(userInfoDto.getFirstName());
            user.setLastName(userInfoDto.getLastName());
            user.setEmail(userInfoDto.getEmail());
            user.setPhoneNo(userInfoDto.getPhoneNo());
            user.setProfilePhoto(userInfoDto.getProfilePhoto());

            return userRepository.save(user);
        };

        Supplier<UserInfo> createdUser = ()->{
            return userRepository.save(userInfoDto.transformToEntity());
        };

        UserInfo userInfo = userRepository.findByUserId(userInfoDto.getUserId())
                .map(updatedUser)
                .orElseGet(createdUser);

        return userInfoDto;
    }

    public UserInfoDto getUser(String userId) throws Exception {
        Optional<UserInfo> user = userRepository.findByUserId(userId);

        if(user.isEmpty()){
            throw new Exception("User not valid");
        }

        return UserInfoDto.builder().userId(user.get().getUserId())
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .email(user.get().getEmail())
                .phoneNo(user.get().getPhoneNo())
                .profilePhoto(user.get().getProfilePhoto())
                .build();
    }
}
