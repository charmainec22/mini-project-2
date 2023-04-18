package miniproject2.server.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import miniproject2.server.dao.UserDao;

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService{

    
    @Autowired
    UserDao userDao;

    private miniproject2.server.model.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDetail = userDao.findByEmailId(username);
        if (!Objects.isNull(userDetail))
            return new User(userDetail.getEmail(), new BCryptPasswordEncoder().encode(userDetail.getPassword()) , new ArrayList<>());
        else
            throw new UsernameNotFoundException("User not found");
    }


    public miniproject2.server.model.User getuserDetail() {
        // miniproject2.server.model.User user = userDetail;
        // user.setPassword(null);
        return userDetail;
    }
   
}
