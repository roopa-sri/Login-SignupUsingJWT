package com.signupandlogin.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.signupandlogin.common.APIResponse;
import com.signupandlogin.dto.LoginRequestDTO;
import com.signupandlogin.dto.SignUpRequestDTO;
import com.signupandlogin.entity.User;
import com.signupandlogin.repo.UserRepository;
import com.signupandlogin.utils.JwtUtils;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtils jwtUtils;

	public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) throws Exception {
		APIResponse apiResponse = new APIResponse();

		User userEntity = new User();
		userEntity.setName(signUpRequestDTO.getName());
		userEntity.setEmailId(signUpRequestDTO.getEmailId());
		userEntity.setIsActive(Boolean.TRUE);
		userEntity.setGender(signUpRequestDTO.getGender());
		userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
		userEntity.setPassword(signUpRequestDTO.getPassword());

		userEntity = userRepository.save(userEntity);

		String token = jwtUtils.generateJwt(userEntity);

		Map<String, Object> data = new HashMap<>();
		data.put("accessToken", token);
		apiResponse.setData(data);
		return apiResponse;
	}

	public APIResponse login(LoginRequestDTO loginRequestDTO) throws Exception {
		APIResponse apiResponse = new APIResponse();
		User user = userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDTO.getEmailId(),
				loginRequestDTO.getPassword());

		if (user == null) {
			apiResponse.setData("User Login Failed");
			return apiResponse;
		}

		String token = jwtUtils.generateJwt(user);
		jwtUtils.validateToken(token);
		apiResponse.setData(token);
		return apiResponse;
	}

}
