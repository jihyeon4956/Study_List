package com.example.jwtlogin.service;

import com.example.jwtlogin.dto.LoginRequestDto;
import com.example.jwtlogin.dto.SignupRequestDto;
import com.example.jwtlogin.dto.StatusResponseDto;
import com.example.jwtlogin.dto.UserUpdateRequestDto;
import com.example.jwtlogin.entity.User;
import com.example.jwtlogin.jwt.JwtUtil;
import com.example.jwtlogin.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public StatusResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        // 비밀번호느 인코딩으로 저장
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복확인
        Optional<User> checkUsernameOverlap = userRepository.findByUsername(username);
        if (checkUsernameOverlap.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username, password, requestDto.getEmail(), requestDto.getNickname());
        userRepository.save(user);
        return new StatusResponseDto(String.valueOf(HttpStatus.OK), "회원가입 성공");
    }

    public StatusResponseDto login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 존재여부 DB확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // password 검증
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, res);
        return new StatusResponseDto(String.valueOf(HttpStatus.OK), "로그인 성공");
    }

    @Transactional
    public StatusResponseDto updateUser(UserUpdateRequestDto requestDto, HttpServletRequest req) {
        // 현재 로그인한 사용자 인증하기
        User loginUser = getAuthenticatedUser(req);
        String nickname = requestDto.getNickname();
        String password = passwordEncoder.encode(requestDto.getPassword());

        loginUser.update(password, nickname);

        userRepository.save(loginUser);
        return new StatusResponseDto(String.valueOf(HttpStatus.OK), "수정 성공!");
    }

    public StatusResponseDto deleteUser( HttpServletRequest req, HttpServletResponse res) {
        User loginUser = getAuthenticatedUser(req);

        // 쿠키에서 토큰 삭제
        userRepository.delete(loginUser);
        jwtUtil.deleteCookie(res, JwtUtil.AUTHORIZATION_HEADER);
        return new StatusResponseDto(String.valueOf(HttpStatus.OK), "탈퇴 완료");
    }


    public StatusResponseDto logout(HttpServletRequest req, HttpServletResponse res) {
        String token = jwtUtil.getTokenFromRequest(req);
        System.out.println(token);

        if (token == null || !jwtUtil.validateToken(token.substring(7))) {
            return new StatusResponseDto(String.valueOf(HttpStatus.UNAUTHORIZED), "로그아웃 실패: 유효하지 않은 토큰");
        }
        // 쿠키에서 토큰 삭제
        jwtUtil.deleteCookie(res, JwtUtil.AUTHORIZATION_HEADER);
        return new StatusResponseDto(String.valueOf(HttpStatus.OK), "로그아웃 성공!");
    }

    // 이용자가 DB에 존재하는지 확인하는 메서드
    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 회원은 존재하지 않습니다.")
        );
    }

    // 사용자 인증확인
    public User getAuthenticatedUser(HttpServletRequest request) {
        User loginUser = (User) request.getAttribute("user");
        if (loginUser == null) {
            throw new IllegalArgumentException("인증되지 않은 사용자입니다.");
        }
        return loginUser;
    }
}
