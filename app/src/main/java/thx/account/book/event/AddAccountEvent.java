package thx.account.book.event;

import thx.account.book.model.AccountBean;

public class AddAccountEvent {

    public AccountBean accountBean;

    public AddAccountEvent(AccountBean accountBean) {
        this.accountBean = accountBean;
    }
}
