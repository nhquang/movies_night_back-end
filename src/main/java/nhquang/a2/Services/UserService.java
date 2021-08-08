package nhquang.a2.Services;

import nhquang.a2.Models.User;
import nhquang.a2.Models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService

{
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){8,18}$";
    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){8,18}$";
    @Autowired
    private UserRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User addUser(User user) throws Exception
    {
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("email").is(user.getEmail()), Criteria.where("username").is(user.getEmail()));
        Query query = new Query();
        query.addCriteria(criteria);
        List<User> users = mongoTemplate.find(query, User.class);
        if(users.size() > 0)
            throw new Exception("Email already exists!");
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(user.getUsername());
        if(!matcher.matches())
            throw new Exception("Username must contain at least 8 characters and at least one digit!");
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(user.getPassword());
        if(!matcher.matches())
            throw new Exception("Password must contain at least 8 characters, at least one digit, at least one special character!");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if(user.getFirstName() == null || user.getFirstName().trim() == "")
            throw new Exception("First Name is empty!");
        if(user.getLastName() == null || user.getLastName().trim() == "")
            throw new Exception("Last Name is empty!");

        user.setRole("user");
        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());

        return repository.insert(user);
    }

    public List<User> getUsers()
    {
        return repository.findAll();
    }

    public Optional<User> getAnUser(String id) throws Exception
    {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new Exception("Not Found!");

        return user;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User foundUser = repository.findByUsername(username);
        String userN = foundUser.getUsername();
        String password = foundUser.getPassword();

        return new org.springframework.security.core.userdetails.User(userN, password, new ArrayList<>());


    }
}
