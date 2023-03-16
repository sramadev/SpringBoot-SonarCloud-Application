package com.springboot2.jpacrudexample.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot2.jpacrudexample.exception.ResourceNotFoundException;
import com.springboot2.jpacrudexample.model.SonarJobInfo;
import com.springboot2.jpacrudexample.repository.SonarJobInfoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class SonarJobInfoController {
	@Autowired
	private SonarJobInfoRepository sonarJobInfoRepository;

	@GetMapping("/sonarJobs")
	public List<SonarJobInfo> getAllJobs() {
		return sonarJobInfoRepository.findAll();
	}

	@GetMapping("/sonarJobs/{id}")
	public ResponseEntity<SonarJobInfo> getJobById(@PathVariable(value = "id") Long jobId)
			throws ResourceNotFoundException {
		SonarJobInfo sonarJobInfo = sonarJobInfoRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("SonarJob not found for this id :: " + jobId));
		return ResponseEntity.ok().body(sonarJobInfo);
	}

	@PostMapping("/sonarJobs")
	public SonarJobInfo createJob(@Valid @RequestBody SonarJobInfo sonarJobInfo) {
		return sonarJobInfoRepository.save(sonarJobInfo);
	}

	@PutMapping("/sonarJobs/{id}")
	public ResponseEntity<SonarJobInfo> updateJob(@PathVariable(value = "id") Long jobId,
			@Valid @RequestBody SonarJobInfo sonarJobIndo) throws ResourceNotFoundException {
		SonarJobInfo sonarJobInfo = sonarJobInfoRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("SonarJob not found for this id :: " + jobId));

		sonarJobInfo.setEmailId(sonarJobIndo.getEmailId());
		sonarJobInfo.setJobType(sonarJobIndo.getJobType());
		sonarJobInfo.setJobName(sonarJobIndo.getJobName());
		final SonarJobInfo updateJobInfo = sonarJobInfoRepository.save(sonarJobInfo);
		return ResponseEntity.ok(updateJobInfo);
	}

	@DeleteMapping("/sonarJobs/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long jobId)
			throws ResourceNotFoundException {
		SonarJobInfo sonarJobInfo = sonarJobInfoRepository.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("SonarJob not found for this id :: " + jobId));

		sonarJobInfoRepository.delete(sonarJobInfo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
