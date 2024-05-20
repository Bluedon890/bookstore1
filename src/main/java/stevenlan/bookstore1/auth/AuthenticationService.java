package stevenlan.bookstore1.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stevenlan.bookstore1.config.JwtService;
import stevenlan.bookstore1.user.Role;
import stevenlan.bookstore1.user.User;
import stevenlan.bookstore1.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) {
        
        var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .account(request.getAccount())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getAccount(), request.getPassword())
    );

    var user = repository.findByAccount(request.getAccount()).orElseThrow();

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
}

    
}
