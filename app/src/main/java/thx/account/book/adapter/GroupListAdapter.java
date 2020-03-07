package thx.account.book.adapter;

import android.graphics.Typeface;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import thx.account.book.R;
import thx.account.book.model.GroupBean;

public class GroupListAdapter extends BaseQuickAdapter<GroupBean, BaseViewHolder> {

    private int groupIdenx = 0;

    public void setGroupIndex(int groupIdenx) {
        this.groupIdenx = groupIdenx;
        notifyDataSetChanged();
    }

    public GroupListAdapter(int layoutResId, List<GroupBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, GroupBean groupBean) {
        TextView tvGroupName = holder.getView(R.id.tvGroupName);
        tvGroupName.setText(groupBean.name);
        if (groupIdenx == getItemPosition(groupBean)) {
            tvGroupName.setTextColor(getContext().getResources().getColor(R.color.colorMain));
            tvGroupName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            tvGroupName.setTextColor(getContext().getResources().getColor(R.color.colorGray));
            tvGroupName.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
    }
}
