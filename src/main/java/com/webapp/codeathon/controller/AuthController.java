package com.webapp.codeathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.codeathon.config.JwtUtil;
import com.webapp.codeathon.entity.Contestant;
import com.webapp.codeathon.entity.RootAdmin;
import com.webapp.codeathon.entity.SubAdmin;
import com.webapp.codeathon.exception.UserException;
//import com.webapp.codeathon.repository.ContestRepository;
import com.webapp.codeathon.repository.ContestantRepository;
import com.webapp.codeathon.repository.RootAdminRepository;
import com.webapp.codeathon.repository.SubAdminRepository;
import com.webapp.codeathon.request.ContestantLoginRequest;
import com.webapp.codeathon.request.ContestantSignupRequest;
import com.webapp.codeathon.request.LoginRequest;
import com.webapp.codeathon.request.RootAdminSignupRequest;
import com.webapp.codeathon.request.SubAdminSignupRequest;
import com.webapp.codeathon.response.JwtResponse;
import com.webapp.codeathon.role.UserRole;
import com.webapp.codeathon.service.CustomUserDetailService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private RootAdminRepository rootAdminRepository;
	
	private SubAdminRepository subAdminRepository;
	
//	private ContestRepository contestRepository;
	
	@Autowired
	private ContestantRepository contestantRepository;
	
	private PasswordEncoder passwordEncoder;
	
	private JwtUtil jwtUtil;
	
	private CustomUserDetailService customUserDetailService;

	// Constructor
	public AuthController(RootAdminRepository rootAdminRepository, SubAdminRepository subAdminRepository,
			PasswordEncoder passwordEncoder, JwtUtil jwtUtil, CustomUserDetailService customUserDetailService) {
		super();
		this.rootAdminRepository = rootAdminRepository;
		this.subAdminRepository = subAdminRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
		this.customUserDetailService = customUserDetailService;
	}

	// Root Admin Signup Handler
	@PostMapping("/rootadmin/signup")
	public ResponseEntity<JwtResponse> rootAdminSignupHandler(@RequestBody RootAdminSignupRequest req)
			throws UserException {

		String firstName = req.getFirstName();
		String middleName = req.getMiddleName();
		String lastName = req.getLastName();
		String gender = req.getGender();
		String mobileNumber = req.getMobileNumber();
		String email = req.getEmail();
		String password = req.getPassword();

		RootAdmin rootAdmin = rootAdminRepository.findByEmail(email);

		if (rootAdmin != null) {
			throw new UserException("Email Already Exists-" + email);
		}

		String encodedPassword = passwordEncoder.encode(password);

		// CREATES THE ROOT ADMIN
		RootAdmin createdRootAdmin = new RootAdmin();
		createdRootAdmin.setFirstName(firstName);
		createdRootAdmin.setMiddleName(middleName);
		createdRootAdmin.setLastName(lastName);
		createdRootAdmin.setGender(gender);
		createdRootAdmin.setMobileNumber(mobileNumber);
		createdRootAdmin.setEmail(email);
		createdRootAdmin.setPassword(encodedPassword);
		createdRootAdmin.setRole(UserRole.SUBADMIN);

		RootAdmin saveRootAdmin = rootAdminRepository.save(createdRootAdmin);

		Authentication authentication = new UsernamePasswordAuthenticationToken(saveRootAdmin.getEmail(),
				saveRootAdmin.getPassword());

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
		jwtResponse.setType(UserRole.ROOTADMIN);
		jwtResponse.setMessage("Account Created Successfully-" + saveRootAdmin.getFirstName());

		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
	}

	// Root Admin Signin Handler
	@PostMapping("/rootadmin/signin")
	public ResponseEntity<JwtResponse> rootAdminSignin(@RequestBody LoginRequest req) {

		String username = req.getEmail();
		String password = req.getPassword();

		Authentication authentication = authenticate(username, password);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtil.generateJwtToken(authentication);

		// Generate Jwt Response
		JwtResponse jwtResponse = new JwtResponse();

		// Set Jwt Response
		jwtResponse.setJwt(jwt);
		jwtResponse.setAuthenticated(true);
		jwtResponse.setError(false);
		jwtResponse.setErrorDetails(null);
		jwtResponse.setType(UserRole.ROOTADMIN);
		jwtResponse.setMessage("Account Logged in Successfully");

		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);

	}

	
	// Sub Admin Signup Handler
