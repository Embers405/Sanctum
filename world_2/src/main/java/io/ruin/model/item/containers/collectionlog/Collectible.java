package io.ruin.model.item.containers.collectionlog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class Collectible {
    private int enumId;
    private int slot;
}