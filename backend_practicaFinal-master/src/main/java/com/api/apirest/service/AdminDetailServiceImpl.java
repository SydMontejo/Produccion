package com.api.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.api.apirest.model.Administrador;
import com.api.apirest.repository.AdministradorRepository;

@Service
public class AdminDetailServiceImpl implements UserDetailsService{
	
	@Autowired
    private AdministradorRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Administrador administrador = this.adminRepository.findByUsername(username);
        if(administrador == null){
            throw new UsernameNotFoundException("Usuario Administrador no encontrado");
        }
        return administrador;
    }

}
