package com.ogunkuade.microservicesmanager.controller;



import com.ogunkuade.microservicesmanager.entity.User;
import com.ogunkuade.microservicesmanager.exception.UnmatchedPasswordException;
import com.ogunkuade.microservicesmanager.exception.UserAlreadyExistsException;
import com.ogunkuade.microservicesmanager.exception.UserNotFoundException;
import com.ogunkuade.microservicesmanager.service.UserRestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/users")
public class UserRestController {


    private final UserRestService userRestService;

    public UserRestController(UserRestService userRestService) {
        this.userRestService = userRestService;
    }


    //CHECK FOR USER BY ID
    @GetMapping("/{id}/checkId")
    public Boolean checkForId(Long id){
        return userRestService.checkForId(id);
    }


    //CHECK FOR USER BY USERNAME
    @GetMapping("/{username}/check")
    public Boolean checkingForUsername(@PathVariable String username){
        return userRestService.checkForUsername(username);
    };


    //GET USER BY USERNAME
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User gettingUser(@PathVariable String username) throws UserNotFoundException {
        return userRestService.getUser(username);
    }


    //GET ALL USERS
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> gettingAllUsers(){
        return userRestService.getAllUsers();
    }


    //CREATE USER
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public User creatingUser(@Valid @RequestBody User User) throws UserAlreadyExistsException, UnmatchedPasswordException {
        return userRestService.createUser(User);
    }



    //UPDATE USER BY USERNAME
    @PutMapping("/{username}/update")
    @ResponseStatus(HttpStatus.OK)
    public User updatingUser(@Valid @RequestBody User User, @PathVariable String username) throws UserNotFoundException{
        return userRestService.updateUser(User, username);
    }


    //DELETE USER BY USERNAME
    @DeleteMapping("/{username}/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deletingUser(@PathVariable String username) throws UserNotFoundException {
        return userRestService.deleteUser(username);
    }



}
