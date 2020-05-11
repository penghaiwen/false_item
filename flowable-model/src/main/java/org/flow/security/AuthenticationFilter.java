package org.flow.security;

import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.ui.common.security.DefaultPrivileges;
import org.flowable.ui.common.security.FlowableAppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;


public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = new UserEntityImpl();
        user.setId("admin");
        List<GrantedAuthority> noAuthorities = AuthorityUtils.NO_AUTHORITIES;
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(
                DefaultPrivileges.ACCESS_ADMIN,
                DefaultPrivileges.ACCESS_IDM,
                DefaultPrivileges.ACCESS_MODELER,
                DefaultPrivileges.ACCESS_REST_API,
                DefaultPrivileges.ACCESS_TASK
        );
        FlowableAppUser flowableAppUser = new FlowableAppUser(user, "admin", noAuthorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(flowableAppUser, null, noAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
