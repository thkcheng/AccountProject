package thx.account.book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import thx.account.book.adapter.AccountListAdapter;
import thx.account.book.adapter.GroupListAdapter;
import thx.account.book.adapter.ItemListAdapter;
import thx.account.book.databinding.ActivityAddAccountBinding;
import thx.account.book.event.AddAccountEvent;
import thx.account.book.model.AccountBean;
import thx.account.book.model.ItemBean;
import thx.account.book.util.DataUtil;
import thx.account.book.util.ToastUtil;

public class AddAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddAccountBinding mBinding;

    /**
     * 账号信息列表
     */
    private List<ItemBean> items = new ArrayList<>();

    private ItemListAdapter itemAdapter;

    private ItemBean bean;

    private List<ItemBean> itemBeans = new ArrayList<>();
    private AccountBean accountBean = new AccountBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddAccountBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setSupportActionBar(mBinding.toolbar);
        // toolbar左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true);
        // 设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initRecyclerView();
        setListener();
    }

    private void setListener() {
        mBinding.btnAddItem.setOnClickListener(this);
    }

    private void initRecyclerView() {
        items.addAll(DataUtil.initItemListData());
        mBinding.rvItem.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemListAdapter(R.layout.item_add_account_layout, items);
        mBinding.rvItem.setAdapter(itemAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.toolbar_save:
                for (int i = 0; i < items.size(); i++) {
                    EditText etKey = (EditText) itemAdapter.getViewByPosition(i, R.id.etKey);
                    String key = etKey.getText().toString();
                    if (TextUtils.isEmpty(key)) {
                        ToastUtil.showToast("名称不能为空");
                        return true;
                    }
                    EditText etValue = (EditText) itemAdapter.getViewByPosition(i, R.id.etValue);
                    String value = etValue.getText().toString();
                    if (TextUtils.isEmpty(value)) {
                        ToastUtil.showToast("内容没有可以填无");
                        return true;
                    }

                    switch (i) {
                        case 0:
                            accountBean.name = value;
                            break;
                        case 1:
                            accountBean.account = value;
                            break;
                        default:
                            ItemBean bean = new ItemBean();
                            bean.key = key;
                            bean.value = value;
                            itemBeans.add(bean);
                            break;
                    }
                }
                accountBean.itemList = itemBeans;
                ToastUtil.showToast("保存成功");
                // 传递到MainActivity对于的组下面保存
                EventBus.getDefault().post(new AddAccountEvent(accountBean));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddItem:
                if (bean == null) {
                    bean = new ItemBean();
                }
                if (itemAdapter.getItemPosition(bean) > -1) {
                    // 按位置获取特定视图 例如：列表中最后一个item中id为etKey的EditTextView
                    EditText etKey = (EditText) itemAdapter.getViewByPosition(items.size() - 1, R.id.etKey);
                    EditText etValue = (EditText) itemAdapter.getViewByPosition(items.size() - 1, R.id.etValue);
                    String key = etKey.getText().toString();
                    String value = etValue.getText().toString();
                    if (TextUtils.isEmpty(key) && TextUtils.isEmpty(value)) {
                        ToastUtil.showToast("别急，写完一个再加吧");
                        return;
                    } else {
                        bean = new ItemBean();
                    }
                }
                itemAdapter.addData(bean);
                break;
            default:
                break;
        }
    }
}
