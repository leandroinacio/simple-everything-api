package com.leandro.simpleEverythingAPI.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leandro.simpleEverythingAPI.models.CurrentUser;
import com.leandro.simpleEverythingAPI.models.User;
import com.leandro.simpleEverythingAPI.security.Authority;
import com.leandro.simpleEverythingAPI.security.JwtAuthenticationRequest;
import com.leandro.simpleEverythingAPI.security.JwtAuthenticationResponse;
import com.leandro.simpleEverythingAPI.security.JwtTokenUtil;
import com.leandro.simpleEverythingAPI.security.JwtUser;
import com.leandro.simpleEverythingAPI.services.IUserService;
import com.leandro.simpleEverythingAPI.utils.PasswordUtils;

/**
 * @author Leandro Souza
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;
    
	@Autowired
	private IUserService userService;
	
	@PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        
        // Return the token
        return ResponseEntity.ok(new CurrentUser(token, null));
    }

    @RequestMapping(value = "refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

	@PostMapping("addTest")
	public @ResponseBody ResponseEntity<Object> fetchById() {
		ArrayList<Authority> auths = new ArrayList<>();
		User user = userService.createUser(new User("test", PasswordUtils.generateBCrypt("test123"), "Tester", "Hue", "tester@hue.com", true, new Date(), auths));
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}
	
	@PostMapping("test")
	public @ResponseBody ResponseEntity<Object> test() {
		return new ResponseEntity<Object>(new JwtAuthenticationRequest("test", "test123"), HttpStatus.OK);
	}
	
}