//	@PostMapping("/subadmin/signup")
//	public ResponseEntity<JwtResponse> subAdminSignupHandler(@RequestBody SubAdminSignupRequest req)
//			throws UserException {
//
//		String firstName = req.getFirstName();
//		String middleName = req.getMiddleName();
//		String lastName = req.getLastName();
//		String gender = req.getGender();
//		String mobileNumber = req.getMobileNumber();
//		String email = req.getEmail();
//		String password = req.getPassword();
//		String designation = req.getDesignation();
//
//		SubAdmin subAdmin = subAdminRepository.findByEmail(email);
//
//		if (subAdmin != null) {
//			throw new UserException("Email Already Exists-" + email);
//		}
//
//		String encodedPassword = passwordEncoder.encode(password);
//
//		// CREATES THE ROOT ADMIN
//		SubAdmin createdSubAdmin = new SubAdmin();
//		createdSubAdmin.setFirstName(firstName);
//		createdSubAdmin.setMiddleName(middleName);
//		createdSubAdmin.setLastName(lastName);
//		createdSubAdmin.setGender(gender);
//		createdSubAdmin.setDesignation(designation);
//		createdSubAdmin.setMobileNumber(mobileNumber);
//		createdSubAdmin.setEmail(email);
//		createdSubAdmin.setPassword(encodedPassword);
//		createdSubAdmin.setRole(UserRole.SUBADMIN);
//
//		SubAdmin saveSubAdmin = subAdminRepository.save(createdSubAdmin);
//
//		Authentication authentication = new UsernamePasswordAuthenticationToken(saveSubAdmin.getEmail(),
//				saveSubAdmin.getPassword());
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		// Generate jwt token
//		String jwt = jwtUtil.generateJwtToken(authentication);
//
//		// Generate Jwt Response
//		JwtResponse jwtResponse = new JwtResponse();
//
//		// Set Jwt Response
//		jwtResponse.setJwt(jwt);
//		jwtResponse.setAuthenticated(true);
//		jwtResponse.setError(false);
//		jwtResponse.setErrorDetails(null);
//		jwtResponse.setType(UserRole.SUBADMIN);
//		jwtResponse.setMessage("Account Created Successfully-" + saveSubAdmin.getFirstName());
//
//		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
//	}

//	// Sub Admin Signin Handler
//	@PostMapping("/subadmin/signin")
//	public ResponseEntity<JwtResponse> subAdminSignin(@RequestBody LoginRequest req) {
//
//		String username = req.getEmail();
//		String password = req.getPassword();
//
//		Authentication authentication = authenticate(username, password);
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		String jwt = jwtUtil.generateJwtToken(authentication);
//
//		// Generate Jwt Response
//		JwtResponse jwtResponse = new JwtResponse();
//
//		// Set Jwt Response
//		jwtResponse.setJwt(jwt);
//		jwtResponse.setAuthenticated(true);
//		jwtResponse.setError(false);
//		jwtResponse.setErrorDetails(null);
//		jwtResponse.setType(UserRole.SUBADMIN);
//		jwtResponse.setMessage("Account Logged in Successfully");
//
//		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);
//
//	}

	// Contestant Signup Handler
	@PostMapping("/contestant/signup")
	public ResponseEntity<JwtResponse> contestantSignupHandler(@RequestBody ContestantSignupRequest req)
			throws UserException {

		String contestNumber = req.getContestNumber();
		String fullName = req.getFullName();
		String facultyNumber = req.getFacultyNumber();
		String enrollmentNumber = req.getEnrollmentNumber();
		String gender = req.getGender();
		String department = req.getDepartment();
		String course = req.getCourse();
		String participationType = req.getParticipationType();

//		Right Now No Email and Mobile Number is needed or getting stored
//		String email = req.getEmail();
//		String mobileNumber= req.getMobileNumber();
		
//		Contestant contestant = contestantRepository.findByEnrollmentNumber(enrollmentNumber);
		Contestant contestant = contestantRepository.findByFacultyNumber(facultyNumber);

		
		if (contestant != null) {
			throw new UserException("You have already registered-");
		}

		String encodedEnrollmentNumber = passwordEncoder.encode(enrollmentNumber);
//		String encodedFacultyNumber = passwordEncoder.encode(facultyNumber);

		// CREATE THE CONTESTANT
		Contestant createdContestant = new Contestant();
		createdContestant.setContestNumber(contestNumber);
		createdContestant.setFullName(fullName);
		createdContestant.setEnrollmentNumber(encodedEnrollmentNumber);
		createdContestant.setFacultyNumber(facultyNumber);
		createdContestant.setGender(gender);
		createdContestant.setDepartment(department);
		createdContestant.setCourse(course);
		createdContestant.setParticipationType(participationType);
		
		

		Contestant saveContestant = contestantRepository.save(createdContestant);

		Authentication authentication = new UsernamePasswordAuthenticationToken(saveContestant.getFacultyNumber(),
														saveContestant.getEnrollmentNumber());

		System.out.println("AUTHENTICATION DETAILS--->"+authentication);
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
		jwtResponse.setType(UserRole.CONTESTANT);
		jwtResponse.setMessage("Registered Successfully-");

		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
	}

	// Contestant Signin(Say Join Contest) Handler
	@PostMapping("/contestant/signin")
	public ResponseEntity<JwtResponse> contestantSignin(@RequestBody ContestantLoginRequest req) {

		System.out.println("ENTERED IN RESPONSENTITY");
		
		String username = req.getFacultyNumberAsUsername();
		String password = req.getEnrollmentNumberAsPassword();

		Authentication authentication = authenticate(username, password);
		
		System.out.println("AUTHENTICATION-->"+authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtil.generateJwtToken(authentication);

		// Generate Jwt Response
		JwtResponse jwtResponse = new JwtResponse();

		// Set Jwt Response
		jwtResponse.setJwt(jwt);
		jwtResponse.setAuthenticated(true);
		jwtResponse.setError(false);
		jwtResponse.setErrorDetails(null);
		jwtResponse.setType(UserRole.CONTESTANT);
		jwtResponse.setMessage("Account Logged in Successfully");

		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.ACCEPTED);

	}
	
	//Authentication
	private Authentication authenticate(String username, String password) {
		
		UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
		
		
		System.out.println("USER DETAILS:: "+userDetails);
		
//		if (userDetails == null) {
//			System.out.println("NULL WALA HAIN");
//			throw new BadCredentialsException("No User Found");
//		}

		if(userDetails != null) {
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				throw new BadCredentialsException("invalid password");
			}
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
