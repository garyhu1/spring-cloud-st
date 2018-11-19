package com.garyhu.repository;

import com.garyhu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author: garyhu
 * @since: 2018/11/7 0007
 * @decription:
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {
}
