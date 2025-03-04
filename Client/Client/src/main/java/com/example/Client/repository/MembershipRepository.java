package com.example.Client.repository;

import com.example.Client.entity.Membership;
import com.example.Client.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MembershipRepository extends JpaRepository<Membership , Long> {


    Membership findMembershipByUserId(User user);
}
