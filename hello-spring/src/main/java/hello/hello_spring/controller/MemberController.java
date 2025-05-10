package hello.hello_spring.controller;

import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService; // MemberService자체가 자체 타입으로 동작하는 뭐시기인지... 잘 모름

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
