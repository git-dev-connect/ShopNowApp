package com.shopnow.ShopNowApp.controller;
import com.shopnow.ShopNowApp.Security.UserService;
import com.shopnow.ShopNowApp.Dto.AuthResponse;
import com.shopnow.ShopNowApp.Dto.LoginRequest;
import com.shopnow.ShopNowApp.Dto.SignUpRequest;
import com.shopnow.ShopNowApp.Entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("login api request hitted with username:"+ loginRequest.getUsername() +" & Password: "+loginRequest.getPassword());
        Optional<User> userOptional = userService.validUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            logger.info("User present");
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getName()));
        }
        logger.info("User Not Present - UNAUTHORIZED USER");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody SignUpRequest signUpRequest) {

        System.out.println("SignUpRequest with parameters");
        System.out.println("username: "+ signUpRequest.getUsername());
        System.out.println("PWD: "+ signUpRequest.getPassword());
        System.out.println("email: "+ signUpRequest.getEmail());
        System.out.println("name: "+ signUpRequest.getName());

        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
            throw new RuntimeException(String.format("Username %s is already been used", signUpRequest.getUsername()));
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new RuntimeException(String.format("Email %s is already been used", signUpRequest.getEmail()));
        }

        User user = userService.saveUser(createUser(signUpRequest));
        return new AuthResponse(user.getId(), user.getName());
    }

    private User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        return user;
    }


}
