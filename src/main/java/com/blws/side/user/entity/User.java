package com.blws.side.user.entity;

import com.blws.side.auth.enumerate.AccountStatus;
import com.blws.side.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Table(name = "TB_USER")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "status")
    private String status;      // 계정 상태 (S: 휴먼, B: 정지)

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "withdrawalAt")
    private String withdrawalAt;

    @Column(name = "withdrawal_memo")
    private String withdrawMemo;

    @Column(name = "refreshToken")
    private String refreshToken;

    @Column(name = "agree_marketing_at")
    private String agreeMarketingAt;

    @Column(name = "agree_privacy_at")
    private String agreePrivacyAt;

    public User updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public User update(User user) {
        this.refreshToken = user.getRefreshToken();
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return AccountStatus.fromAlias(this.status) != AccountStatus.BLOCKED
                || AccountStatus.fromAlias(this.status) != AccountStatus.SLEEPER;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
