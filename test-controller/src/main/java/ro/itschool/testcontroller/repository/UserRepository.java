package ro.itschool.testcontroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.itschool.testcontroller.persistence.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
