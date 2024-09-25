package com.example.dynamicpropertychange.repo;

import com.example.dynamicpropertychange.vo.DynamicPropertiesVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface DynamicPropertiesRepository extends JpaRepository<DynamicPropertiesVo, Long> {

    @Query(value = "Select dynamic_property_id,\n" +
            "dynamic_property_name,\n" +
            "dynamic_property_value from dynamic_properties where to_update=:to_update", nativeQuery = true)
    List<Map<String, String>> getAllProperties(@Param("to_update") int to_update);


    @Modifying
    @Transactional
    @Query(value = "update dynamic_properties set to_update=0 where to_update=1", nativeQuery = true)
    int updateAllToUpdatedProperties();

}
