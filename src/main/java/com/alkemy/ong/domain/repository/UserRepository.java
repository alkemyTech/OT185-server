package com.alkemy.ong.domain.repository;

import com.alkemy.ong.domain.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

<<<<<<< HEAD
    Optional<User> findByEmail(String username);

=======
    Optional<User> findUserByEmail(String email);
>>>>>>> feature/OT185-32

}
