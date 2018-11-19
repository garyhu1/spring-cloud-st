package com.garyhu.repository;

import com.garyhu.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author : Administrator
 * @decripetion :
 * @since : 2018/10/28
 **/
@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department,Integer> {


}
