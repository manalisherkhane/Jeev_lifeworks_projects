// // package com.example.jwtdemo.service;

// // import org.springframework.security.authentication.AuthenticationManager;
// // import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.stereotype.Service;

// // import org.slf4j.Logger;
// // import org.slf4j.LoggerFactory;

// // import com.example.jwtdemo.dto.AuthRequest;
// // import com.example.jwtdemo.dto.AuthResponse;
// // import com.example.jwtdemo.dto.RegisterRequest;
// // import com.example.jwtdemo.model.Role;
// // import com.example.jwtdemo.model.User;
// // import com.example.jwtdemo.repository.UserRepository;

// // import lombok.RequiredArgsConstructor;



// // @Service
// // @RequiredArgsConstructor
// // public class AuthService {

// //     // Add at the top of the class (after package/imports):
// // private static final Logger log =
// //         LoggerFactory.getLogger(AuthService.class);

// // private static final Logger AUDIT_LOG =
// //         LoggerFactory.getLogger("AUDIT_LOGGER");

// //     private final UserRepository userRepository;
// //     private final PasswordEncoder passwordEncoder;
// //     private final JwtService jwtService;
// //     private final AuthenticationManager authenticationManager;

// //     public AuthResponse register(RegisterRequest request) {
// //         var user = User.builder()
// //                 .name(request.getName())
// //                 .email(request.getEmail())
// //                 .password(passwordEncoder.encode(request.getPassword()))
// //                 .role(Role.USER)
// //                 .build();
// //         userRepository.save(user);
// //         return new AuthResponse(jwtService.generateToken(user));
// //     }

// //     public AuthResponse login(AuthRequest request) {
// //         authenticationManager.authenticate(
// //                 new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
// //         );
// //         var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
// //         return new AuthResponse(jwtService.generateToken(user));
// //     }
// // }

// package com.example.jwtdemo.service;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import com.example.jwtdemo.dto.AuthRequest;
// import com.example.jwtdemo.dto.AuthResponse;
// import com.example.jwtdemo.dto.RegisterRequest;
// import com.example.jwtdemo.model.Role;
// import com.example.jwtdemo.model.User;
// import com.example.jwtdemo.repository.UserRepository;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class AuthService {

//     private static final Logger log =
//             LoggerFactory.getLogger(AuthService.class);

//     private static final Logger AUDIT_LOG =
//             LoggerFactory.getLogger("AUDIT_LOGGER");

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtService jwtService;
//     private final AuthenticationManager authenticationManager;

//     public AuthResponse register(RegisterRequest request) {


//         log.info("[REGISTER] Attempting registration for email: {}",
//                 request.getEmail());

//         var user = User.builder()
//                 .name(request.getName())
//                 .email(request.getEmail())
//                 .password(passwordEncoder.encode(request.getPassword()))
//                 .role(Role.USER)
//                 .build();

//         userRepository.save(user);

//         log.info("[REGISTER] User registered successfully: {} with role {}",
//                 user.getEmail(), user.getRole());

//                 if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//     log.warn("[REGISTER] Email already used: {}", request.getEmail());
//     throw new RuntimeException(
//             "Email already registered: " + request.getEmail());
// }

//         AUDIT_LOG.info("[AUDIT] REGISTER_SUCCESS | user={} | role={}",
//                 user.getEmail(), user.getRole());

//         return new AuthResponse(jwtService.generateToken(user));
//     }

//     public AuthResponse login(AuthRequest request) {

//         log.info("[LOGIN] Login attempt for email: {}",
//                 request.getEmail());

//         authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(
//                         request.getEmail(),
//                         request.getPassword())
//         );

//         var user = userRepository.findByEmail(request.getEmail())
//                 .orElseThrow();

//         String token = jwtService.generateToken(user);

//         log.info("[LOGIN] Login successful for user: {}",
//                 user.getEmail());

//         AUDIT_LOG.info("[AUDIT] LOGIN_SUCCESS | user={} | role={}",
//                 user.getEmail(), user.getRole());

//         return new AuthResponse(token);
//     }
// }



package com.example.jwtdemo.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jwtdemo.dto.AuthRequest;
import com.example.jwtdemo.dto.AuthResponse;
import com.example.jwtdemo.dto.RegisterRequest;
import com.example.jwtdemo.model.Role;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger log =
            LoggerFactory.getLogger(AuthService.class);

    private static final Logger AUDIT_LOG =
            LoggerFactory.getLogger("AUDIT_LOGGER");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        log.info("[REGISTER] Attempting registration for email: {}",
                request.getEmail());

        // Check if email already exists BEFORE saving
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            log.warn("[REGISTER] Email already registered: {}",
                    request.getEmail());

            throw new RuntimeException(
                    "Email already registered: " + request.getEmail());
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        log.info("[REGISTER] User registered successfully: {} with role {}",
                user.getEmail(), user.getRole());

        AUDIT_LOG.info("[AUDIT] REGISTER_SUCCESS | user={} | role={}",
                user.getEmail(), user.getRole());

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {

        log.info("[LOGIN] Login attempt for email: {}",
                request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        log.info("[LOGIN] Login successful for user: {}",
                user.getEmail());

        AUDIT_LOG.info("[AUDIT] LOGIN_SUCCESS | user={} | role={}",
                user.getEmail(), user.getRole());

        return new AuthResponse(token);
    }
}