package lcbo.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Persistent implementation of {@code UserDetailsManager} backed by a MongoDB.
 */
public class MongoDBUserDetailsManager implements UserDetailsManager {

    private final Log logger = LogFactory.getLog(getClass());

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;

    MongoDBUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDetails user) {

        if (userExists(user.getUsername())) {
            throw new IllegalArgumentException();
        }

        userRepository.save(new LcboUser(user.getUsername(), user.getPassword()));
    }

    public void deleteUser(String username) {
        //todo implement this
    }

    public void updateUser(UserDetails user) {
       //todo implement this
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username.toLowerCase()) != null;
    }

    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext()
                .getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context "
                            + "for current user.");
        }

        String username = currentUser.getName();

        logger.debug("Changing password for user '" + username + "'");

        // If an authentication manager has been set, re-authenticate the user with the
        // supplied password.
        if (authenticationManager != null) {
            logger.debug("Reauthenticating user '" + username
                    + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username, oldPassword));
        }
        else {
            logger.debug("No authentication manager set. Password won't be re-checked.");
        }

        LcboUserDetails user = userRepository.findByUsername(username);

        if (user == null) {
            throw new IllegalStateException("Current user doesn't exist in database.");
        }

        user.setPassword(newPassword);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = userRepository.findByUsername(username.toLowerCase());

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new LcboUser(user.getUsername(), user.getPassword());
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
