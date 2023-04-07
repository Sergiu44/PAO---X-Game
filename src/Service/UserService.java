package Service;

import Repository.UserRepository;

public class UserService {
    private UserRepository userRepository;
    public UserService() {
        this.userRepository = new UserRepository();
    }
}