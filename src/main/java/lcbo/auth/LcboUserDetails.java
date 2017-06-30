package lcbo.auth;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Provides core user information.
 */
public interface LcboUserDetails extends UserDetails {

    void setPassword(String password);
}

