package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class ResponseBodyController {

    @RequestMapping("/response-body-string-v1")
    public void method1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @RequestMapping("/response-body-string-v2")
    public ResponseEntity<String> method2() throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

//    @ResponseBody
    @RequestMapping("/response-body-string-v3")
    public String method3() {
        return "ok";
    }

    @RequestMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> method4() {
        HelloData helloData = new HelloData();
        helloData.setUsername("lim");
        helloData.setAge(30);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @RequestMapping("/response-body-json-v2")
    public HelloData method5() {
        HelloData helloData = new HelloData();
        helloData.setUsername("lim");
        helloData.setAge(30);

        return helloData;
    }

}
