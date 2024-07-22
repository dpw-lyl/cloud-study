package com.dpw.lyl.join.good.job.domian;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
public class PayOrder implements Comparable<PayOrder>, Serializable {

    private long creationTime;

    private final Type type;

    private final int orderId;

   public   enum Type { SPECIAL, URGENT, NORMAL }
    @Override
    public int compareTo(PayOrder other) {
        int typeComparison = this.type.compareTo(other.type);
        if (typeComparison != 0) {
            return typeComparison;
        } else {
            // If types are the same, compare by creation time (newer orders come last)
            return Long.compare(other.creationTime, this.creationTime);
        }
    }
}
