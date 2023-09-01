package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.FolderRequestDto;
import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FolderController {

    private final FolderService folderService;

    @PostMapping("/folders")
    public void addFolders(@RequestBody FolderRequestDto folderRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // Body쪽에 String 폴더이름이 여러게 넘어오는걸 받아야함. 바디에서 넘어오니까 @RequestBody
        // 회원별로 폴더가 등록되어야 하는건데 이 프로젝트에는 security가 걸려있고 api/folders는 인가 되어있지 않음 -> 반드시 인증처리가 필요함(토큰검사하기)
        // 내가 만든 JwtAuthorizationFilter를 통해서 토큰 검사가 된 후 uthentication인증 객체 principal에 User정보가 담겨서 올거임
        // 그래서 @AuthenticationPrincipal 에 UserDetailsImpl를 가져옴 (우리가 원하는 USer정보가 들어있음)

        List<String> folderNames = folderRequestDto.getFolderNames();
        folderService.addFolders(folderNames, userDetails.getUser());
    }

    // 회원이 등록한 모든 폴더 조회 기능
    // 회원의 정보를 받아와야함, JwtAuthrizationFilter를 구현해서 회원의 정보를 UserDetails에 담고
    // 그 UserDetails는 Authentication이라는 인증객체 Principa 부분에 저장이 된다.
    // 받아오는 방법은 @AuthenticationPrincipal 애너테이션을 사용한다
    @GetMapping("/folders")
    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return folderService.getFolders(userDetails.getUser());
        // folderService.getFolders()는 이미 구형되어 있는데 이는 UserController에서
        // @GetMapping("/user-folder")에 유저의 폴더 정보를 조회해오는 기능을 추가할 때 FolderService에 만들어뒀고 폴더를 전체 조회하는 기능이다.
        // 단, 조건은 Login한 유저
    }
}
