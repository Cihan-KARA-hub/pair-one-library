package com.pairone.library.controller;

import com.pairone.library.dto.member.MemberCreateDto;
import com.pairone.library.dto.member.MemberListDto;
import com.pairone.library.service.MemberServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/member")
public class MemberController {
    private final MemberServiceImpl memberServiceImpl;

    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberServiceImpl = memberServiceImpl;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberListDto> allMember() {
        return memberServiceImpl.getMembers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addMember(@RequestBody MemberCreateDto member) {
        memberServiceImpl.addMember(member);
    }
}
