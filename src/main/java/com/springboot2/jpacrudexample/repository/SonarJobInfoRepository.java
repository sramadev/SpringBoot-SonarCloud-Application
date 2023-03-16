package com.springboot2.jpacrudexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot2.jpacrudexample.model.SonarJobInfo;

@Repository
public interface SonarJobInfoRepository extends JpaRepository<SonarJobInfo, Long>{

}
