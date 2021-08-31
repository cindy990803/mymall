package com.megait.mymall.validation;

import com.megait.mymall.domain.Member;
import com.megait.mymall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component  //빈으로 등록됨
@RequiredArgsConstructor
@Slf4j
public class JoinFormValidator implements Validator {
    private final MemberRepository memberRepository;

    // ctrl + i 단축키 (override)

    // 이 Validator(JoinFormValidator)가 해당 클래스(clazz)를 지원하는지
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(JoinFormVo.class);  //.class파일(클래스, 인터페이스, enum)
        //clazz가 JoinFormVo.class거나 그 자식이면 true, 아니면 false
        //유효성 검사가 가능한 클래스인지 판별
    }

    //실제로 스프링이 호출할 검사 본문 내용
    @Override
    public void validate(Object target, Errors errors) {
        // target: 유효성 검사 대상(지금의 경우 JoinFormVo)
        // errors: 유효성 검사 실패 시 에러메세지를 담을 객체
        JoinFormVo vo = (JoinFormVo) target;
        Optional<Member> optional = memberRepository.findByEmail(vo.getEmail());
        if (optional.isPresent()) {  //이미 해당 회원이 존재
            errors.rejectValue(
                    "email",  //검사 에러가 발생한 필드 이름
                    "duplicate.email",  //에러 코드(맘대로 쓰면 됨)
                    "이미 가입된 이메일입니다."  //보여줄 에러메세지
            );
            log.info("중복된 이메일 : {}", errors.getAllErrors());
        }

    }
}
