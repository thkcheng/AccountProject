package thx.account.book.model;

import java.util.List;

public class DataBean {

    public List<GroupBean> groupList;

    public int version;

    public List<GroupBean> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupBean> groupList) {
        this.groupList = groupList;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
