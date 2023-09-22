package com.pragma.userservice.domain.spi;

public interface ISecurityContextPort {
    String getRolFromSecurityContext();

    Long getIdFromSecurityContext();
}
