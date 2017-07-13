package cn.advu.workflow.web.example.echarts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weiqz on 2017/7/12.
 */
@Controller
@RequestMapping("/echartsDemo")
public class EchartsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchartsController.class);

    /**
     * 测试页面
     * @return
     */
    @RequestMapping(value = "/index")
    private String index(Model model) {
        return "/example/echarts";
    }

}
