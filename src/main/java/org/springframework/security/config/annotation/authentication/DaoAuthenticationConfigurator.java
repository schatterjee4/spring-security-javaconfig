/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.config.annotation.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfiguratorAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DaoAuthenticationConfigurator<T extends UserDetailsService> extends SecurityConfiguratorAdapter<AuthenticationManager,AuthenticationManagerBuilder> {
    private DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    private final T userDetailsService;

    public DaoAuthenticationConfigurator(T userDetailsService) {
        this.userDetailsService = userDetailsService;
        provider.setUserDetailsService(userDetailsService);
    }

    public DaoAuthenticationConfigurator passwordEncoder(PasswordEncoder passwordEncoder) {
        provider.setPasswordEncoder(passwordEncoder);
        return this;
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.add(provider);
    }

    protected T getUserDetailsService() throws Exception {
        return userDetailsService;
    }
}
