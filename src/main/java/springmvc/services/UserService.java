package springmvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.enums.UserStatus;
import springmvc.model.User;
import springmvc.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void activateUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        user.setStatus(UserStatus.ACTIVATED);
        userRepository.save(user);
    }
}