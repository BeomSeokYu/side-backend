//package com.blws.side.config.jpa;
//
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Optional;
//
//public class AuditorAwareImpl implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        String updatedBy = ((UserDetails) SecurityContextHolder.getContext()).getUsername();
//        if (updatedBy == null) {
//            updatedBy = "unknown";
//        }
//        return Optional.of(updatedBy);
//    }
//}
