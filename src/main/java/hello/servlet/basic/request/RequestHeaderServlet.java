package hello.servlet.basic.request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    //start line 정보
    private static void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
        //--- REQUEST-LINE - start ---
        //request.getMethod() = GET
        //request.getProtocol() = HTTP/1.1
        //request.getScheme() = http
        //request.getRequestURL() = http://localhost:8080/request-header
        //request.getRequestURI() = /request-header
        //request.getQueryString() = null
        //request.isSecure() = false
        //--- REQUEST-LINE - end ---
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

//        Enumeration<String> headerNames = request.getHeaderNames(); // header 정보 모두 출력
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + request.getHeader(headerName));
//        }

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
        //--- Headers - start ---
        //host: localhost:8080
        //connection: keep-alive
        //cache-control: max-age=0
        //sec-ch-ua: "Not/A)Brand";v="99", "Google Chrome";v="115", "Chromium";v="115"
        //sec-ch-ua-mobile: ?0
        //sec-ch-ua-platform: "Windows"
        //upgrade-insecure-requests: 1
        //user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36
        //accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
        //sec-fetch-site: none
        //sec-fetch-mode: navigate
        //sec-fetch-user: ?1
        //sec-fetch-dest: document
        //accept-encoding: gzip, deflate, br
        //accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
        //--- Headers - end ---
    }
    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale()); // 언어 우선순위 가장 높은 언어 뽑아온다
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType());
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
        //--- Header ���� ��ȸ start ---
        //[Host ���� ��ȸ]
        //request.getServerName() = localhost
        //request.getServerPort() = 8080
        //
        //[Accept-Language ���� ��ȸ]
        //locale = ko_KR
        //locale = ko
        //locale = en_US
        //locale = en
        //request.getLocale() = ko_KR
        //
        //[cookie ���� ��ȸ]
        //
        //[Content ���� ��ȸ]
        //request.getContentType() = null
        //request.getContentLength() = -1
        //request.getCharacterEncoding() = UTF-8
        //--- Header ���� ��ȸ end ---
    }
    //기타 정보
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]"); // 서버 정보
        System.out.println("request.getLocalName() = " +
                request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " +
                request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
        //--- ��Ÿ ��ȸ start ---
        //[Remote ����]
        //request.getRemoteHost() = 0:0:0:0:0:0:0:1
        //request.getRemoteAddr() = 0:0:0:0:0:0:0:1
        //request.getRemotePort() = 52095
        //
        //[Local ����]
        //request.getLocalName() = 0:0:0:0:0:0:0:1
        //request.getLocalAddr() = 0:0:0:0:0:0:0:1
        //request.getLocalPort() = 8080
        //--- ��Ÿ ��ȸ end ---
    }
}

