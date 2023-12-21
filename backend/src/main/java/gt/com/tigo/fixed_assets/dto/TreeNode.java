package gt.com.tigo.fixed_assets.dto;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {

    private String id;
    private String parentId;
    private String title;
    private List<TreeNode> children;

    public TreeNode(String id, String title) {
        this.id = id;
        this.title = title;
        this.children = new LinkedList<>();
    }

    public TreeNode(String id, String parentId, String title) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.children = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
