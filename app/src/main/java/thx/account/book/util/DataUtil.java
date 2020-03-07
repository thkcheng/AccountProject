package thx.account.book.util;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import thx.account.book.common.SpName;
import thx.account.book.model.DataBean;
import thx.account.book.model.GroupBean;
import thx.account.book.model.ItemBean;

public class DataUtil {

    public static DataBean initData() {

        String jsonDefault = "{\n" +
                "    \"groupList\":[\n" +
                "        {\n" +
                "            \"accountList\":[\n" +
                "               \n" +
                "            ],\n" +
                "            \"name\":\"默认\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"accountList\":[\n" +
                "\n" +
                "            ],\n" +
                "            \"name\":\"工作\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"accountList\":[\n" +
                "\n" +
                "            ],\n" +
                "            \"name\":\"游戏\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"version\":1\n" +
                "}";

        String jsonSP = SpUtil.getString(SpName.SP_ACCOUNT_DATA);
        Log.e("xxx", jsonSP);
        DataBean dataBean = GsonUtil.toObject(!TextUtils.isEmpty(jsonSP) ? jsonSP : jsonDefault, DataBean.class);

//        List<GroupBean> groupList = new ArrayList<>();
//        GroupBean groupBean1 = new GroupBean();
//        groupBean1.name = "默认";
//        GroupBean groupBean2 = new GroupBean();
//        groupBean2.name = "工作";
//        GroupBean groupBean3 = new GroupBean();
//        groupBean3.name = "游戏";
//        groupList.add(groupBean1);
//        groupList.add(groupBean2);
//        groupList.add(groupBean3);
//
//        DataBean databean = new DataBean();
//        databean.version = 1;
//        databean.groupList = groupList;

        return dataBean;
    }

    public static List<GroupBean> initGroupListData() {
        List<GroupBean> groups = new ArrayList<>();
        GroupBean bean1 = new GroupBean();
        bean1.name = "默认";
        GroupBean bean2 = new GroupBean();
        bean2.name = "工作";
        GroupBean bean3 = new GroupBean();
        bean3.name = "游戏";
        groups.add(bean1);
        groups.add(bean2);
        groups.add(bean3);
        return groups;
    }

    public static List<ItemBean> initItemListData() {
        List<ItemBean> itemBeans = new ArrayList<>();
        ItemBean bean1 = new ItemBean();
        bean1.key = "名称";
        ItemBean bean2 = new ItemBean();
        bean2.key = "账号";
        ItemBean bean3 = new ItemBean();
        bean3.key = "密码";
        itemBeans.add(bean1);
        itemBeans.add(bean2);
        itemBeans.add(bean3);
        return itemBeans;
    }

}
