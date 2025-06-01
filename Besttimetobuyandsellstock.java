// Problem Statement:
// You are given an array prices where prices[i] is the price of a given stock on the ith day.
// You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
// Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

// Approach:
// This problem asks us to find the maximum profit by buying a stock on one day and selling it on a future day.
// We need to find the minimum buying price and the maximum selling price that occurs after the buying price.

// We can solve this with a single pass through the array.
// 1. Initialize `minPrice` to a very large value (e.g., `Integer.MAX_VALUE`). This variable will keep track of the minimum price encountered so far.
// 2. Initialize `maxProfit` to 0. This variable will store the maximum profit found.
// 3. Iterate through the `prices` array from left to right.
// 4. For each `price` on the current day:
//    a. If `price` is less than `minPrice`, it means we found a new lower buying price. Update `minPrice = price`.
//    b. If `price` is greater than or equal to `minPrice`, it means we can potentially sell on this day. Calculate the potential profit: `currentProfit = price - minPrice`.
//    c. Update `maxProfit = Math.max(maxProfit, currentProfit)`.
// 5. After iterating through all prices, `maxProfit` will hold the maximum profit achievable.

// This approach works because by iterating from left to right, when we consider selling on a particular day, `minPrice` will always represent the lowest price from a previous day (or the current day itself if it's the new minimum), ensuring that we are buying before selling.

// Time Complexity:
// O(N), where N is the number of days (length of the `prices` array).
// We iterate through the array only once, performing constant-time operations in each iteration.

// Space Complexity:
// O(1), as we only use a few constant-space variables (`minPrice`, `maxProfit`, `i`).

class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        return maxProfit;
    }
}