package com.sample.app.library.services;

import com.sample.app.library.dtos.user.LoginDTO;
import com.sample.app.library.dtos.user.UserDTO;
import com.sample.app.library.repositories.UserRepository;
import com.sample.app.library.repositories.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private static final String USER_DATA_API_URL = "https://jsonplaceholder.org/users";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void loadUserData() {
        final UserDTO[] userDTOS = restTemplate.getForEntity(USER_DATA_API_URL, UserDTO[].class).getBody();
        final List<User> users = Arrays.stream(Objects.requireNonNull(userDTOS))
                .map(userDTO -> {
                    final LoginDTO userDTOLogin = userDTO.getLogin();
                    return new User((long) userDTO.getId(), userDTO.getFirstname(), userDTO.getLastname(),
                            userDTOLogin.getUsername(), userDTOLogin.getPassword());
                }).toList();
        final List<User> loadedUsers = userRepository.saveAllAndFlush(users);
        System.out.println(loadedUsers);
    }

    public User getUser(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public User findUser(final String userName, final String password) {
        return userRepository.findByUsernameAndPassword(userName, password)
                .orElseThrow(RuntimeException::new);
    }
}
