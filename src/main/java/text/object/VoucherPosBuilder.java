package text.object;

import data.entity.accountingType.AccountingTypeRequest;
import data.entity.voucher.VoucherPosSave;

public class VoucherPosBuilder {


    public Object build(float max) {
        AccountingTypeRequest accountingTypeRequest = new AccountingTypeRequest(26);
        int taxRate = 19;
        boolean net = false;
        float netSum = (float) (max / 1.19);
        VoucherPosSave voucherPosSaveRequest = new VoucherPosSave(accountingTypeRequest, taxRate, net, netSum, max);
        return voucherPosSaveRequest;
    }
}
