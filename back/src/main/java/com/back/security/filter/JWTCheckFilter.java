package com.back.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.back.dto.MemberSecurityDTO;
import com.back.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter{

    @Override
    //필터링 하지않고 건너뛸 조건을 정의할 수 있는데
    //해당 메서드가 true 를 반환하면 건너뛰고, false 를 반환하면 필터가 작동한다.
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{

        // Preflight요청은 체크하지 않음
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        String path = request.getRequestURI();

        log.info("check uri......................."+path);

        //api/member/ 경로의 호출은 체크하지 않음
        if(path.startsWith("/")) {
            return true;
        }

        // 이미지 조회 경로는 체크하지 않는다면
        if(path.startsWith("/api/products/view/")){
            return true;
        }

        return false;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{


        log.info("------------------------JWTCheckFilter------------------");

        String authHeaderStr = request.getHeader("Authorization");

        try {
            //Bearer accestoken...
            String accessToken = authHeaderStr.substring(7);
            Map<String, Object> claims = JWTUtil.validateToken(accessToken);

            log.info("JWT claims: " + claims);

            //추가
            String email = (String) claims.get("email");
            String pw = (String) claims.get("pw");
            String name = (String) claims.get("name");
            Boolean social = (Boolean) claims.get("social");
            String number = (String) claims.get("number");
            String zipCode = (String) claims.get("zipCode");
            String nickname = (String) claims.get("nickname");
            String streetAddress = (String) claims.get("streetAddress");
            String detailAddress = (String) claims.get("detailAddress");
            List<String> memberRoleList = (List<String>) claims.get("memberRoleList");

            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(email, pw, name, social.booleanValue(),
                    number,zipCode,nickname,streetAddress,detailAddress,memberRoleList);

            log.info("-----------------------------------");
            log.info(memberSecurityDTO);
            log.info(memberSecurityDTO.getAuthorities());

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(memberSecurityDTO, pw, memberSecurityDTO.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);

        }catch(Exception e){

            log.error("JWT Check Error..............");
            log.error(e.getMessage());

            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));

            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();

        }
    }

}
