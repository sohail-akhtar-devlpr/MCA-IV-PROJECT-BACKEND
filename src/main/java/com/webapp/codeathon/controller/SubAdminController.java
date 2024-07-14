package com.webapp.codeathon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webapp.codeathon.config.AuthenticationTokenJwtUtil;
import com.webapp.codeathon.config.JwtTokenValidationFilter;
import com.webapp.codeathon.config.JwtUtil;
import com.webapp.codeathon.entity.Contestant;
import com.webapp.codeathon.entity.ProfilePicture;
import com.webapp.codeathon.entity.SubAdmin;
import com.webapp.codeathon.exception.UserException;
import com.webapp.codeathon.repository.SubAdminRepository;
import com.webapp.codeathon.request.ContestantSignupRequest;
import com.webapp.codeathon.request.LoginRequest;
import com.webapp.codeathon.request.SubAdminSignupRequest;
import com.webapp.codeathon.response.JwtResponse;
import com.webapp.codeathon.role.UserRole;
import com.webapp.codeathon.service.CustomUserDetailService;
import com.webapp.codeathon.service.ProfilePictureService;
import com.webapp.codeathon.service.SubAdminService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/subadmin")
public class SubAdminController {

	@Autowired
	private SubAdminService subAdminService;

	@Autowired
	SubAdminRepository subAdminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private AuthenticationTokenJwtUtil authenticationTokenJwtUtil;

	@Autowired
	private ProfilePictureService profilePictureService;

	// SubAdmin Signup Handler or say SubAdmin Creation
	@PostMapping("/signup")
	public ResponseEntity<?> subAdminSignupHandler(@Valid @RequestBody SubAdminSignupRequest req, BindingResult result)
			throws UserException {

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}

		// Check if password and re-entered password matches
		if (!(req.getPassword().equals(req.getRePassword()))) {
			Map<String, String> errors = new HashMap<>();
			errors.put("rePassword", "Passwords do not match");
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}

		String firstName = req.getFirstName();
		String middleName = req.getMiddleName();
		String lastName = req.getLastName();
		String gender = req.getGender();
		String mobileNumber = req.getMobileNumber();
		String designation = req.getDesignation();
		String email = req.getEmail();
		String password = req.getPassword();
//		String rePassword = req.getRePassword();

		System.out.println("PASSWORD: " + password);

		SubAdmin subAdmin = subAdminRepository.findByEmail(email);

		// checks for if already exists
		if (subAdmin != null) {
			throw new UserException("Email Already Exists");
		}

		// Encodes the password
		String encodedPassword = passwordEncoder.encode(password);

		// CREATE THE SUBADMIN
		SubAdmin createdSubAdmin = new SubAdmin();

		createdSubAdmin.setFirstName(firstName);
		createdSubAdmin.setMiddleName(middleName);
		createdSubAdmin.setLastName(lastName);
		createdSubAdmin.setGender(gender);
		createdSubAdmin.setMobileNumber(mobileNumber);
		createdSubAdmin.setDesignation(designation);
		createdSubAdmin.setEmail(email);
		createdSubAdmin.setPassword(encodedPassword);
		createdSubAdmin.setRole(UserRole.SUBADMIN);

		SubAdmin saveSubAdmin = subAdminRepository.save(createdSubAdmin);

		Authentication authentication = new UsernamePasswordAuthenticationToken(saveSubAdmin.getEmail(),
				saveSubAdmin.getPassword());

//		System.out.println("AUTHENTICATION DETAILS--->"+authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Generate jwt token
		String jwt = jwtUtil.generateJwtToken(authentication);

		// Generate Jwt Response
		JwtResponse jwtResponse = new JwtResponse();

