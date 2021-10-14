/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ma.start;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Osikena
 */
public class DetailsService implements UserDetailsService{

	
	UserData data = new UserData();
	
	ApiConfig api = new ApiConfig();
	
	@Override
	public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
		api.setlogindetails(data);
		return new User(data.getUsername(), data.getPassword(), new ArrayList<>());
	}
	
}
