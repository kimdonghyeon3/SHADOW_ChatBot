package com.example.shadow.chatbot.Repository;

import com.example.shadow.chatbot.shadow.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowRepository extends JpaRepository<Flow, Long> {

}
