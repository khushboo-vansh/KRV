package com.krv.my.app.authenticate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Khushboo_Vansh
 *
 */
@Getter
@Setter
@MappedSuperclass
public class TransactionalDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "object_id")
	protected Long objectId;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "created_at", nullable = false, insertable = true, updatable = false)
	protected LocalDateTime createdOn;

	@Column(name = "created_by", length = 10, nullable = false, updatable = false)
	protected String createdBy;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "last_modified_at", nullable = true)
	protected LocalDateTime lastModifiedOn;

	@Column(name = "last_modified_by", length = 10, nullable = true)
	protected String lastModifiedBy;

	@PrePersist
	protected void onCreate() {
		createdOn = LocalDateTime.now();
		lastModifiedOn = LocalDateTime.now();

		if (StringUtils.isEmpty(this.createdBy)) {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
				String currentUserName = authentication.getName();
				createdBy = currentUserName;
				lastModifiedBy = currentUserName;
			} else {
				createdBy = "SYSTEM";
				lastModifiedBy = "SYSTEM";
			}
		}
	}

	@PreUpdate
	protected void onUpdate() {
		lastModifiedOn = LocalDateTime.now();

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			lastModifiedBy = authentication.getName();
		} else {
			lastModifiedBy = "SYSTEM";
		}
	}
}
