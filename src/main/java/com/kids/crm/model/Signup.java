package com.kids.crm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Setter
@Getter
public class Signup {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String Address;
    private long[] enrollingIds;
    private String session;
    private String email;
    private String password;
    private String confirmPassword;
}
