package com.steppedua.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private String userId;

    @NotEmpty
    @Column(name = "user_name")
    @Size(min = 4, max = 15)
    @JsonProperty("user_name")
    private String userName;

    @NotEmpty
    @Column(name = "login")
    @Size(min = 4, max = 15)
    @JsonProperty("login")
    private String login;

    @NotEmpty
    @Column(name = "password")
    @Size(min = 4, max = 15)
    @JsonProperty("password")
    private String password;

    @NotEmpty
    @Column(name = "creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonProperty("creation_date")
    private Date creationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Document> documents;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(creationDate, user.creationDate) && Objects.equals(documents, user.documents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, login, password, creationDate, documents);
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                ", documents=" + documents +
                '}';
    }

    public User(String userId, String userName, String login, String password, Date creationDate, Set<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.creationDate = creationDate;
        this.roles = roles;
    }
}
