/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QRDiscount.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Chahir Chalouati
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class AppUser implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "")
    private String fullname;

    @Column(unique = true)
    @NotBlank(message = "")
    private String username;// username

    @Column(unique = true)
    @Email(message = "Invalid E-mail address")
    private String email; //email

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles = new ArrayList<>();

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isNew = true;

    @JsonIgnore
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isAccountNonLocked = true;
    @JsonIgnore
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isAccountNonExpired = true;
    @JsonIgnore
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isCredentialsNonExpired = true;
    @JsonIgnore
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isEnabled = true;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date modifiedAt;

}
