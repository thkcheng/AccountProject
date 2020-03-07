package thx.account.book.model;

import java.util.List;

public class AccountBean {

    public String account;

    public String name;

    public List<ItemBean> itemList;

    public int top;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemBean> itemList) {
        this.itemList = itemList;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", itemList=" + itemList +
                ", top=" + top +
                '}';
    }
}
