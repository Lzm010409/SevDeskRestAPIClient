package text.object;

import data.entity.accountingType.AccountingTypeRequest;
import data.entity.voucher.VoucherPosSaveRequest;

public class VoucherPosBuilder {


    public Object build(float max) {
        AccountingTypeRequest accountingTypeRequest = new AccountingTypeRequest(2);
        int taxRate = 19;
        boolean net = false;
        float netSum = (float) (max / 1.19);
        VoucherPosSaveRequest voucherPosSaveRequest = new VoucherPosSaveRequest(accountingTypeRequest, taxRate, net, netSum, max);
        return voucherPosSaveRequest;
    }
}
