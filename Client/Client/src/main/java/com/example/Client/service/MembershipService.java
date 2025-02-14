package com.example.Client.service;

import com.example.Client.dto.MembershipDto;

import java.util.List;

public interface MembershipService {

    MembershipDto createMembership(MembershipDto membershipDto);
    MembershipDto updateMembership(Long id, MembershipDto membershipDto);
    void deleteMembership(Long id);
    MembershipDto getMembershipById(Long id);
    List<MembershipDto> getAllMemberships();
}
