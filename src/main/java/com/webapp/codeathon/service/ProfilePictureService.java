package com.webapp.codeathon.service;

//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.codeathon.entity.ProfilePicture;
import com.webapp.codeathon.repository.ProfilePictureRepository;
import com.webapp.codeathon.util.ImageUtils;

import io.jsonwebtoken.io.IOException;

@Service
public class ProfilePictureService {

	@Autowired
	private ProfilePictureRepository profilePictureRepository;

	// Save or Update the image
	public ResponseEntity<ProfilePicture> saveProfilePic(String username, MultipartFile file)
			throws IOException, java.io.IOException {
		Optional<ProfilePicture> existingProfilePicture = profilePictureRepository.findByUsername(username);

		try {
			ProfilePicture profilePicture = existingProfilePicture.orElse(new ProfilePicture());
			profilePicture.setUsername(username);
			profilePicture.setFileName(file.getOriginalFilename());
			profilePicture.setType(file.getContentType());
			profilePicture.setImageData(ImageUtils.compressImage(file.getBytes()));
			profilePictureRepository.save(profilePicture);
			
	        return new ResponseEntity<>(profilePicture, HttpStatus.CREATED);

		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("File upload failed for " + file.getOriginalFilename(), e);
		}
	}

	// 2. Retrieve or Get the image
	public byte[] getProfilePic(String username) {
		Optional<ProfilePicture> dbProfilePicture = profilePictureRepository.findByUsername(username);
		return dbProfilePicture.map(profilePicture -> ImageUtils.deCompressImage(profilePicture.getImageData()))
				.orElse(null);
	}
}
