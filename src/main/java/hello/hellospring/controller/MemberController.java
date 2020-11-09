package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // autowired의 부가적인 효과
    // spring bean에 등록되어 있는 객체의 의존관계를 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }





}
