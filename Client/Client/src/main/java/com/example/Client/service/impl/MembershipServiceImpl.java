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

    /**
     * Handles database operations related to the Membership entity.
     * Provides methods for creating, reading, updating, and deleting Membership records.
     * Acts as the communication layer between the application and the database for Membership entities.
     */
    private final MembershipRepository membershipRepository;
    /**
     * A reference to the {@link UserRepository} which provides data access operations
     * for {@link User} entities. This repository is used to interact with the persistence
     * layer for CRUD operations and custom query methods related to the User entity.
     */
    private final UserRepository userRepository;
    /**
     * A repository used for performing CRUD operations on {@link Payment} entities.
     * Injected into the service to manage database interactions for payment-related data.
     */
    private final PaymentRepository paymentRepository;

    /**
     * Constructs a MembershipServiceImpl with the necessary repositories.
     *
     * @param membershipRepository the repository for managing Membership entities
     * @param userRepository the repository for managing User entities
     * @param paymentRepository the repository for managing Payment entities
     */
    public MembershipServiceImpl(MembershipRepository membershipRepository, UserRepository userRepository, PaymentRepository paymentRepository) {
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    /**
     * Creates a new membership by saving the associated user and payment entities and
     * converting the membership data transfer object (DTO) into an entity for persistence.
     *
     * @param membershipDto the MembershipDto object containing user, payment, and membership details.
     * @return the created MembershipDto representing the newly saved membership entity.
     */
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

    /**
     * Updates the membership details for the given membership ID using the provided MembershipDto.
     *
     * @param id the ID of the membership to update
     * @param membershipDto the data transfer object containing the updated membership details
     * @return the updated membership details as a MembershipDto
     * @throws RuntimeException if the membership, user, or payment referenced in the update is not found
     */
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

    /**
     * Deletes a membership by its unique identifier.
     *
     * @param id the unique identifier of the membership to be deleted
     */
    @Override
    public void deleteMembership(Long id) {
        membershipRepository.deleteById(id);
    }

    /**
     * Retrieves a membership by its unique identifier.
     *
     * @param id the unique identifier of the membership to retrieve
     * @return the membership details as a MembershipDto object
     * @throws RuntimeException if the membership with the specified id is not found
     */
    @Override
    public MembershipDto getMembershipById(Long id) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        return convertToDto(membership);
    }

    /**
     * Retrieves all memberships as a list of MembershipDto objects.
     *
     * @return a list of MembershipDto objects representing all memberships in the system.
     */
    @Override
    public List<MembershipDto> getAllMemberships() {
        return membershipRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a MembershipDto object to a Membership entity.
     *
     * @param dto The MembershipDto object to be converted.
     * @return The corresponding Membership entity.
     */
    private Membership convertToEntity(MembershipDto dto) {
        Membership membership = new Membership();
        membership.setPaymentStatus(dto.getPaymentStatus());
        membership.setUserId(dto.getUserId());
        membership.setPaymentId(dto.getPaymentId());
        return membership;
    }
    /**
     * Converts a Membership entity into a MembershipDto.
     *
     * @param membership the Membership entity to be converted
     * @return the converted MembershipDto
     */
    private MembershipDto convertToDto(Membership membership) {
        return new MembershipDto(
                membership.getMembershipId(),
                membership.getUserId(),
                membership.getPaymentId(),
                membership.getPaymentStatus()
        );
    }
}
