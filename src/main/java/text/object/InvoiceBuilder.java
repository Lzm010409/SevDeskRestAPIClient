package text.object;

import data.entity.voucher.Voucher;

import java.util.List;

public class InvoiceBuilder implements ObjectBuilder{


    @Override
    public Object build(List<String> e) {
        Voucher returnVoucher= new Voucher(100, "D", "VOU", null);
        returnVoucher.setVoucherDate(e.get(0));
        returnVoucher.setDeliveryDate(e.get(0));
        returnVoucher.setDescritption(e.get(1));
        return returnVoucher;
    }
}
