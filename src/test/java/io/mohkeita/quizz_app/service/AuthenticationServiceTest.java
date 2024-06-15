package io.mohkeita.quizz_app.service;

import io.mohkeita.quizz_app.dto.AuthenticationRequest;
import io.mohkeita.quizz_app.dto.AuthenticationResponse;
import io.mohkeita.quizz_app.dto.RegistrationRequest;
import io.mohkeita.quizz_app.model.Role;
import io.mohkeita.quizz_app.model.Token;
import io.mohkeita.quizz_app.model.User;
import io.mohkeita.quizz_app.repository.RoleRepository;
import io.mohkeita.quizz_app.repository.TokenRepository;
import io.mohkeita.quizz_app.repository.UserRepository;
import io.mohkeita.quizz_app.security.JwtService;
import io.mohkeita.quizz_app.service.mailjet.MailJetServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private MailJetServiceImp mailJetService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService userService;

    private RegistrationRequest request;

    private AuthenticationRequest authRequest;
    private Authentication auth;

    @BeforeEach
    void setUp() {
        request = RegistrationRequest
                .builder()
                .firstname("John")
                .lastname("Doe")
                .email("becaya9418@fna6.com")
                .password("password")
                .build();

        authRequest = AuthenticationRequest.
                builder()
                .email("john.doe@example.com")
                .password("password")
                .build();


        auth = mock(Authentication.class);

    }

    @Test
    void testRegister_UserSuccessfullyRegistered() {
        // Mock the role repository to return a role
        Role userRole = Role.builder().name("USER").build();

        when(roleRepository.findByName("USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        // Mock token repository save method
        when(tokenRepository.save(any(Token.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the register method
        userService.register(request);

        // Verify that the user repository's save method was called
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testRegister_RoleNotFound_ThrowsException() {
        // Mock the role repository to return an empty optional
        when(roleRepository.findByName("USER")).thenReturn(Optional.empty());

        // Assert that an IllegalStateException is thrown
        assertThrows(IllegalStateException.class, () -> {
            userService.register(request);
        });

        // Verify that userRepository.save was never called
        verify(userRepository, never()).save(any(User.class));

        // Verify that tokenRepository.save was never called
        verify(tokenRepository, never()).save(any(Token.class));
    }

    @Test
    void testRegister_NegativeInvalidEmail_ThrowsException() {
        // Set an invalid email
        request.setEmail("invalid-email");

        // Assert that an IllegalArgumentException is thrown
        assertThrows(IllegalStateException.class, () -> {
            userService.register(request);
        });

        // Verify that userRepository.save was never called
        verify(userRepository, never()).save(any(User.class));

        // Verify that tokenRepository.save was never called
        verify(tokenRepository, never()).save(any(Token.class));
    }

    @Test
    void testRegister_NegativeWeakPassword_ThrowsException() {
        // Set a weak password
        request.setPassword("123");

        // Assert that an IllegalArgumentException is thrown
        assertThrows(IllegalStateException.class, () -> {
            userService.register(request);
        });

        // Verify that userRepository.save was never called
        verify(userRepository, never()).save(any(User.class));

        // Verify that tokenRepository.save was never called
        verify(tokenRepository, never()).save(any(Token.class));
    }

    @Test
    void testRegister_NegativeMissingFields_ThrowsException() {
        // Set missing fields
        request.setFirstname(null);

        // Assert that an IllegalArgumentException is thrown
        assertThrows(IllegalStateException.class, () -> {
            userService.register(request);
        });

        // Verify that userRepository.save was never called
        verify(userRepository, never()).save(any(User.class));

        // Verify that tokenRepository.save was never called
        verify(tokenRepository, never()).save(any(Token.class));
    }


    @Test
    void testAuthenticate_UserSuccessfullyAuthenticated() {
        User user = mock(User.class);
        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(user);
        when(user.fullName()).thenReturn("John Doe");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);
        when(jwtService.generateToken(anyMap(), any(User.class))).thenReturn("jwt-token");

        AuthenticationResponse response = userService.authenticate(authRequest);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).generateToken(anyMap(), any(User.class));
    }

    @Test
    void testAuthenticate_InvalidCredentials_ThrowsException() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Invalid credentials"));

        assertThrows(RuntimeException.class, () -> {
            userService.authenticate(authRequest);
        });

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, never()).generateToken(anyMap(), any(User.class));
    }
}