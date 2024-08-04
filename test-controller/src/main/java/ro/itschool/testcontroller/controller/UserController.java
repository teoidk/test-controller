package ro.itschool.testcontroller.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.testcontroller.controller.dto.User;
import ro.itschool.testcontroller.service.UserService;

import java.util.List;
import java.util.Map;

// http://localhost:8080/api/v1/users


public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<Integer, User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload<User>> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponsePayload<>(user, "User found"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponsePayload<>(null, "User not found"));
        }
    }

    // http://localhost:8080/api/v1/users/[orice]/[orice]
    @GetMapping("/{id}/{name}")
    public User getUserByIdAndPrintName(@PathVariable int id, @PathVariable String name) {
        System.out.println("id: " + id + ", name: " + name);
        return userService.getUserById(id);
    }

    // http://localhost:8080/api/v1/users/user?name=User1&age=21&email=user1@test.com&address=Test Address
    @GetMapping("/user")
    public List<User> getUserByFilter(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) Integer age,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) String address) {
        return userService.getUserByFilters(name, age, email, address);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam int id) {
        userService.removeUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestParam int id, @RequestBody User user) {
        User currentUser = userService.getUserById(id);
        if (currentUser != null) {
            userService.updateAndLoadUserData(currentUser, user, id);
            return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
        } else {
            userService.addUser(user, id);
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        }
    }
}
