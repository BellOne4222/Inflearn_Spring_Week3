package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // *은 하위에 어떤 것이 들어와도 서블릿이 호출 가능
public class FrontControllerServletV1 extends HttpServlet {
    private Map<String, ControllerV1> controllerMap = new HashMap<>(); // 다형성 활용

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        //"/front-controller/v1/*"의  uri 정보를 뺄 수 있다.
        String requestURI = request.getRequestURI();
        
        //new MemberListControllerV1()이 반환 된다., 다형성 : 부모는 자식을 다 받아드릴 수 있다, 담길 수 있다.
        ControllerV1 controller = controllerMap.get(requestURI);

        // 예외 처리(null)
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404error
            return;
        }
        controller.process(request, response);

    }
}
