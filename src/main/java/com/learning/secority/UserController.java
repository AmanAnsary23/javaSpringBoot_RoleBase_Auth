package com.learning.secority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

//    @GetMapping("/passEncoder")
//    public void saveUserWithEncodedPassword(String username , String password , String role) {
//
//        UserEntity user = new UserEntity();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setIsActive(true);
//        user.getRole(role);
//
//        userRepository.save(user);
//    }

    @PostMapping("/encodePassword")
   public void saveUserWithEncodedPassword(@RequestBody UserRequestDTO dto) {
       UserEntity user = new UserEntity();
       user.setUsername(dto.getUsername());
       user.setPassword(passwordEncoder.encode(dto.getPassword()));
       user.setIsActive(true);
       user.setRole(dto.getRole());

       userRepository.save(user);
   }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername() ,
                        authRequest.getPassword()));

        if(authenticate.isAuthenticated()) {
            String role = authenticate
                    .getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority()
                    .replace("ROLE_" , "");

            return jwtService.generateToken(authRequest.getUsername() , role
            );
        }

        return null;
    }
}
