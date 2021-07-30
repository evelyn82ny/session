package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.web.session.SessionManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SessionManagerTest {

    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, mockHttpServletResponse);

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setCookies(mockHttpServletResponse.getCookies());

        Object result = sessionManager.getSession(mockHttpServletRequest);
        Assertions.assertThat(result).isEqualTo(member);

        sessionManager.expire(mockHttpServletRequest);
        Object expired = sessionManager.getSession(mockHttpServletRequest);
        Assertions.assertThat(expired).isNull();
    }
}
