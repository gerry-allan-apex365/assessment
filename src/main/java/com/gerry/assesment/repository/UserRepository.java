package com.gerry.assesment.repository;

import com.gerry.assesment.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Iterable<User> findAllByFirstName(String firstName);
    Iterable<User> findAllBySurname(String surname);
}
