package com.pairone.library.controller;

import com.pairone.library.dto.member.MemberCreateDto;
import com.pairone.library.dto.member.MemberListDto;
import com.pairone.library.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberListDto> allMember() {
        return memberService.getMembers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addMember(@RequestBody MemberCreateDto member) {
        memberService.addMember(member);
    }
}
