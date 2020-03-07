package thx.account.book.model;

import java.util.List;

public class GroupBean {

    public String name;

    public List<AccountBean> accountList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountBean> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountBean> accountList) {
        this.accountList = accountList;
    }
}
