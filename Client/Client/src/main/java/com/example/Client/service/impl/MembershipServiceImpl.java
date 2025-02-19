package com.example.Client.service.impl;

import com.example.Client.dto.MembershipDto;
import com.example.Client.entity.Membership;
import com.example.Client.entity.Payment;
import com.example.Client.entity.User;
import com.example.Client.repository.MembershipRepository;
import com.example.Client.repository.PaymentRepository;
import com.example.Client.repository.UserRepository;
import com.example.Client.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public MembershipServiceImpl(MembershipRepository membershipRepository, UserRepository userRepository, PaymentRepository paymentRepository) {
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public MembershipDto createMembership(MembershipDto membershipDto) {

        User user = membershipDto.getUserId();
        Payment payment = membershipDto.getPaymentId();

        userRepository.save(user);
        paymentRepository.save(payment);

        membershipDto.setUserId(user);
        membershipDto.setPaymentId(payment);

        Membership membership = convertToEntity(membershipDto);

        return convertToDto(membershipRepository.save(membership));
    }

    @Override
    public MembershipDto updateMembership(Long id, MembershipDto membershipDto) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        membership.setPaymentStatus(membershipDto.getPaymentStatus());

        if (membershipDto.getUserId() != null) {
            User user = userRepository.findById(membershipDto.getUserId().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            membership.setUserId(user);
        }

        if (membershipDto.getPaymentId() != null) {
            Payment payment = paymentRepository.findById(membershipDto.getPaymentId().getPaymentId())
                    .orElseThrow(() -> new RuntimeException("Payment not found"));
            membership.setPaymentId(payment);
        }

        return convertToDto(membershipRepository.save(membership));
    }

    @Override
    public void deleteMembership(Long id) {
        membershipRepository.deleteById(id);
    }

    @Override
    public MembershipDto getMembershipById(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        return convertToDto(membership);
    }

    @Override
    public List<MembershipDto> getAllMemberships() {
        return membershipRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Membership convertToEntity(MembershipDto dto) {
        Membership membership = new Membership();
        membership.setPaymentStatus(dto.getPaymentStatus());
        membership.setUserId(dto.getUserId());
        membership.setPaymentId(dto.getPaymentId());
        return membership;
    }
    private MembershipDto convertToDto(Membership membership) {
        return new MembershipDto(
                membership.getMembershipId(),
                membership.getUserId(),
                membership.getPaymentId(),
                membership.getPaymentStatus()
        );
    }
}
