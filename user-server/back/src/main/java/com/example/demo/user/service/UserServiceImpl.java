package com.example.demo.user.service;

import com.example.demo.common.component.security.JwtProvider;
import com.example.demo.common.component.Messenger;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDto;
import com.example.demo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final JwtProvider jwtProvider;
    private final UserRepository repository;


    @Override
    public List<UserDto> findAll() {
        return repository.findAll().stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        Optional<UserDto> dto = Optional.ofNullable(entityToDto(Objects.requireNonNull(repository.findById(id).orElse(null))));
        return dto;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Messenger save(User user) {
        if(existsById(user.getId()))repository.save(user);
        return new Messenger();
    }

    @Override
    public Messenger deleteById(Long id) {
         if(repository.existsById(id)){
             repository.deleteById(id);
            return Messenger.builder()
                    .message("SUCCESS")
                    .build();
        }else {
             return Messenger.builder()
                     .message("FAIL")
                     .build();
         }
    }

    @Override
    public Messenger modify(User user) {
        return Messenger.builder()
                .message(repository.save(user).toString())
                .build();
    }

    @Override
    public List<?> findByName(String name) {
        List<UserDto> dto = new ArrayList<>();
//        dto.add(re.findByUsername(name));
        return dto;
    }

    @Override
    public List<?> findByJob(String job) {
        List<UserDto> dto = new ArrayList<>();
        dto.add(repository.findUsersByJob(job));
        return dto;
    }

    @Transactional
    @Override
    public Messenger login(UserDto userDto) {
        User user = repository.findByUsername(userDto.getUsername());
        String token = jwtProvider.createToken(entityToDto(user));
        boolean flag = user.getPassword().equals(userDto.getPassword());
        repository.modifyTokenById(token,user.getId());
        log.info(jwtProvider.getPayload(token));

        return Messenger.builder()
                .message(flag? "SUCCESS":"FAIL")
                .accessToken(flag?jwtProvider.createToken(userDto):"None")
                .build();
//        User user = re.findByUsername(userDto.getUsername());
//        if(userDto.getPassword().equals(user.getPassword())){
//            return Messenger.builder().message("SUCCESS").build();
//        }else{
//            return Messenger.builder().message("FAIL").build();
//        }
    }



    @Override
    public Messenger findByUsername(String username) {
        return null;
    }

    @Override
    public Messenger existsByUsername(String username) {
        log.info(username);
        return Messenger.builder()
                .message(repository.existsByUsername(username)?"true":"false")
                .build();
    }
}