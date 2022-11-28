package text.object;

import data.entity.voucher.Voucher;
import text.parser.DateParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InvoiceBuilder {


    public Object build(List<String> e) {
        Voucher returnVoucher = new Voucher(50, "D", "VOU", null);
        Calendar calendar= new DateParser().parseDate(e.get(0));
        Date voucherDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 14);

        Date paymentDate = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


        returnVoucher.setVoucherDate(dateFormat.format(voucherDate));
        returnVoucher.setDeliveryDate(dateFormat.format(voucherDate));
        returnVoucher.setPaymentDeadline(dateFormat.format(paymentDate));
        returnVoucher.setDescritption((String) e.get(1));
        return returnVoucher;
    }
}
