package fa.training.spring.security.userprinciple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.spring.entity.userentity.User;
import fa.training.spring.repository.userrepository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepository iUserRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = iUserRepository.findUserByUsername(username);
		return UserPrinciple.build(user);
	}

}