		// Set Jwt Response
		jwtResponse.setJwt(jwt);
		jwtResponse.setAuthenticated(true);
		jwtResponse.setError(false);
		jwtResponse.setErrorDetails(null);
		jwtResponse.setType(UserRole.SUBADMIN);
		jwtResponse.setMessage("Registered Successfully-");

		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.CREATED);
	}

	// Sub Admin Signin Handler
	@PostMapping("/signin")
	public ResponseEntity<?> subAdminSignin(@Valid @RequestBody LoginRequest req,
			BindingResult result,
			 HttpServletResponse response) {
		System.out.println("API GETS CALLED");
		
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
		}

		String username = req.getEmail();
		System.out.println("USERNAME: " + username);
		String password = req.getPassword();
		System.out.println("PASSWORD: " + password);

		Authentication authentication = authenticate(username, password);

		System.out.println("AUTHENTICATION: " + authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// generateJwtToken(authentication) in jwtUtil.java file in config package.
		String jwtToken = jwtUtil.generateJwtToken(authentication);

		System.out.println("SUBADMIN JWT TOKEN:: " + jwtToken);

		// Generate Jwt Response
		JwtResponse jwtResponse = new JwtResponse();

		// Create a cookie with the JWT
		Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
		jwtCookie.setHttpOnly(true); // Prevent client-side scripts from accessing the cookie
		jwtCookie.setSecure(true); // Set to true in production
		jwtCookie.setPath("/");
		jwtCookie.setMaxAge(24 * 60 * 60); // 1 day expiration

		// Add the cookie to the response
		response.addCookie(jwtCookie);

		System.out.println("COOKIE:: " + jwtCookie);

		// Set Jwt Response
		jwtResponse.setJwt(jwtToken);
		jwtResponse.setAuthenticated(true);
		jwtResponse.setError(false);
		jwtResponse.setErrorDetails(null);
//		jwtResponse.setType(UserRole.SUBADMIN);
		jwtResponse.setMessage("Account Logged in Successfully");
//	    jwtResponse.setUsername(username); // Set the username

		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);
	}

	// Logout endpoint
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidate the JWT cookie
		Cookie cookie = new Cookie("jwtToken", null);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0); // Set cookie expiration to 0 to delete it
		response.addCookie(cookie);

		// Optionally clear security context
		SecurityContextHolder.clearContext();

		return ResponseEntity.ok("Logged out successfully");
	}

	// Authentication
	private Authentication authenticate(String username, String password) {

		System.out.println("AUTHENTICATE ME USERNAME: " + username);

		UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

		System.out.println("USER DETAILS:: " + userDetails);

		if (userDetails != null) {
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("invalid password");
			}
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	// get subadmin information end point
	@GetMapping("/subadmininfo")
	public ResponseEntity<?> getSubadminInfo(@RequestHeader("Authorization") String jwt) {
		System.out.println("JWT:::" + jwt);
		SubAdmin subadmin = subAdminService.findSubadminInfo(jwt);
		if (subadmin == null) {
			return new ResponseEntity<String>("Subadmin not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SubAdmin>(subadmin, HttpStatus.OK);
	}

	// Save the profile Picture
	@PostMapping("/save-profile-picture")
	public ResponseEntity<?> saveProfilePicture(@RequestParam("username") String username,
			@RequestParam("profilePicture") MultipartFile file) {
		try {
			ResponseEntity<ProfilePicture> response = profilePictureService.saveProfilePic(username, file);
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload profile picture: " + e.getMessage());
		}
	}

	// Get the Profile Picture
	@GetMapping("/get-profile-picture")
	public ResponseEntity<?> getProfilePicture(@RequestParam String username) {
		byte[] getProfilePic = profilePictureService.getProfilePic(username);
		if (getProfilePic == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
		}
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG) // Adjust this based on your image
																						// type
				.body(getProfilePic);
	}

	// Authenticate Interceptor for every api request
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateInterceptor(@RequestBody LoginRequest req) {

		String username = req.getEmail();
		System.out.println("username in authenticate::" + username);
		String password = req.getPassword();
		System.out.println("password in authenticate::" + password);

		Authentication authentication = authenticate(username, password);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken = jwtUtil.generateJwtToken(authentication);

		return ResponseEntity.ok(jwtToken);
	}

}