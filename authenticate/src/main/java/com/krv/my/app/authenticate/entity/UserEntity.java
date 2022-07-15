package com.krv.my.app.authenticate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Khushboo_Vansh
 *
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users", schema = "myapp")
public class UserEntity extends TransactionalDataEntity {

	@Column(name = "user_name", length = 10, nullable = false)
	private String userName;

	@Column(name = "firstname", length = 15, nullable = false)
	private String firstName;

	@Column(name = "lastname", length = 30, nullable = false)
	private String lastName;

	@ManyToOne(targetEntity = UsersRoleEntity.class)
	@JoinColumn(name = "role_id", nullable = false)
	private UsersRoleEntity role;

	@Column(name = "email", length = 30, nullable = false)
	private String email;

	@Column(name = "phone_number", length = 16)
	private String phoneNumber;

	@Column(name = "status", length = 10, nullable = false)
	private String status;

}
