package com.snva.reactive.service;

import com.snva.reactive.model.User;
import com.snva.reactive.respository.DepartmentRepository;
import com.snva.reactive.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@Slf4j
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Mono<User> createUser(User user){
        return  userRepository.save(user);
    }

    public Mono<User> findById(Integer userId){
        return  userRepository.findById(userId);
    }

    public Flux<User> getAllUser(){
        return  userRepository.findAll();
    }

    public  Mono<User> updateUser(Integer userId, User user){
    return  userRepository.findById(userId)
            .flatMap(dbuser->{
                dbuser.setAge(user.getAge());
                dbuser.setSalary(user.getSalary());
                return  userRepository.save(dbuser);
            });
    }

    public  Mono<User> deleteUser(Integer userId){
        return  userRepository.findById(userId)
                .flatMap(dbuser->userRepository.delete(dbuser).then(Mono.just(dbuser)));
    }


    public Flux<User> findUsersByAge(int age                                     ){
        return userRepository.findByAge(age);
    }

    public Flux<User> fetchUsers(List<Integer> userIds){
        return  Flux.fromIterable(userIds)
                .parallel()
                .runOn(Schedulers.boundedElastic() )
                .flatMap(i->findById(i))
                .ordered((u1,u2)->u1.getId()-u2.getId());
    }


    public Flux<User> getDepartmentByUserId(Integer userId){
        return  departmentRepository.findByUserId(userId);
    }

}
