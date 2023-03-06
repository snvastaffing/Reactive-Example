package com.snva.reactive.respository;

import com.snva.reactive.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
    @Query("select * from users where age >= $1")
    Flux<User> findByAge(int age);
}
