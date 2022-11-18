package data.entity.other;

import com.google.gson.annotations.Expose;
import data.entity.contact.Contact;

public class Objects {

    @Expose(serialize = false, deserialize = true)
    private Contact contact;

    public Objects(){

    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
