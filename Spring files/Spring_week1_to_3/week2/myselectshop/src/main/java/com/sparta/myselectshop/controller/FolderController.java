package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.FolderRequestDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
