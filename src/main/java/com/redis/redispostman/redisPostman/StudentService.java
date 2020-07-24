package com.redis.redispostman.redisPostman;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements StudentRepository {
	
	private static final String CACHE_NAME = "students";

	private RedisTemplate<String, Object> redisTemplate;

	private HashOperations<String, Long, Student> hashOperations;

	@Autowired
	public StudentService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(Student student) {
		hashOperations.put(CACHE_NAME, student.getId(), student);
	}

	@Override
	public Student findById(Long id) {
		return hashOperations.get(CACHE_NAME, id);
	}

	@Override
	public void delete(Long id) {
		hashOperations.delete(CACHE_NAME, id);

	}
	
	@Override
	public void update(Long id, Student student) {
		hashOperations.put(CACHE_NAME, id, student);

	}

	@Override
	public Map<Long, Student> findAllStudents() {
		return hashOperations.entries(CACHE_NAME);
	}
}
