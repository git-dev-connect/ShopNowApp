package com.shopnow.ShopNowApp.Security;
import com.shopnow.ShopNowApp.Entity.User;
import java.util.List;
import java.util.Optional;
public interface UserService {


    Optional<User> getUserByUsername(String username);

    boolean hasUserWithUsername(String username);

    boolean hasUserWithEmail(String email);


    User saveUser(User user);

    void deleteUser(User user);

    Optional<User> validUsernameAndPassword(String username, String password);
}
