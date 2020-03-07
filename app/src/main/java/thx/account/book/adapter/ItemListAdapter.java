package thx.account.book.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import thx.account.book.R;
import thx.account.book.model.ItemBean;

public class ItemListAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {

    public ItemListAdapter(int layoutResId, List<ItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ItemBean itemBean) {
        EditText etKey = holder.getView(R.id.etKey);
        etKey.setText(itemBean.key);

        if (!TextUtils.isEmpty(itemBean.value)) {
            holder.setText(R.id.etValue, itemBean.value);
        }

        /**
         * 名称和账号不能更改
         */
        int position = getItemPosition(itemBean);
        if (position <= 1) {
            etKey.clearFocus();
            etKey.setFocusable(false);
            etKey.setFocusableInTouchMode(false);
        } else {
            etKey.setFocusable(true);
            etKey.setFocusableInTouchMode(true);
        }
    }
}
