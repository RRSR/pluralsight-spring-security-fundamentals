package com.pluralsight.security;

import java.io.Serializable;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

/**
 * Created by Raj Rathore on 01-Jun-18
 */
public class FitnessPermissionEvaluator implements PermissionEvaluator {

  private DataSource dataSource;

  public DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public boolean hasPermission(Authentication authentication, Object targetDomainObject,
      Object permission) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    Object[] args = {((User) authentication.getPrincipal()).getUsername(),
        targetDomainObject.getClass().getName(), permission.toString()};
    int count = jdbcTemplate.queryForObject(
        "select count(*) from permissions p where p.username = ? and p.targetClass = ? and p.permission = ?",
        args, Integer.class);
    return count == 1;
  }

  public boolean hasPermission(Authentication authentication, Serializable targetId,
      String targetType, Object permission) {

    return false;
  }
}
