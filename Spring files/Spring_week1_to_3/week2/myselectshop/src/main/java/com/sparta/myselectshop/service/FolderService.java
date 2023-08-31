package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public void addFolders(List<String> folderNames, User user) {
        // 이미 생성된 폴더를 먼저 조회한다(폴더 중복생성 방지)
        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);
        // findAll: 천제를 찾는데 User가 누군지에 따른 User 기준으로 찾는다
        // 그리고 폴더테이블의 이름도 찾는다

        // saveAll 사용할거임
        List<Folder> folderList = new ArrayList<>();
        for (String folderName : folderNames) {
            // 주어진 값 folderNames과
            // 찾아온 값 existFolderList을 비교해서 일치하지 않는것만 저장해야됨
            if(!isExistFolderName(folderName, existFolderList)){
                // 동일하지 않을때만 folderList에 폴더 자장
                Folder folder = new Folder(folderName, user);
                folderList.add(folder);
            } else{
        throw new IllegalArgumentException("폴더명이 중복되었습니다");
            }
        }
        folderRepository.saveAll(folderList);
    }

    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user); // 해당 유저가 등록한 폴더만 넘겨줌
        List<FolderResponseDto> responseDtoList = new ArrayList<>();

        for (Folder folder : folderList) {
            responseDtoList.add(new FolderResponseDto(folder));
        }
        return responseDtoList;
    }

    // 폴더 존재하는지 여부
    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        for (Folder existFolder : existFolderList) {
            if(folderName.equals(existFolder.getName())){
                return true;
            }
        }
        return false;
    }

}


