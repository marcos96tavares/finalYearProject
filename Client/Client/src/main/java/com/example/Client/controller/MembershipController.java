package com.example.Client.controller;


import com.example.Client.dto.MembershipDto;
import com.example.Client.entity.Membership;
import com.example.Client.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership")

public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public MembershipDto createMembership(@RequestBody MembershipDto membershipDto) {
        MembershipDto membership = membershipService.createMembership(membershipDto);
        return membership;
    }

    @PutMapping("/{id}")
    public MembershipDto updateMembership(@PathVariable Long id, @RequestBody MembershipDto membershipDto) {
        return membershipService.updateMembership(id, membershipDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMembership(@PathVariable Long id) {
        membershipService.deleteMembership(id);
    }

    @GetMapping("/{id}")
    public MembershipDto getMembershipById(@PathVariable Long id) {
        return membershipService.getMembershipById(id);
    }

    @GetMapping
    public List<MembershipDto> getAllMemberships() {
        return membershipService.getAllMemberships();
    }



    @GetMapping("/get_size")
    public Integer getUserSize() {
        return membershipService.getAllMemberships().size();
    }
}
