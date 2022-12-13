package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody:{}", messageBody);

        response.getWriter().write("ok");
    }

    //request, response없이 -> 바로 request나 body를 받을 수 있다.
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody:{}", messageBody);

        writer.write("ok");
    }

    //view 조회 안한다.
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) {
//        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        String messageBody = httpEntity.getBody();
        log.info("messageBody:{}", messageBody);

//        writer.write("ok");
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(@RequestBody String messageBody) {
//        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

//        String messageBody = httpEntity.getBody();
        log.info("start4");
        log.info("messageBody:{}", messageBody);

//        writer.write("ok");
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }
}
