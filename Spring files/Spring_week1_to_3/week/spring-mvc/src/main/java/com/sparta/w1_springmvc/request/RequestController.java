package com.sparta.w1_springmvc.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class RequestController {

    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

    // [Request sample]
    // GET http://localhost:8080/hello/request/star/Robbie/age/95
    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age) {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
    // GET http://localhost:8080/hello/request/form/param?name=Robbie&age=95
    // @RequestParam(required = false : 이게 붙어있는 변수는 값이 없어도 정상처리함
    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam(required = false) String name, int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
    // POST http://localhost:8080/hello/request/form/param
    // Header
    //  Content type: application/x-www-form-urlencoded
    // Body
    //  name=Robbie&age=95
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
    // POST http://localhost:8080/hello/request/form/model
    // Header
    //  Content type: application/x-www-form-urlencoded
    // Body
    //  name=Robbie&age=95
    // @ModelAttribute  : Body 부분에 들어온 쿼리 스트링 방식의 데이터를 객체에 mapping해서 가져올 수 있게 함
    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {
        return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age);
    }

    // [Request sample]
    // GET http://localhost:8080/hello/request/form/param/model?name=Robbie&age=95
    @GetMapping("/form/param/model")
    @ResponseBody
    public String helloRequestParam(@ModelAttribute Star star) {
        return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age);
    }

    // [Request sample]
    // POST http://localhost:8080/hello/request/form/json
    // Header
    //  Content type: application/json
    // Body
    //  {"name":"Robbie","age":"95"}
    // ★★ @RequestBody -> HTTP Body에 데이터가 넘어왔을 때 그 데이터를 처리할 수 있는 객체를 만들고 이 애노테이션을 꼭 붙인다
    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }
}