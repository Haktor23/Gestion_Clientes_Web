package com.project.gestclients.gest.clients.Auth;

import com.project.gestclients.gest.clients.Entidad.HUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.project.gestclients.gest.clients.Jwt.JwtService;
import com.project.gestclients.gest.clients.User.Role;
import com.project.gestclients.gest.clients.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final JwtService jwtService;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;

        public AuthResponse login(LoginRequest request) {
                authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
                                                request.getPassword()));
                UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
                String token = jwtService.getToken(user);
                return AuthResponse.builder()
                                .token(token)
                                .build();

        }

        public AuthResponse register(RegisterRequest request) {
                // Verificar si el usuario ya existe

                if (userRepository.findByUsername(request.getUsername()).isPresent()) {
                        throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
                }


                // Asignar rol por defecto si el campo role está vacío
                String roleStr = (request.getRole() == null || request.getRole().trim().isEmpty()) ? "USER"
                                : request.getRole().toUpperCase();

                // Convertir el rol del String a un Enum
                Role role;
                try {
                        role = Role.valueOf(roleStr);
                } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Rol no válido: " + request.getRole());
                }

                // Crear y guardar el usuario
                HUser hUser = HUser.builder()
                                .username(request.getUsername())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(role)
                                .build();

                userRepository.save(hUser);

                // Generar y retornar la respuesta de autenticación
                return AuthResponse.builder()
                                .token(jwtService.getToken(hUser))
                                .build();
        }

        public void deleteUserById(Long userId) {
                // Obtener el usuario autenticado
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String currentUsername = authentication.getName();

                // Verificar si el usuario autenticado es un admin
                HUser currentHUser = userRepository.findByUsername(currentUsername)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

                if (!currentHUser.getRole().equals(Role.ADMIN)) {
                        throw new SecurityException("No tienes permisos para eliminar usuarios.");
                }

                // Verificar si el usuario a eliminar existe
                HUser HUserToDelete = userRepository.findById(userId)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario a eliminar no encontrado"));

                // Verificar que no se está intentando eliminar a sí mismo
                if (currentHUser.getId().equals(HUserToDelete.getId())) {
                        throw new SecurityException("No puedes eliminar tu propio usuario.");
                }

                // Eliminar al usuario
                userRepository.delete(HUserToDelete);
        }
}
