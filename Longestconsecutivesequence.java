// Problem Statement:
// Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
// You must write an algorithm that runs in O(n) time.

// Approach:
// The problem asks for the longest consecutive elements sequence in an unsorted array, with an O(N) time complexity constraint.
// A naive approach would be to sort the array (O(N log N)) and then iterate to find the longest sequence. However, we need an O(N) solution.

// We can use a HashSet for efficient lookups (average O(1) time complexity).
// 1. First, insert all elements of the input array `nums` into a HashSet. This allows for O(1) average time complexity checking if a number exists. This step takes O(N) time.
// 2. Initialize `longestStreak = 0`.
// 3. Iterate through each `num` in the `nums` array (or equivalently, iterate through the elements in the HashSet, which is generally better for handling duplicates if they were present).
// 4. For each `num`, check if `num - 1` exists in the HashSet.
//    - If `num - 1` does NOT exist, it means `num` is the potential start of a new consecutive sequence.
//    - If `num - 1` DOES exist, then `num` is part of a longer sequence that starts at `num - 1` or earlier. In this case, we can skip `num` because we will process this sequence when we iterate to its starting element. This optimization is crucial for achieving O(N) overall.
// 5. If `num` is a potential start of a sequence (i.e., `num - 1` is not in the set):
//    a. Initialize `currentNum = num` and `currentStreak = 1`.
//    b. While `currentNum + 1` exists in the HashSet:
//       i. Increment `currentNum` by 1.
//       ii. Increment `currentStreak` by 1.
//    c. Update `longestStreak = Math.max(longestStreak, currentStreak)`.
// 6. After iterating through all numbers, `longestStreak` will hold the length of the longest consecutive elements sequence.

// Why this is O(N):
// - Adding all elements to the HashSet takes O(N) time.
// - We iterate through the `nums` array once. For each number, we do an O(1) average time lookup (`contains`).
// - The inner `while` loop (`currentNum + 1` check) effectively processes each number at most twice: once when it's initially added to the set and once when it's part of extending a sequence. Crucially, a number is only part of the `while` loop if it's found as `currentNum + 1`. This means each number is visited by the inner `while` loop at most once as `currentNum + 1`.
// - Therefore, the total work for checking and extending sequences is proportional to the total number of elements, making the overall time complexity O(N).

// Time Complexity:
// O(N), where N is the number of elements in the `nums` array.
// - Populating the HashSet: O(N)
// - Iterating through numbers and extending sequences: Each number is processed a constant number of times (added to set, checked as a starting point, and checked as a successor). So, this part is also O(N).

// Space Complexity:
// O(N) to store all unique elements in the HashSet in the worst case (all elements are unique).

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : nums) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}