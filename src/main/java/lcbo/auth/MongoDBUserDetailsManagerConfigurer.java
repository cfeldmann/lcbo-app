package lcbo.auth;

import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Configures an
 * {@link org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder}
 * to have authentication via MongoDB.
 *
 * @param <B> the type of the {@link ProviderManagerBuilder} that is being configured
 *
 */
class MongoDBUserDetailsManagerConfigurer<B extends ProviderManagerBuilder<B>>
        extends UserDetailsManagerConfigurer<B, MongoDBUserDetailsManagerConfigurer<B>> {

    MongoDBUserDetailsManagerConfigurer(UserDetailsManager mgr) {
        super(mgr);
    }
}
