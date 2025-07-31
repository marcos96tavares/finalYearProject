package com.example.Client.service.impl;

import com.example.Client.dto.UserDto;
import com.example.Client.entity.Membership;
import com.example.Client.entity.User;
import com.example.Client.exception.EmailAlreadyExistsException;
import com.example.Client.exception.ResourceNotFoundException;
import com.example.Client.service.ApiBookingCall;
import com.example.Client.repository.MembershipRepository;
import com.example.Client.repository.UserRepository;
import com.example.Client.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {


    /**
     * An instance of {@link UserRepository} used to perform CRUD operations
     * on the User entity in the database.
     */
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MembershipRepository membershipRepository;


    private final   ApiBookingCall apiBookingCall;
    /**
     * Constructs a new instance of UserServiceImp with the specified UserRepository.
     *
     * @param userRepository the repository used to perform data operations for users
     */
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, MembershipRepository membershipRepository, ApiBookingCall apiBookingCall) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.membershipRepository = membershipRepository;

        this.apiBookingCall = apiBookingCall;
    }

    /**
     * Creates a new user in the system based on the provided user data transfer object.
     *
     * @param userDto the data transfer object containing user information to be created
     * @return the created user as a data transfer object
     */
    @Override
    public User createUser(UserDto userDto) {

        User user = convertToEntity(userDto);
        return userRepository.save(user);
    }

    /**
     * Updates the details of an existing user with the specified id using the provided UserDto object
     * and saves the updated user information in the repository.
     *
     * @param id       the unique identifier of the user to be updated
     * @param userDto  the UserDto object containing the updated user details (first name, last name, and email)
     * @return the updated user data as a UserDto object
     * @throws RuntimeException if the user with the specified id is not found in the repository
     */
    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setFirstName(userDto.getFirstNameDto());
        user.setLastName(userDto.getLastNameDto());
        user.setEmail(userDto.getEmailDto());

        return convertToDto(userRepository.save(user));
    }

    /**
     * Deletes a user by the given identifier.
     *
     * @param id the unique identifier of the user to be deleted
     */
    @Transactional
    @Override
    public void deleteUser(Long id) {

        Membership membership = membershipRepository.findMembershipByUserId_UserId(id);
        membershipRepository.delete(membership);
        apiBookingCall.deleteBooking(id);
        userRepository.deleteById(id);
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique identifier of the user to retrieve
     * @return a UserDto object containing the user's information
     * @throws RuntimeException if no user is found with the given identifier
     */
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return convertToDto(user);
    }

    /**
     * Retrieves a list of all users in the system.
     *
     * @return a list of {@link UserDto} objects representing all users.
     */
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailAlreadyExistsException("Wrong Email"));

        return convertToDto(user);
    }


    /**
     * Converts a UserDto object into a User entity.
     *
     * @param userDto the UserDto object to be converted
     * @return the corresponding User entity
     */
    public User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserDtoId());
        user.setFirstName(userDto.getFirstNameDto());
        user.setLastName(userDto.getLastNameDto());
        user.setEmail(userDto.getEmailDto());
        user.setAge(userDto.getAgeDto());
        user.setPassword(passwordEncoder.encode(userDto.getPasswordDto()));
        return user;
    }


    /**
     * Converts a User entity to a UserDto object.
     *
     * @param user the User entity to be converted
     * @return the converted UserDto object containing the mapped data
     */
    public UserDto convertToDto(User user) {
        return new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getAge());
    }
}
