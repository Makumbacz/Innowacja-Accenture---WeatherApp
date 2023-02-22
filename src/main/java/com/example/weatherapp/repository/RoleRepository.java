package com.example.weatherapp.repository;

import com.example.weatherapp.model.Role;
import com.example.weatherapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
