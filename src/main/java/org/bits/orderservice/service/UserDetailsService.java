package org.bits.orderservice.service;

import org.bits.orderservice.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    User getUserIdFromName(String username) throws UsernameNotFoundException;
}
