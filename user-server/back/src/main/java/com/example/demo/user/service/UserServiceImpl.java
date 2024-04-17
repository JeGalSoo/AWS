package com.example.demo.user.service;

import com.example.demo.common.component.JwtProvider;
import com.example.demo.common.component.Messenger;
import com.example.demo.common.component.PageRequestVo;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDto;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;


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

    @Override
    public Messenger login(UserDto userDto) {
        boolean flag = repository.findByUsername(userDto.getUsername()).getPassword().equals(userDto.getPassword());
        return Messenger.builder()
                .message(flag? "SUCCESS":"FAIL")
                .token(flag?jwtProvider.createToken(userDto):"None")
                .build();
//        User user = re.findByUsername(userDto.getUsername());
//        if(userDto.getPassword().equals(user.getPassword())){
//            return Messenger.builder().message("SUCCESS").build();
//        }else{
//            return Messenger.builder().message("FAIL").build();
//        }
    }
}