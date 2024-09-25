package com.example.dynamicpropertychange.vo;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "dynamic_properties")
public class DynamicPropertiesVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dynamic_property_id")
    private long dynamicPropertyId;

    @Column(name = "dynamic_property_name", columnDefinition = "text")
    private String dynamicPropertyName;

    @Column(name = "dynamic_property_value", columnDefinition = "text")
    private String dynamicPropertyValue;

    @Column(name = "to_update", length = 1, columnDefinition = "int default 0")
    private int toUpdate;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "modified_on")
    private Date modifiedOn;
}
