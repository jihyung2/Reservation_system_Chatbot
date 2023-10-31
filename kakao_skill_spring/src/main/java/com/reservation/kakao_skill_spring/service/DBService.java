package com.reservation.kakao_skill_spring.service;


import com.reservation.kakao_skill_spring.entity.Database;
import com.reservation.kakao_skill_spring.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    private final DBRepository dbRepository;

    @Autowired
    public DBService(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public Database saveDB(Database database) {
        return dbRepository.save(database);
    }

    public List<Database> getAllDB() {
        return dbRepository.findAll();
    }

    public List<Database> getDeleteDB(String DBnumber) { //Request에서 받은 삭제할 문제 이름을
        // 문제 이름에 해당하는 문제를 찾습니다.
        List<Database> deleteProblems = dbRepository.findByNumber(DBnumber); // Repository에서 name을 찾기
        //List<Problem> deleteProblems = problemRepository.findAll(); 이렇게하면 모든 문제를 다 지우는 내용임
        // 찾은 문제를 삭제합니다.
        dbRepository.deleteAll(deleteProblems); // 찾은 이름과 연결된 정보를 다 지운다.

        return deleteProblems;
    }
    public List<Database> getSearchDB(String DBnumber) { //Request에서 받은 삭제할 문제 이름을
        // 문제 이름에 해당하는 문제를 찾습니다.
        List<Database> SearchDB = dbRepository.findByNumber(DBnumber); // Repository에서 name을 찾기
        //List<Problem> deleteProblems = problemRepository.findAll(); 이렇게하면 모든 문제를 다 지우는 내용임
        // 찾은 문제를 삭제합니다.

        return SearchDB;
    }
}