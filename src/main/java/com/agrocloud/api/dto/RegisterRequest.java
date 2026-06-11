package com.agrocloud.api.dto;

import java.util.List;

public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Long spaceId;
    private Long objectId;
    private Long cropId;
    private List<Long> resourceIds;

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Long getCropId() { return cropId; }
}