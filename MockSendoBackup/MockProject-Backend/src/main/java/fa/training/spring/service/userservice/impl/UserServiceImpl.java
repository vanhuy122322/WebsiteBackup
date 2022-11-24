package fa.training.spring.service.userservice.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fa.training.spring.dto.RequestDTO;
import fa.training.spring.dto.ResponseDTO;
import fa.training.spring.dto.shopdto.ShopDTO;
import fa.training.spring.dto.userdto.FormChangePassword;
import fa.training.spring.dto.userdto.JwtResponse;
import fa.training.spring.dto.userdto.LocationDTO;
import fa.training.spring.dto.userdto.UserDTO;
import fa.training.spring.entity.productentity.Cart;
import fa.training.spring.entity.userentity.Location;
import fa.training.spring.entity.userentity.Role;
import fa.training.spring.entity.userentity.RoleName;
import fa.training.spring.entity.userentity.User;
import fa.training.spring.mapper.usermapper.LocationMapper;
import fa.training.spring.mapper.usermapper.RoleMapper;
import fa.training.spring.mapper.usermapper.UserMapper;
import fa.training.spring.repository.productrepository.CartRepository;
import fa.training.spring.repository.userrepository.LocationRepository;
import fa.training.spring.repository.userrepository.RoleRepository;
import fa.training.spring.repository.userrepository.UserRepository;
import fa.training.spring.security.jwt.JwtProvider;
import fa.training.spring.security.userprinciple.UserPrinciple;
import fa.training.spring.service.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    LocationRepository addressRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    LocationMapper addressMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public UserDTO findUserById(RequestDTO<UserDTO> requestDTO) {
        User user = userRepository.findById(requestDTO.getId()).get();
        System.out.println(user.getRoles());
        UserDTO userDTO = userMapper.mapToDTO(user, UserDTO.class);
        Set<String> rolenames = new HashSet<>();
        for (Role role : user.getRoles()) {
            rolenames.add(role.getName());
        }
        userDTO.setRoleNames(rolenames);
        return userDTO;
    }

    @Override
    public UserDTO findByUsername(RequestDTO<UserDTO> requestDTO) {
        User user = userRepository.findUserByUsername(requestDTO.getData().getUsername());

        UserDTO userDTO = userMapper.mapToDTO(user, UserDTO.class);
        Set<String> nameRoles = new HashSet<>();
        for (Role role : user.getRoles()) {
            nameRoles.add(role.getName());
        }
        userDTO.setRoleNames(nameRoles);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = userMapper.mapToDTO(user, UserDTO.class);
            Set<String> nameRoles = new HashSet<>();
            Iterator<Role> iter = user.getRoles().iterator();
            while (iter.hasNext()) {
                nameRoles.add(iter.next().getName());
            }
            userDTO.setRoleNames(nameRoles);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public ResponseDTO<JwtResponse> login(RequestDTO<UserDTO> requestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getData().getUsername(),
                        requestDTO.getData().getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        Iterator<? extends GrantedAuthority> iter = userPrinciple.getAuthorities().iterator();
        List<String> roles = new ArrayList<>();
        while (iter.hasNext()) {
            roles.add(iter.next().getAuthority());
        }
        return new ResponseDTO<JwtResponse>(
                new JwtResponse(token, userPrinciple.getUsername(), roles), HttpStatus.OK);
    }

    @Override
    public ResponseDTO<UserDTO> createUser(RequestDTO<UserDTO> requestDTO) {
        if (userRepository.existsByUsername(requestDTO.getData().getUsername())) {
            return new ResponseDTO<>("The username existed! Please try again!", HttpStatus.OK);
        }
        if (userRepository.existsByEmail(requestDTO.getData().getEmail())) {
            return new ResponseDTO<>("The email existed! Please try again!", HttpStatus.OK);
        }

        UserDTO userDTO = new UserDTO(requestDTO.getData().getFullName(), requestDTO.getData().getUsername(),
                requestDTO.getData().getEmail(),
                passwordEncoder.encode(requestDTO.getData().getPassword()));
        Set<String> strRoles = requestDTO.getData().getRoleNames();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "customer":
                    Role customerRole = roleRepository.findRoleByName(RoleName.ROLE_CUSTOMER);
                    roles.add(customerRole);
                    break;
                case "admin":
                    Role adminRole = roleRepository.findRoleByName(RoleName.ROLE_ADMIN);
                    roles.add(adminRole);
                    break;
                case "seller":
                    Role sellerRole = roleRepository.findRoleByName(RoleName.ROLE_SELLER);
                    roles.add(sellerRole);
                    break;
            }
        });
        System.out.println(roles);
        User user = userMapper.mapToEntity(User.class, userDTO);
        user.setRoles(roles);
        userRepository.save(user);
        Cart cart = new Cart();
        cart.setUsername(user.getUsername());
        cartRepository.save(cart);
        return new ResponseDTO<>("Register success!", HttpStatus.OK);
    }

    @Override
    public ResponseDTO<UserDTO> addRoleToUser(RequestDTO<UserDTO> requestDTO) {
        Set<String> roleNames = requestDTO.getData().getRoleNames();
        User user = userRepository.findUserByUsername(requestDTO.getData().getUsername());
        Set<Role> roles = new HashSet<>();
        for (String name : roleNames) {
            if (roleRepository.findRoleByName(name) != null)
                roles.add(roleRepository.findRoleByName(name));
        }
        if (user != null) {
            user.setRoles(roles);
            userRepository.save(user);
            return new ResponseDTO<UserDTO>(userMapper.mapToDTO(user, UserDTO.class), "success", HttpStatus.OK);
        }
        return new ResponseDTO<UserDTO>("Not found", HttpStatus.OK);
    }

    @Override
    public ResponseDTO<UserDTO> changePassword(RequestDTO<FormChangePassword> requestDTO) {
        User user = userRepository.findById(requestDTO.getData().getIdFormChange()).get();
        System.out.println(user.getPassword());
        if (user != null && passwordEncoder.matches(requestDTO.getData().getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(requestDTO.getData().getNewPassword()));
            return new ResponseDTO<UserDTO>(userMapper.mapToDTO(userRepository.save(user), UserDTO.class),
                    "success", HttpStatus.OK);
        }
        return new ResponseDTO<UserDTO>(userMapper.mapToDTO(user, UserDTO.class), "fail", HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseDTO<UserDTO> updateUser(RequestDTO<UserDTO> requestDTO) {
        UserDTO userDTO = requestDTO.getData();
        System.out.println(userDTO);
        User user = userRepository.findUserByUsername(userDTO.getUsername());
        if (user != null) {
            if (user.getFullName() != null && !user.getFullName().equals(userDTO.getFullName())) {
                user.setFullName(userDTO.getFullName());
            } else if (user.getFullName() == null) {
                user.setFullName(userDTO.getFullName());
            }
            if (user.getBirthDate() != null && !user.getBirthDate().equals(userDTO.getBirthDate())) {
                user.setBirthDate(userDTO.getBirthDate());
            } else if (user.getBirthDate() == null) {
                user.setBirthDate(userDTO.getBirthDate());
            }
            if (user.getGender() != null && !user.getGender().equals(userDTO.getGender())) {
                user.setGender(userDTO.getGender());
            } else if (user.getGender() == null) {
                user.setGender(userDTO.getGender());
            }

            if (user.getLocation() != null && !user.getLocation().equals(userDTO.getLocation())) {
                user.setLocation(userDTO.getLocation());
            } else if (user.getGender() == null) {
                user.setLocation(userDTO.getLocation());
            }

            if (user.getPhone() != null && !user.getPhone().equals(userDTO.getPhone())) {
                user.setPhone(userDTO.getPhone());
            } else if (user.getGender() == null) {
                user.setPhone(userDTO.getPhone());
            }
            return new ResponseDTO<UserDTO>(userMapper.mapToDTO(userRepository.save(user), UserDTO.class),
                    "Update user success",
                    HttpStatus.OK);
        } else {
            return new ResponseDTO<UserDTO>(userDTO, "Update user fail", HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseDTO<UserDTO> addAddressToUser(RequestDTO<LocationDTO> requestDTO) {
        Location address = addressMapper.mapToEntity(Location.class, requestDTO.getData());
        addressRepository.save(address);
        User user = userRepository.findUserByUsername(requestDTO.getInput());
        user.getAddreses().add(address);
        return new ResponseDTO<UserDTO>((userMapper.mapToDTO(userRepository.save(user), UserDTO.class)),
                "Add new address success", HttpStatus.OK);
    }

    @Override
    public String deleteUser(RequestDTO<UserDTO> requestDTO) {
        User user = userRepository.findById(requestDTO.getId()).get();
        if (user != null) {
            user.setDeleteStatus(true);
            userRepository.save(user);
            return "Delete Success";
        } else {
            return "User not exist";
        }

    }

    @Override
    public boolean existsByUserName(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.existsByEmail(email);
    }

}
