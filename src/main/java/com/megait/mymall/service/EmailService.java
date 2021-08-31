package com.megait.mymall.service;


import com.megait.mymall.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Transactional  //DB접근하는 어노테이션. 같은 클래스의 메서드를 호출당하면 DB접근이 안됨. => 그래서 EmailService클래스로 이동시켰음
    public void sendEmail(Member member) {
        // 토큰 생성
        String token = UUID.randomUUID().toString();

        // 토큰을 DB에 update
        member.setEmailCheckToken(token);

        // 이메일을 실제로 보내기
        String url = "http://localhost:8080/email-check?email=" + member.getEmail() + "&token=" + token;

        String title = "[MyMall 회원가입에 감사드립니다. 딱 한 가지 과정이 남았습니다!]";
        String message = "다음 링크를 브라우저에 붙여넣기하세요. 링크 : " + url;
        String sender = "mymall-admin-noreply@mymall.com";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(member.getEmail());
        mailMessage.setSubject(title);
        mailMessage.setText(message);
        mailMessage.setFrom(sender);

        javaMailSender.send(mailMessage);

    }
}
