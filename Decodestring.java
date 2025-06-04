// Problem Statement:
// Given an encoded string, return its decoded string.
// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
// You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
// The test cases are generated so that the length of the output will never exceed 10^5.

// Approach:
// This problem can be solved using two stacks: one to store the repetition counts (integers) and another to store the string segments accumulated before encountering a '['.
// We iterate through the input string character by character.
// 1. If the character is a digit, we accumulate it to form the complete number 'k'.
// 2. If the character is '[', it signifies the start of a new encoded segment. We push the current 'k' onto the countStack and the 'currentString' (the string built so far before this segment) onto the stringStack. Then, we reset 'currentString' to an empty StringBuilder and 'k' to 0 to start building the new inner segment.
// 3. If the character is ']', it signifies the end of an encoded segment. We pop the repetition count from countStack and the previous string segment from stringStack. We then append the 'currentString' (which represents the decoded segment inside the brackets) to itself 'k' times. Finally, we append this repeated string to the 'previousString' popped from the stack and update 'currentString' with this combined result.
// 4. If the character is a letter, we simply append it to the 'currentString'.
// After iterating through all characters, the 'currentString' will contain the fully decoded string.

// Time Complexity:
// O(L), where L is the total length of the decoded string. Although we iterate through the input string once (O(N), where N is input length), the operations of appending strings when a ']' is encountered can take time proportional to the length of the string being built. Since the maximum length of the output string is constrained to 10^5, each character in the final decoded string is effectively processed a constant number of times (appended).

// Space Complexity:
// O(N + L), where N is the maximum depth of recursion (or stack size, which can be up to the length of the input string in case of deep nesting) and L is the length of the final decoded string. The space is used by the two stacks and the StringBuilder for constructing the current string. In the worst case, the stack depth can be N, and the StringBuilder can hold up to L characters.

// Optimal Solution:
// The stack-based approach is optimal for this problem. It processes the input string in a single pass and efficiently handles nested structures and repetitions. The time complexity is directly related to the length of the output, which is the minimum required work to generate the result.
import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                countStack.push(k);
                stringStack.push(currentString);
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                int repeatTimes = countStack.pop();
                StringBuilder decodedSegment = new StringBuilder();
                for (int i = 0; i < repeatTimes; i++) {
                    decodedSegment.append(currentString);
                }
                currentString = stringStack.pop().append(decodedSegment);
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}
