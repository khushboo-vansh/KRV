package com.krv.my.app.authenticate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krv.my.app.authenticate.entity.UserEntity;

/**
 * @author Khushboo_Vansh
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	/**
	 * @param userName
	 * @return
	 */
	UserEntity findByUserName(String userName);

}
