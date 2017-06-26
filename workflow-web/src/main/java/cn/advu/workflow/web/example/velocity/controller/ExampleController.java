package cn.advu.workflow.web.example.velocity.controller;


import cn.advu.workflow.web.example.velocity.vo.ExampleVO;
import cn.advu.workflow.web.example.velocity.vo.PullDownVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 示例
 */
@Controller
@RequestMapping("/example")
public class ExampleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

    /**
     * 测试页面
     * @return
     */
    @RequestMapping(value = "/index")
    private String index(Model model) {
        ExampleVO exampleVO = new ExampleVO();
        exampleVO.setId("1");
        exampleVO.setName("weiqz");
        exampleVO.setAge("1");

        ExampleVO anotherExampleVO = new ExampleVO();
        anotherExampleVO.setId("2");
        anotherExampleVO.setName("robot");
        anotherExampleVO.setAge("0");

        List<ExampleVO> userList = new ArrayList();
        userList.add(exampleVO);
        userList.add(anotherExampleVO);

        List<PullDownVO> pulldownList = new ArrayList();
        PullDownVO pulldown = new PullDownVO();
        pulldown.setId("1");
        pulldown.setName("北京");
        pulldownList.add(pulldown);
        pulldown = new PullDownVO();
        pulldown.setId("2");
        pulldown.setName("图们");
        pulldownList.add(pulldown);
        pulldown = new PullDownVO();
        pulldown.setId("3");
        pulldown.setName("石家庄");
        pulldownList.add(pulldown);


        model.addAttribute("exampleVO",exampleVO);
        model.addAttribute("pulldownList",pulldownList);
        model.addAttribute("userList",userList);
        return "/example/index";
    }


    @RequestMapping("/filUpload")
    public String getTest(HttpServletRequest request){
        return "example/filUpload";
    }

//    public static void main(String[] args){
//        Gson gson = new Gson();
//        String Str = "bid:3001818675,scpid:5506856";
//        BookConsume bookConsume = gson.fromJson(Str, new TypeToken<BookConsume>() {}.getType());
//        System.out.print("bookConsume:"+bookConsume.getBid());
//    }



}
