package cn.advu.workflow.web.service.system;

/**
 * Created by weiqz on 2017/6/24.
 */
public interface NoticeService {

    public void doNotify(String taskId, String template);
}
