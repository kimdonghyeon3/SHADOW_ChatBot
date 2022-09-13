package com.example.shadow.domain.shadow.Repository;

import com.example.shadow.domain.shadow.entity.Flow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowRepository extends JpaRepository<Flow, Long> {

}
