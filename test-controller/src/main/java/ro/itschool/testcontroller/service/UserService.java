package ro.itschool.testcontroller.service;

import org.springframework.stereotype.Service;
import ro.itschool.testcontroller.controller.dto.User;
import ro.itschool.testcontroller.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private static final Map<Integer, User> users = new HashMap<>();

    static {
        users.put(1,
                User.builder()
                       .name("User1")
                       .age(20)
                        .email("user1@test.com")
                        .address("Test Address")
                        .build());
        users.put(2, new User("User2", 21, "user2@test.com", "Test Address 2"));
        users.put(3, new User("User3", 22, "user3@test.com", "Test Address 3"));
      users.put(4, new User("User4", 23, "user4@test.com", "Test Address 4"));
        users.put(5, new User("User5", 24, "user5@test.com", "Test Address 5"));
        users.put(6, new User("User6", 20, "user6@test.com", "Test Address 6"));
    }



    public Map<Integer, User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public List<User> getUserByFilters(String name, Integer age, String email, String address) {
        return users.values().stream()
                .filter(user -> (name == null || user.getName().equals(name))
                        && (age == null || user.getAge() == age)
                        && (email == null || user.getEmail().equals(email))
                        && (address == null || user.getAddress().equals(address)))
                .toList();
    }

    public void addUser(User user) {
        users.put(users.size() + 1, user);
    }

    public void addUser(User user, int id) {
        users.put(id, user);
    }

    public void removeUserById(int id) {
        users.remove(id);
    }

    public void updateAndLoadUserData(User currentUser, User newUser, int id) {
        currentUser.setName(newUser.getName() != null
                ? newUser.getName() : currentUser.getName());
        currentUser.setAge(newUser.getAge() != 0
                ? newUser.getAge() : currentUser.getAge());
        currentUser.setEmail(newUser.getEmail() != null
                ? newUser.getEmail() : currentUser.getEmail());
        currentUser.setAddress(newUser.getAddress() != null
                ? newUser.getAddress() : currentUser.getAddress());

        users.put(id, currentUser);
    }
}
