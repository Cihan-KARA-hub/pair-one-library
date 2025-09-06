package com.pairone.library.service;

import com.pairone.library.dto.member.MemberCreateDto;
import com.pairone.library.dto.member.MemberListDto;
import com.pairone.library.entity.Member;
import com.pairone.library.entity.Role;
import com.pairone.library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void addMember(MemberCreateDto dto) {
        Member member = new Member();
        member.seteMail(dto.geteMail());
        member.setPhone(dto.getPhone());
        member.setActive(true);
        member.setLastname(dto.getLastname());
        member.setFirstname(dto.getFirstname());
        member.setAddressId(dto.getAddress());
        Role role = new Role();
        role.setType(dto.getRole().getType());
        member.setRoleId(role);
        memberRepository.save(member);
    }

    public List<MemberListDto> getMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberListDto> dtos = new ArrayList<>();
        MemberListDto dto = new MemberListDto();
        for (Member member : members) {
            dto.seteMail(member.geteMail());
            dto.setPhone(member.getPhone());
            dto.setLastname(member.getLastname());
            dto.setFirstname(member.getFirstname());
            dto.setRoleId(member.getId());
            dto.setAddressId(member.getAddressId().getId());
            dtos.add(dto);
        }
        return dtos;
    }
}
