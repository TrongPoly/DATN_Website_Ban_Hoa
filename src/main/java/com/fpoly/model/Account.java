package com.fpoly.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Size(max = 100)
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 15)
    @NotNull
    @Column(name = "password", nullable = false, length = 15)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    
    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "locked", nullable = false)
    private Boolean locked;

    public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

	public Account() {
		super();
	}

	public Account(@Size(max = 100) String email, @Size(max = 15) @NotNull String password, Role role,
			@NotNull Boolean active) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.active = active;
	}

	

}