package com.example.demo.common.component.security;

import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDto;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.iss}")
    private String issuer;

    private final SecretKey secretkey;

    Instant expiredDate = Instant.now().plus(1, ChronoUnit.DAYS);

    public JwtProvider (@Value("${jwt.secret}") String secretKey){
        this.secretkey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));

    }

    public String createToken(UserDto userDto) {
        String token = Jwts.builder()
                .issuer(issuer)
                .signWith(secretkey)
                .expiration(Date.from(expiredDate))
                .subject("turing")
                .claim("username", userDto.getUsername())
                .claim("job", userDto.getJob())
                .claim("id", userDto.getId())
                .compact();

        log.info("로그인성공으로 발급된 토큰 " + token);
        return token;
    }

//    public String getPayload(String token) {
//        String[] chunks = token.split("\\.");
//        Base64.Decoder decoder = Base64.getUrlDecoder();
//        String header = new String(decoder.decode(chunks[0]));
//        String payload = new String(decoder.decode(chunks[1]));
//
//        System.out.printf("accessToken header : "+header);
//        System.out.printf("accessToken body : "+payload);
//
//        return header;
//    }



    public String extractTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        return bearerToken != null && bearerToken.startsWith("Bearer ")?bearerToken.substring(7):"undefined";
    }

    public Claims getPayload(String accessToken){
        return Jwts.parser().verifyWith(secretkey).build().parseSignedClaims(accessToken).getPayload();
    }

    public void printPayload(String accessToken){
        String[] chunks = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        log.info("JWT 프로바이더 Access Token Header : "+header);
        log.info("JWT 프로바이더 Access Token Payload : "+payload);
    }

//    public JWTdecoder getAuthentication(String token){
//        Claims claims = (Claims) parse(token);
//        return null;
//    }

}
