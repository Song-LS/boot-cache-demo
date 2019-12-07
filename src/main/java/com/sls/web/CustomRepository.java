package com.sls.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sls
 **/
@Repository
public interface CustomRepository extends JpaRepository<Custom,Integer> {

    /**
     * 通过用户名查询
     * @param customName
     * @return
     */
    Custom findByCustomName(String customName);
}
