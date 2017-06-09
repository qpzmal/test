package cn.advu.workflow.web.controller.workflow;

import cn.advu.workflow.web.facade.workflow.ActivitiFacade;
import cn.advu.workflow.web.facade.workflow.vo.WorkflowVO;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by weiqz on 2017/6/4.
 */
@Controller
@RequestMapping("workflow")
public class WorkflowController {

    private static Logger LOGGER = LoggerFactory.getLogger(WorkflowController.class);

    @Autowired
    private ActivitiFacade activitiFacade;

    /**
     * 部署管理首页显示
     * @return
     */
    @RequestMapping("/deployIndex")
    public String deployHome(Model model) {
        // 1:查询部署对象信息，对应表（act_re_deployment）
        List<Deployment> depList = activitiFacade.findDeploymentList();

        // 2:查询流程定义的信息，对应表（act_re_procdef）
        List<ProcessDefinition> pdList = activitiFacade.findProcessDefinitionList();

        model.addAttribute("depList", depList);
        model.addAttribute("pdList", pdList);

        return "workflow/deploy_index";
    }

    /**
     * 发布流程
     * @return
     */
    @RequestMapping("/saveDeploy")
    public String newdeploy( @RequestParam("upload_file") CommonsMultipartFile uploadFile, WorkflowVO workflowVO){
        //获取页面传递的值
//        //1：获取页面上传递的zip格式的文件，格式是File类型
//        File file = workflowBean.getFile();
        //文件名称
        String filename = workflowVO.getFilename();
        //完成部署
        activitiFacade.saveNewDeploye(uploadFile, filename);
        // TODO 改为ajax请求
        return "workflow/deploy_index";
    }

    /**
     * 删除部署信息
     */
    @RequestMapping("/delDeploy")
    public String delDeployment(WorkflowVO workflowVO){
        //1：获取部署对象ID
        String deploymentId = workflowVO.getDeploymentId();
        //2：使用部署对象ID，删除流程定义
        activitiFacade.deleteProcessDefinitionByDeploymentId(deploymentId);
        return "workflow/deploy_index";
    }

    /**
     * 查看流程图
     * @throws Exception
     */
    public String viewImage(HttpServletResponse response, WorkflowVO workflowVO) throws Exception{
        //1：获取页面传递的部署对象ID和资源图片名称
        //部署对象ID
        String deploymentId = workflowVO.getDeploymentId();
        //资源图片名称
        String imageName = workflowVO.getImageName();
        //2：获取资源文件表（act_ge_bytearray）中资源图片输入流InputStream
        InputStream in = activitiFacade.findImageInputStream(deploymentId,imageName);
        //3：从response对象获取输出流
        OutputStream out = response.getOutputStream();
        //4：将输入流中的数据读取出来，写到输出流中
        for(int b=-1;(b=in.read())!=-1;){
            out.write(b);
        }
        out.close();
        in.close();
        //将图写到页面上，用输出流写
        return null;
    }

//    // 启动流程
//    public String startProcess(){
//        //更新请假状态，启动流程实例，让启动的流程实例关联业务
//        activitiFacade.saveStartProcess(workflowBean);
//        return "listTask";
//    }
//
//
//
//    /**
//     * 任务管理首页显示
//     * @return
//     */
//    public String listTask(){
//        //1：从Session中获取当前用户名
//        String name = SessionContext.get().getName();
//        //2：使用当前用户名查询正在执行的任务表，获取当前任务的集合List<Task>
//        List<Task> list = activitiFacade.findTaskListByName(name);
//        ValueContext.putValueContext("list", list);
//        return "task";
//    }
//
//    /**
//     * 打开任务表单
//     */
//    public String viewTaskForm(){
//        //任务ID
//        String taskId = workflowBean.getTaskId();
//        //获取任务表单中任务节点的url连接
//        String url = activitiFacade.findTaskFormKeyByTaskId(taskId);
//        url += "?taskId="+taskId;
//        ValueContext.putValueContext("url", url);
//        return "viewTaskForm";
//    }
//
//    // 准备表单数据
//    public String audit(){
//        //获取任务ID
//        String taskId = workflowBean.getTaskId();
//        /**一：使用任务ID，查找请假单ID，从而获取请假单信息*/
//        LeaveBill leaveBill = activitiFacade.findLeaveBillByTaskId(taskId);
//        ValueContext.putValueStack(leaveBill);
//        /**二：已知任务ID，查询ProcessDefinitionEntiy对象，从而获取当前任务完成之后的连线名称，并放置到List<String>集合中*/
//        List<String> outcomeList = activitiFacade.findOutComeListByTaskId(taskId);
//        ValueContext.putValueContext("outcomeList", outcomeList);
//        /**三：查询所有历史审核人的审核信息，帮助当前人完成审核，返回List<Comment>*/
//        List<Comment> commentList = activitiFacade.findCommentByTaskId(taskId);
//        ValueContext.putValueContext("commentList", commentList);
//        return "taskForm";
//    }
//
//    /**
//     * 提交任务
//     */
//    public String submitTask(){
//        activitiFacade.saveSubmitTask(workflowBean);
//        return "listTask";
//    }
//
//    /**
//     * 查看当前流程图（查看当前活动节点，并使用红色的框标注）
//     */
//    public String viewCurrentImage(){
//        //任务ID
//        String taskId = workflowBean.getTaskId();
//        /**一：查看流程图*/
//        //1：获取任务ID，获取任务对象，使用任务对象获取流程定义ID，查询流程定义对象
//        ProcessDefinition pd = activitiFacade.findProcessDefinitionByTaskId(taskId);
//        //workflowAction_viewImage?deploymentId=<s:property value='#deploymentId'/>&imageName=<s:property value='#imageName'/>
//        ValueContext.putValueContext("deploymentId", pd.getDeploymentId());
//        ValueContext.putValueContext("imageName", pd.getDiagramResourceName());
//        /**二：查看当前活动，获取当期活动对应的坐标x,y,width,height，将4个值存放到Map<String,Object>中*/
//        Map<String, Object> map = activitiFacade.findCoordingByTask(taskId);
//        ValueContext.putValueContext("acs", map);
//        return "image";
//    }
//
//    // 查看历史的批注信息
//    public String viewHisComment(){
//        //获取清单ID
//        Long id = workflowBean.getId();
//        //1：使用请假单ID，查询请假单对象，将对象放置到栈顶，支持表单回显
//        LeaveBill leaveBill = leaveBillService.findLeaveBillById(id);
//        ValueContext.putValueStack(leaveBill);
//        //2：使用请假单ID，查询历史的批注信息
//        List<Comment> commentList = activitiFacade.findCommentByLeaveBillId(id);
//        ValueContext.putValueContext("commentList", commentList);
//        return "viewHisComment";
//    }
}
