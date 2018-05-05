package com.udemy.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udemy.entity.UserRole;
import com.udemy.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {
	
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository UserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.udemy.entity.User userLogin = UserRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(userLogin.getUserRole());
		return this.buildUser(userLogin, authorities);
	}
	/**
	 * creo una entidad usuario para spring <br>
	 * accountNonExpired :  No esta expirado<br>
	 * credentialsNonExpired: Los credenciales no estan expirado<br>
	 * accountNonLocked: La cuenta no esta bloqueado<br>
	 * @param user
	 * @param authorities
	 * @return
	 */
	private User buildUser(com.udemy.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true,
				true, true, authorities);
	}
	
	/**
	 * transforma los objecto nuestro de permiso en GrantedAuthority que son los que
	 * interpreta spring para saber los roles de los usuarios logeado
	 * @param userRole
	 * @return
	 */
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
		Set<GrantedAuthority> auths = new HashSet<>();
		
		userRoles.stream().forEach(
				(userRole)-> auths.add(new SimpleGrantedAuthority(userRole.getRole())));
		
		return new ArrayList<GrantedAuthority>(auths);
	}

}
