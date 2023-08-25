package com.sparta.springauth.service;

import com.sparta.springauth.dto.LoginRequestDto;
import com.sparta.springauth.dto.SignupRequestDto;
import com.sparta.springauth.entity.User;
import com.sparta.springauth.entity.UserRoleEnum;
import com.sparta.springauth.jwt.JwtUtil;
import com.sparta.springauth.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service  // @RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;  // 내가 등록한거 가져오기

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN _일반 사용자인지 관리자인지 구분하기 위해 사용함
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";  // 실제로 이렇게 주진 않음

    public void signup(SignupRequestDto requestDto) {

        // - username 변수
        String username = requestDto.getUsername();
        // - password encoding
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, email, role);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인 -> 받아온 값을 기준으로 DB에 존재하는지 일치여부 확인 필요
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())) { // (평문, 암호문)
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        // 인증되었으니 JWT 생성 및 쿠키에 저장 후 Response 객체에 추가하기
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());   // 파라미터는 이름과 role이 필요한데 둘 다 DB에서 인증된 값으로 넣어준다
        jwtUtil.addJwtToCookie(token, res);  // 위에서 생성된 토큰을 쿠키에 저장하는 메서드
    }
}