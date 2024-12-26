package com.example.aos.sistema_aos_spring_boot;

import com.example.aos.sistema_aos_spring_boot.Modelo.Rol;
import com.example.aos.sistema_aos_spring_boot.Modelo.Usuario;
import com.example.aos.sistema_aos_spring_boot.Modelo.UsuarioRol;
import com.example.aos.sistema_aos_spring_boot.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SistemaAosSpringBootApplication implements CommandLineRunner {
	@Autowired
	private UsuarioService usuarioService;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SistemaAosSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Verificar si el usuario ya existe en la base de datos por su username o email
		String username = "teccuida_24";
		Usuario usuarioExistente = usuarioService.obtenerUsuario(username);

		if (usuarioExistente==null) {
			// Solo crear el usuario si no existe
			Usuario usuario = new Usuario();
			usuario.setNombre("Administrador");
			usuario.setApellido("Teccuida & Teckonecta");
			usuario.setUsername(username);
			usuario.setPassword(bCryptPasswordEncoder.encode("Admintec%24"));
			usuario.setEmail("teccuida@gmail.com");
			usuario.setTelefono("999999999");

			Rol rol = new Rol();
			rol.setRolId(1L);
			rol.setRolNombre("ADMIN");

			Set<UsuarioRol> usuariosRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setRol(rol);
			usuarioRol.setUsuario(usuario);
			usuariosRoles.add(usuarioRol);

			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuariosRoles);
			System.out.println("Usuario creado: " );
		} else {
			System.out.println("El usuario ya existe: ");
		}
	}

}
