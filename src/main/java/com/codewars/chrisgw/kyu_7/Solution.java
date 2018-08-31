package com.codewars.chrisgw.kyu_7;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Solution {


    public static int solveSuperMarketQueue(int[] customers, int n) {
        List<CheckoutTill> checkoutTills = Stream.generate(CheckoutTill::new)
                .limit(n)
                .collect(Collectors.toList());

        Comparator<CheckoutTill> checkoutWaitingTimeComparator = CheckoutTill.checkoutWaitingTimeComparator();
        for (int customerCheckoutTime : customers) {
            checkoutTills.stream()
                    .min(checkoutWaitingTimeComparator)
                    .ifPresent(checkoutTill -> checkoutTill.addCustomer(customerCheckoutTime));
        }
        return checkoutTills.stream()
                .max(checkoutWaitingTimeComparator)
                .map(CheckoutTill::getCheckoutWaitingTime)
                .orElse(0);
    }


    public static class CheckoutTill {

        private int checkoutWaitingTime = 0;


        public int getCheckoutWaitingTime() {
            return checkoutWaitingTime;
        }

        public void addCustomer(int customerCheckoutWaitingTime) {
            checkoutWaitingTime += customerCheckoutWaitingTime;
        }


        public static Comparator<CheckoutTill> checkoutWaitingTimeComparator() {
            return Comparator.comparingInt(CheckoutTill::getCheckoutWaitingTime);
        }

    }


}
