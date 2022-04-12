package com.ine.sge.models;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.domain.AuditorAware;
/*
import org.springframework.security.core.context.SecurityContextHolder;
*/

import java.util.Optional;

/*
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional <String> getCurrentAuditor() {
		// Can use Spring Security to return currently logged in user
		return Optional.ofNullable(((SecurityProperties.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName());
	}
}*/
