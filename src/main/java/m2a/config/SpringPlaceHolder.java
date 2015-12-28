package m2a.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

public class SpringPlaceHolder {

    private static final Logger LOG = LoggerFactory.getLogger(SpringPlaceHolder.class);

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(Environment environment) {
        final PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        final String[] activeProfiles = environment.getActiveProfiles();
        final int activeProfilesLength = activeProfiles.length;

        if (activeProfilesLength > 0) {
            String activeProfile = activeProfiles[0];
            if (activeProfilesLength > 1) {
                LOG.warn("Found multiple profiles {}. Only using first profile to load properties. " +
                        "Loading application.properties and {}.properties", activeProfiles, activeProfile);
            }
            propertyPlaceholderConfigurer.setLocations(
                    new ClassPathResource("application.properties"),
                    new ClassPathResource(String.format("environments/%s.properties", activeProfile)));
        }
        else {
            propertyPlaceholderConfigurer.setLocations(new ClassPathResource("application.properties"));
        }
        return propertyPlaceholderConfigurer;
    }
}