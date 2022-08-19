package com.example.shadow.controller;

import com.example.shadow.test.Test_Keyword;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Repository
public interface ShadowRepository extends JpaRepository<Test_Keyword, Integer> {

//    boolean existsByQuestion();
}
