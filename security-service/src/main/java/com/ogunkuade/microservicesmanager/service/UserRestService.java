package com.ogunkuade.microservicesmanager.service;


import com.ogunkuade.microservicesmanager.dto.UserRequestDto;
import com.ogunkuade.microservicesmanager.dto.UserResponseDto;
import com.ogunkuade.microservicesmanager.dto.UserUpdateRequestDto;
import com.ogunkuade.microservicesmanager.entity.Role;
import com.ogunkuade.microservicesmanager.entity.User;
import com.ogunkuade.microservicesmanager.enums.RoleType;
import com.ogunkuade.microservicesmanager.exception.UnmatchedPasswordException;
import com.ogunkuade.microservicesmanager.exception.UserAlreadyExistsException;
import com.ogunkuade.microservicesmanager.exception.UserNotFoundException;
import com.ogunkuade.microservicesmanager.repository.UserRepository;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserRestService {


    private User user;
    private List<User> userList;
    private User savedUser;
    private User existingUser;
    private UserResponseDto userResponseDto;
    private List<UserResponseDto> userResponseDtoList;



    private final LoadBalancerClient loadBalancerClient;
    private final UserRepository userRepository;
    private final PrincipalUserDetailsService principalUserDetailsService;
    private final PasswordEncoder passwordEncoder;


    public UserRestService(LoadBalancerClient loadBalancerClient, UserRepository userRepository, PrincipalUserDetailsService principalUserDetailsService, PasswordEncoder passwordEncoder) {
        this.loadBalancerClient = loadBalancerClient;
        this.userRepository = userRepository;
        this.principalUserDetailsService = principalUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }



    //CHECK FOR USER BY ID
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Boolean checkForId(Long id){
        return userRepository.existsById(id);
    }


    //CHECK FOR USER BY USERNAME
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Boolean checkForUsername(String username){
        return userRepository.existsByUsername(username);
    };




    //CREATE USER
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws UserAlreadyExistsException, UnmatchedPasswordException {
        if(userRepository.existsByUsername(userRequestDto.getUsername())){
            throw new UserAlreadyExistsException(String.format("USER WITH THE USERNAME %s ALREADY EXIST", userRequestDto.getUsername()));
        }
        if(!userRequestDto.getPassword().equals(userRequestDto.getPassword2())){
            throw new UnmatchedPasswordException(String.format("Passwords do not match"));
        }

        user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setPassword2(passwordEncoder.encode(userRequestDto.getPassword2()));
        user.setFirstname(userRequestDto.getFirstname());
        user.setLastname(userRequestDto.getLastname());
        user.setGender(userRequestDto.getGender());
        user.setEmail(userRequestDto.getEmail());
        if(userRequestDto.getUsername().equals("gbenga") || userRequestDto.getUsername().equals("rotimi")){
            user.setRoles(
                    Arrays.asList(
                            new Role(RoleType.USER.name()),
                            new Role(RoleType.ADMIN.name())
                    )
            );
        } else{
            user.setRoles(
                    Arrays.asList(
                            new Role(RoleType.USER.name())
                    )
            );
        }
        savedUser = userRepository.save(user);
        userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setUsername(savedUser.getUsername());
        userResponseDto.setFirstname(savedUser.getFirstname());
        userResponseDto.setLastname(savedUser.getLastname());
        userResponseDto.setGender(savedUser.getGender());
        userResponseDto.setEmail(savedUser.getEmail());
        return userResponseDto;
    }



    //UPDATE USER BY ID
    public UserResponseDto updateUserById(UserUpdateRequestDto userUpdateRequestDto, Long id) throws UserNotFoundException {
        existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("USER WITH THE ID %d DOES NOT EXISTS", id)));
        existingUser.setFirstname(userUpdateRequestDto.getFirstname());
        existingUser.setLastname(userUpdateRequestDto.getLastname());
        existingUser.setGender(userUpdateRequestDto.getGender());
        existingUser.setEmail(userUpdateRequestDto.getEmail());
        savedUser = userRepository.save(existingUser);
        userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setUsername(savedUser.getUsername());
        userResponseDto.setFirstname(savedUser.getFirstname());
        userResponseDto.setLastname(savedUser.getLastname());
        userResponseDto.setGender(savedUser.getGender());
        userResponseDto.setEmail(savedUser.getEmail());
        return userResponseDto;
    }




    //GET USER BY ID
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public UserResponseDto getUserById(Long id) throws UserNotFoundException {
        existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("USER WITH THE ID %d DOES NOT EXISTS", id)));
        userResponseDto = new UserResponseDto();
        userResponseDto.setId(existingUser.getId());
        userResponseDto.setUsername(existingUser.getUsername());
        userResponseDto.setFirstname(existingUser.getFirstname());
        userResponseDto.setLastname(existingUser.getLastname());
        userResponseDto.setGender(existingUser.getGender());
        userResponseDto.setEmail(existingUser.getEmail());
        return userResponseDto;
    }



    //GET ALL USERS
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public List<UserResponseDto> getAllUsers(){
        userResponseDtoList = new ArrayList<>();
        userList = userRepository.findAll();
        for(User user : userList){
            userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setUsername(user.getUsername());
            userResponseDto.setFirstname(user.getFirstname());
            userResponseDto.setLastname(user.getLastname());
            userResponseDto.setGender(user.getGender());
            userResponseDto.setEmail(user.getEmail());
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }




    public List<UserResponseDto> getAllUsersWithPagination(int pageNumber, int pageSize){
        userResponseDtoList = new ArrayList<>();
        System.out.println(userRepository.findAll().size());
        Page<User> paginatedUserList = userRepository.findAll(PageRequest.of(pageNumber, pageSize));

        userList = paginatedUserList.stream().toList();

        for(User user : userList){
            userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setUsername(user.getUsername());
            userResponseDto.setFirstname(user.getFirstname());
            userResponseDto.setLastname(user.getLastname());
            userResponseDto.setGender(user.getGender());
            userResponseDto.setEmail(user.getEmail());
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }




    //DELETE USER BY ID
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public String deleteUserById(@PathVariable Long id) throws UserNotFoundException {
        user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("USER WITH THE ID %d DOES NOT EXISTS", id)));
        userRepository.delete(user);
        return String.format("USER WITH ID %d SUCCESSFULLY DELETED", id);
    }



}
