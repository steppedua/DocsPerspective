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
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @NotEmpty
    @Size(min = 3, max = 15)
    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private Set<Privileges> privileges;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) && Objects.equals(roleName, role.roleName) && Objects.equals(users, role.users) && Objects.equals(privileges, role.privileges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, users, privileges);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                ", privileges=" + privileges +
                '}';
    }
}
