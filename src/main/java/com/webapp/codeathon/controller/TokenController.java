package com.webapp.codeathon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fetch-jwt-auth")
public class TokenController {
  
  @GetMapping("/token")
  public ResponseEntity<String> getToken(@CookieValue(name = "jwtToken") String jwtToken) {
    if (jwtToken != null) {
      return ResponseEntity.ok(jwtToken);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
  }
}