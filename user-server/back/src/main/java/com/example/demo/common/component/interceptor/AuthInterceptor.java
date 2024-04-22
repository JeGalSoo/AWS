package com.example.demo.common.component.interceptor;

import com.example.demo.common.component.security.JwtProvider;
import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwt;
    private final UserRepository re;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = jwt.extractTokenFromHeader(request);
        if (ObjectUtils.isEmpty(token)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        String strId = jwt.getPayload(token);
        Long id = Long.parseLong(strId);

        Optional<User> user = Optional.of(re.findById(id).get());

        if(ObjectUtils.isEmpty(user)){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
