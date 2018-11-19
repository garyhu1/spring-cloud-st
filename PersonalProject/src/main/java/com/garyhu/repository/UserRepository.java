package com.garyhu.repository;

import com.garyhu.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author : Administrator
 * @decripetion :
 * @since : 2018/10/28
 **/
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

    // 在方法上使用@Query注解的JPQL写法
    @Query("select u from User u where u.name=?1 and u.departmentId=?2")
    public User findUser(String name,Integer departmentId);

    // 如果比较喜欢SQL写法而不是JPQL,可设置@Query的nativeQuery属性值为true
    @Query(value = "select * from user where name=?1 and department_id=?2",nativeQuery = true)
    public User nativeQuery(String name,Integer departmentId);

    // 无论是SQL还是JPQL都支持命名参数
    @Query(value = "select * from user where name=:name and department_id=:departmentId",nativeQuery = true)
    public User nativeQuery2(String name,Integer departmentId);

    // 查询结果并非是Entity,可以用Object[]数组替代，如返回分组统计每个部门的用户数
    @Query(value = "select department_id,count(*) from user group by department_id",nativeQuery = true)
    public List<Object[]> queryUserCount();

    // 查询时可以使用Pageable和Sort来完成翻页和排序
    @Query("select u from User u where u.departmentId=?1")
    public Page<User> queryUsers(Integer departmentId,Pageable page);

    // @Query 还允许更新、删除语句，此时需要搭配@Modifying使用
    @Modifying
    @Query("update User u set u.name=?1 where u.id=?2")
    public int updateName(String name,Integer id);
}
