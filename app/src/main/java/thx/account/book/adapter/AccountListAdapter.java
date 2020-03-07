package thx.account.book.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import thx.account.book.R;
import thx.account.book.model.AccountBean;

public class AccountListAdapter extends BaseQuickAdapter<AccountBean, BaseViewHolder> {

    public AccountListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder holder, AccountBean accountBean) {
        holder.setText(R.id.tvContentName, accountBean.name);
        holder.setText(R.id.tvContentAccount, accountBean.account);
    }
}
