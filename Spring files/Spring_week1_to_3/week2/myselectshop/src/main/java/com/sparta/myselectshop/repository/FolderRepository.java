package com.sparta.myselectshop.repository;

import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);
    // SQL 쿼리문

    // findAll : select * from folder
    // ByUser : where user_id = ?    (ex. 1) 유저 이름이 1
    // AndName : and name in (?,?,?) (ex. '1', '2', '3') 유저1의 폴더가 3개인데 폴더명이 1, 2, 3임


}
