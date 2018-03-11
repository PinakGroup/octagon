package com.kids.crm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity(name = "student")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Student extends User{
    private String phone;
    private String address;

}
