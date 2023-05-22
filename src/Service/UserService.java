package Service;

import Repository.UserRepository;

public class UserService {
    public UserRepository userRepository;
    public UserService() {
        this.userRepository = new UserRepository();
    }
}