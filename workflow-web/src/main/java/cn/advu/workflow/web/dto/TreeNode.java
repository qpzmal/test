package cn.advu.workflow.web.dto;

import java.util.List;

/**
 * 树节点
 */
public class TreeNode {
    private String text;
    private Integer value;

    private List<TreeNode> nodes;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }
}
