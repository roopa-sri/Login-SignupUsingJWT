package com.signupandlogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.signupandlogin.common.APIResponse;
import com.signupandlogin.dto.LoginRequestDTO;
import com.signupandlogin.dto.SignUpRequestDTO;
import com.signupandlogin.service.LoginService;
import com.signupandlogin.utils.JwtUtils;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signup(@RequestBody SignUpRequestDTO signUpRequestDTO) throws Exception {
		APIResponse apiResponse = loginService.signUp(signUpRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
		APIResponse apiResponse = loginService.login(loginRequestDTO);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@GetMapping("/validateToken")
	public ResponseEntity<APIResponse> privateApi(
			@RequestHeader(value = "authorization", defaultValue = "") String authorization) throws Exception {
		APIResponse apiResponse = new APIResponse();
		jwtUtils.validateToken(authorization);
		apiResponse.setData("Valid Token");
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
