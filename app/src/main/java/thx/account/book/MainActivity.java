package thx.account.book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import thx.account.book.adapter.AccountListAdapter;
import thx.account.book.adapter.GroupListAdapter;
import thx.account.book.common.SpName;
import thx.account.book.databinding.ActivityMainBinding;
import thx.account.book.event.AddAccountEvent;
import thx.account.book.model.AccountBean;
import thx.account.book.model.DataBean;
import thx.account.book.model.GroupBean;
import thx.account.book.model.ItemBean;
import thx.account.book.util.DataUtil;
import thx.account.book.util.GsonUtil;
import thx.account.book.util.SpUtil;
import thx.account.book.util.ToastUtil;

/**
 * @author thkcheng
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    /**
     * 原始数据
     * 不管数据怎么改变 最终都会自己增加到原始数据中
     */
    private DataBean dataBean = null;
    private List<GroupBean> groups;
    private List<AccountBean> accounts;

    private GroupListAdapter groupAdapter;
    private AccountListAdapter currentShowAdapter;

    private Map<Integer, AccountListAdapter> adapterMap = new HashMap<>();

    private int groupIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setSupportActionBar(mBinding.toolbar);

        dataBean = DataUtil.initData();

        initRecyclerView();
    }

    private void initRecyclerView() {
        // dataBean.groupList默认是有值的
        groups = dataBean.groupList;
        mBinding.rvGroups.setLayoutManager(new LinearLayoutManager(this));
        groupAdapter = new GroupListAdapter(R.layout.item_group_list_layout, groups);
        mBinding.rvGroups.setAdapter(groupAdapter);

        // 有多少group就会出现多少个accountAdapter  都是独立互不干扰的
        for (int i = 0; i < groups.size(); i++) {
            adapterMap.put(i, new AccountListAdapter(R.layout.item_content_list_layout));
        }
        // 默认显示的是group中下标为0的accountAdapter
        mBinding.rvContents.setLayoutManager(new LinearLayoutManager(this));
        showAcctountList(0, adapterMap.get(0));

        groupAdapter.setOnItemClickListener((adapter, view, position) -> {
            // 记录每次点击groups的下标
            groupAdapter.setGroupIndex(groupIndex = position);
            showAcctountList(position, adapterMap.get(position));
        });

        currentShowAdapter.setOnItemClickListener((adapter, view, position) -> {
            AccountBean accountBean = accounts.get(position);
            ToastUtil.showToast(accountBean.itemList.toString());
        });
    }

    private void showAcctountList(int groupIndex, AccountListAdapter accountAdapter) {
        currentShowAdapter = adapterMap.get(groupIndex);
        mBinding.rvContents.setAdapter(accountAdapter);
        accounts = groups.get(groupIndex).accountList;
        if (accounts != null && accounts.size() > 0) {
            List<AccountBean> adapterData = currentShowAdapter.getData();
            if (adapterData.size() == 0) {
                currentShowAdapter.addData(accounts);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case R.id.toolbar_add:
                startActivity(new Intent(this, AddAccountActivity.class));
                ToastUtil.showToast("添加");
                break;
            case R.id.toolbar_setting:
                ToastUtil.showToast("设置");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void addAccountEvent(AddAccountEvent event) {
        currentShowAdapter.addData(event.accountBean);
        // 每添加一个账号都在本地保存一次
        groups.get(groupIndex).accountList.add(event.accountBean);
        String json = GsonUtil.toJson(dataBean);
        SpUtil.putString(SpName.SP_ACCOUNT_DATA, json);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
