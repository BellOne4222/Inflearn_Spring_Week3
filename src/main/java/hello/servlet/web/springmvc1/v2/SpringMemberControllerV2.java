// form, list, save 통합

package hello.servlet.web.springmvc1.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 클래스 단위 -> 메서드 단위
 * @RequestMapping 클래스 레벨과 메서드 레벨 조합
 */

@Controller
@RequestMapping("/springmvc/v2/members") // 앞에 requestmapping으로 경로 선언해주면 밑의 requestmapping 어노테이션의 중복된 경로는 안써도된다.
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }
    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse
            response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        ModelAndView mav = new ModelAndView("save-result");
        mav.addObject("member", member);
        return mav;
    }

    // /springmvc/v2/members
    @RequestMapping
    public ModelAndView members() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mav = new ModelAndView("members");
        mav.addObject("members", members);
        return mav;
    }

}
