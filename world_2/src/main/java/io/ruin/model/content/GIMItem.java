package io.ruin.model.content;

import com.google.gson.annotations.Expose;
import io.ruin.model.item.Item;

import java.util.Map;

public class GIMItem extends Item {


    protected int sortSlot = -1;

    public GIMItem(int id, int amount, Map<String, String> attibutes) {
        super(id, amount, attibutes);
    }

    @Override
    public GIMItem copy() {
        return new GIMItem(getId(), getAmount(), copyOfAttributes());
    }

    public void toBlank() {
        setId(GIMStorage.BLANK_ID);
        setAmount(0);
    }


}
