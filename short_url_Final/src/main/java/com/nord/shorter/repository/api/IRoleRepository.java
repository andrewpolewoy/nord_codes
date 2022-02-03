package com.nord.shorter.repository.api;

import com.nord.shorter.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
