package com.auth.exam.DAO;

import com.auth.exam.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
