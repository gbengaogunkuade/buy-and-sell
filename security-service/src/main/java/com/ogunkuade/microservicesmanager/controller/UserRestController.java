package com.ogunkuade.microservicesmanager.controller;



import com.ogunkuade.microservicesmanager.dto.UserRequestDto;
import com.ogunkuade.microservicesmanager.dto.UserResponseDto;
import com.ogunkuade.microservicesmanager.dto.UserUpdateRequestDto;
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
    public Boolean checkForId(@PathVariable Long id){
        return userRestService.checkForId(id);
    }


    //CHECK FOR USER BY USERNAME
    @GetMapping("/{username}/check")
    public Boolean checkingForUsername(@PathVariable String username){
        return userRestService.checkForUsername(username);
    };



    //CREATE USER
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto creatingUser(@RequestBody UserRequestDto userRequestDto) throws UserAlreadyExistsException, UnmatchedPasswordException {
        return userRestService.createUser(userRequestDto);
    }



    //UPDATE USER BY ID
    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto updatingUserById(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto, @PathVariable Long id) throws UserNotFoundException{
        return userRestService.updateUserById(userUpdateRequestDto, id);
    }



    //GET USER BY ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto gettingUserById(@PathVariable Long id) throws UserNotFoundException {
        return userRestService.getUserById(id);
    }


    //GET ALL USERS
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> gettingAllUsers(){
        return userRestService.getAllUsers();
    }


    //GET ALL USERS WITH PAGINATION
    @GetMapping("/all/page")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> gettingAllUsersWithPagination(@RequestParam int pageNumber, @RequestParam int pageSize){
        return userRestService.getAllUsersWithPagination(pageNumber, pageSize);
    }




    //DELETE USER BY ID
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deletingUserById(@PathVariable Long id) throws UserNotFoundException {
        return userRestService.deleteUserById(id);
    }



}
