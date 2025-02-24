package com.example.yjh.auth.service;

import com.example.yjh.auth.dto.AuthLoginRequestDto;
import com.example.yjh.auth.dto.AuthLoginResponseDto;
import com.example.yjh.auth.dto.AuthSignupRequestDto;
import com.example.yjh.member.entity.Member;
import com.example.yjh.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public AuthLoginResponseDto login(AuthLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new IllegalStateException("그런 멤버 없음~ ㅎㅎ")
        );
        return new AuthLoginResponseDto(member.getId());
    }
}
