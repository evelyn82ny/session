package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SessionManager sessionManager;

    @GetMapping("/")
    public String homeLogin(HttpServletRequest httpServletRequest, Model model) {

        Member member = (Member)sessionManager.getSession(httpServletRequest);
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
}