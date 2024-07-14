package com.webapp.codeathon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.codeathon.entity.ProfilePicture;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Integer> {
	Optional<ProfilePicture> findByUsername(String username);
	Optional<ProfilePicture> findByFileName(String fileName);
}
