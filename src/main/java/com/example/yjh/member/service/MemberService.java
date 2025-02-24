package com.example.yjh.member.service;

import com.example.yjh.member.dto.MemberResponseDto;
import com.example.yjh.member.dto.MemberUpdateRequestDto;
import com.example.yjh.member.entity.Member;
import com.example.yjh.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(member -> new MemberResponseDto(member.getId(), member.getEmail())).toList();
    }

    @Transactional(readOnly = true)
        public MemberResponseDto findById(Long memberId) {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("그런 사람 없음!"));
            return new MemberResponseDto(
                member.getId(),
                member.getEmail()
        );
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalStateException("그런 사람 없음!"));
        member.update(dto.getEmail());
    }

    @Transactional
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
