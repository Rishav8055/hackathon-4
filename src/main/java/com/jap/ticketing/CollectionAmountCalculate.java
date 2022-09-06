package com.jap.ticketing;

import java.util.List;
@FunctionalInterface

public interface CollectionAmountCalculate {
    public double CollectionAmount(List<Ticket> ticket);
}
