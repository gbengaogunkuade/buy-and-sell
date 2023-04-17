package com.ogunkuade.microservicesmanager.service;


import com.ogunkuade.microservicesmanager.repository.RoleRepository;
import com.ogunkuade.microservicesmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BNSService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public BNSService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public String getHome(){
        return "home";
    }




}
