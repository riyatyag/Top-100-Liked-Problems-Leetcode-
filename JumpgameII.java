// Problem Statement:
// You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
// Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
// 0 <= j <= nums[i] and
// i + j < n
// Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

// Approach:
// This problem asks for the minimum number of jumps to reach the last index. This is a classic Breadth-First Search (BFS) or greedy problem. A greedy approach is more efficient.

// We can solve this by thinking in terms of "levels" of jumps, similar to BFS.
// - `jumps`: This variable will count the minimum number of jumps.
// - `currentEnd`: This marks the end of the current jump's reach. All indices from the start of the current jump up to `currentEnd` are reachable within `jumps` steps.
// - `farthest`: This tracks the maximum index that can be reached from any point within the current jump's range (`currentEnd`).

// Algorithm Steps:
// 1. Initialize `jumps = 0`.
// 2. Initialize `farthest = 0`. This is the maximum index reachable using a certain number of jumps (initially 0 jumps, so only index 0 is reachable).
// 3. Initialize `currentEnd = 0`. This marks the end of the current "jump level". When we reach `currentEnd`, it means we have exhausted all options within the current jump level and must make another jump.
// 4. Iterate through the array with index `i` from `0` to `nums.length - 2` (we don't need to process the last element as we want to reach it, not jump from it).
// 5. Inside the loop:
//    a. Update `farthest = Math.max(farthest, i + nums[i])`. This finds the maximum reach from any point `i` within the current jump's scope.
//    b. If `i == currentEnd`: This means we have processed all elements reachable within the current jump level. To potentially reach further, we must make another jump.
//       i. Increment `jumps` by 1.
//       ii. Update `currentEnd = farthest`. This sets the new boundary for the next jump level to the maximum reach achieved from the previous jump level.
// 6. After the loop, `jumps` will hold the minimum number of jumps required.

// Edge Cases:
// - If `nums.length` is 1, we are already at the last index, so 0 jumps are needed. The loop condition `i < nums.length - 1` handles this as the loop won't execute, and it will correctly return 0 (initial `jumps` value).

// Example Walkthrough: nums = [2,3,1,1,4]
// n = 5
// jumps = 0, farthest = 0, currentEnd = 0

// i = 0 (nums[0] = 2):
//   farthest = max(0, 0 + 2) = 2
//   i (0) == currentEnd (0). So, make a jump.
//   jumps = 1
//   currentEnd = farthest (2)

// i = 1 (nums[1] = 3):
//   farthest = max(2, 1 + 3) = 4
//   i (1) != currentEnd (2). Do nothing for jumps.

// i = 2 (nums[2] = 1):
//   farthest = max(4, 2 + 1) = 4
//   i (2) == currentEnd (2). So, make a jump.
//   jumps = 2
//   currentEnd = farthest (4)

// i = 3 (nums[3] = 1):
//   farthest = max(4, 3 + 1) = 4
//   i (3) != currentEnd (4). Do nothing for jumps.

// Loop finishes (i goes up to nums.length - 2 = 3).
// Return jumps = 2.

// Time Complexity:
// O(N), where N is the length of the `nums` array.
// We iterate through the array once. Each operation inside the loop is constant time.

// Space Complexity:
// O(1), as we only use a few constant-space variables.

class Solution {
    public int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0; 
        }

        int jumps = 0;      
        int farthest = 0;   
        int currentEnd = 0; 

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++; 
                currentEnd = farthest; 
            }
        }
           return jumps;
    }
}