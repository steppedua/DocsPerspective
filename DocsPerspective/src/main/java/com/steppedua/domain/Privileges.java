package com.steppedua.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "privileges")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Privileges implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilege_id")
    private Long privilegeId;

    @NotEmpty
    @Size(min = 3, max = 15)
    @Column(name = "privilege_name", unique = true, nullable = false)
    private String privilegeName;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Privileges that = (Privileges) o;
        return Objects.equals(privilegeId, that.privilegeId) && Objects.equals(privilegeName, that.privilegeName) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privilegeId, privilegeName, roles);
    }

    @Override
    public String toString() {
        return "Privileges{" +
                "privilegeId=" + privilegeId +
                ", privilegeName='" + privilegeName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
