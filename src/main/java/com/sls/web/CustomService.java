package com.sls.web;

import cn.gjing.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sls
 **/
@Service
@Slf4j
public class CustomService {
    @Resource
    private CustomRepository customRepository;

    @Cacheable(value = "user", key = "#customId")
    public Custom getCustom(Integer customId) {
        log.warn("通过数据库去查询，用户id为:{}", customId);
        return customRepository.findById(customId)
                .orElseThrow(()-> new UserNotFountException("Users don't exist"));
    }

    @CacheEvict(value = "user", key = "#customId")
    public void deleteCustom(Integer customId) {
        Custom custom = customRepository.findById(customId)
                .orElseThrow(()->new UserNotFountException("User don't exist"));
        customRepository.delete(custom);
    }

    public Boolean insertCustom(String customName) {
        Custom custom = customRepository.findByCustomName(customName);
        if (custom == null) {
            customRepository.save(Custom.builder()
                    .customName(customName)
                    .customNumber(Integer.valueOf(RandomUtil.generateNumber(6)))
                    .build());
            return true;
        }
        return false;
    }
}
