package ua.com.alevel.util;

import org.keycloak.KeycloakPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {
    private final static String systemUuid = UUID.randomUUID().toString();

    private SecurityUtils() {
    }

    /**
     * Get the uuid of the current user.
     *
     * @return the uuid of the current user
     */
    public static Optional<String> getCurrentUserUuid() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() == null) {
            return Optional.of(systemUuid);
        }

        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
                        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
                        return principal.getName();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return systemUuid;
                });
    }

    /**
     * If the current user has a specific authority (security role). <p> The name of this method comes from the isUserInRole() method in the Servlet
     * API
     *
     * @param authority the authority to check
     * @return true if the current user has the authority, false otherwise
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
                .orElse(false);
    }
}
