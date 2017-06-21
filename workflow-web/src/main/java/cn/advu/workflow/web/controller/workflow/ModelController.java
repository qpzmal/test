package cn.advu.workflow.web.controller.workflow;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.List;

//import org.activiti.engine.repository.Model;

/**
 * Created by weiqz on 2017/6/4.
 */
@Controller
@RequestMapping("/workflow/model")
public class ModelController {

    private static Logger LOGGER = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    RepositoryService repositoryService;

    /**
     * 模型列表
     */
    @RequestMapping(value = "list")
    public String modelList(Model model) {
//        ModelAndView mav = new ModelAndView("workflow/model-list");
        List<org.activiti.engine.repository.Model> list = repositoryService.createModelQuery().list();
        model.addAttribute("modelList", list);
        return "workflow/model-list";
    }

    /**
     * 创建模型
     */
    @RequestMapping(value = "create")
    public void create(@RequestParam("name") String name, @RequestParam("key") String key, @RequestParam("description") String description,
                       HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            org.activiti.engine.repository.Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            description = StringUtils.defaultString(description);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            modelData.setKey(StringUtils.defaultString(key));

            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

            response.sendRedirect(request.getContextPath() + "/service/editor?id=" + modelData.getId());
        } catch (Exception e) {
            LOGGER.error("创建模型失败：", e);
        }
    }

    /**
     * 根据Model部署流程
     */
    @RequestMapping(value = "deploy/{modelId}")
    public String deploy(@PathVariable("modelId") String modelId, RedirectAttributes redirectAttributes) {
        try {
            org.activiti.engine.repository.Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
            redirectAttributes.addFlashAttribute("message", "部署成功，部署ID=" + deployment.getId());
        } catch (Exception e) {
            LOGGER.error("根据模型部署流程失败：modelId={}", modelId, e);
        }
        return "redirect:/workflow/model/list";
    }

    /**
     * 导出model的xml文件
     */
    @RequestMapping(value = "export/{modelId}")
    public void export(@PathVariable("modelId") String modelId, HttpServletResponse response) {
        try {
            org.activiti.engine.repository.Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, response.getOutputStream());
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
        } catch (Exception e) {
            LOGGER.error("导出model的xml文件失败：modelId={}", modelId, e);
        }
    }
}
