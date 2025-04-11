package com.blws.side.team.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamUserId implements Serializable {

    private Integer team;
    private Integer user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamUserId)) return false;
        TeamUserId that = (TeamUserId) o;
        return Objects.equals(team, that.team) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, user);
    }
}

