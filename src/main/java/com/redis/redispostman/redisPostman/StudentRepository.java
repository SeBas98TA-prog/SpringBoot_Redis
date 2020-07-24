package com.redis.redispostman.redisPostman;

import java.util.Map;


public interface StudentRepository {
	
	void save(Student name);
	Student findById(Long id);
	Map<Long, Student> findAllStudents();
	void delete(Long id);
	void update(Long id, Student name);
}
