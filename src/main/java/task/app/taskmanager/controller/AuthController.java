package task.app.taskmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import task.app.taskmanager.dto.AuthResponse;
import task.app.taskmanager.model.AppUser;
import task.app.taskmanager.dto.AuthRequest;
import task.app.taskmanager.repository.AppUserRepository;
import task.app.taskmanager.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthController (AppUserRepository userRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                            JwtService jwtService){

        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService=jwtService;
    }
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request){

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "User already exists";
        }

        AppUser user = new AppUser (request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);
        return "user created";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token =  jwtService.generateToken(request.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e){
            return ResponseEntity.status(401)
                    .body("Invalid username or password");

        }
    }

    @DeleteMapping("/cleanup")
    public String cleanup() {
        userRepository.deleteAll();
        return "All users deleted";
    }


}
