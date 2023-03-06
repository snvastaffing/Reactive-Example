package com.snva.reactive.respository;

import com.snva.reactive.model.Department;
import com.snva.reactive.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DepartmentRepository extends ReactiveCrudRepository<Department, Integer> {
    @Query("select * from department where user_id >= $1")
    Flux<User> findByUserId(Integer userId);
}
