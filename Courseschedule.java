// Problem Statement:
// There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
// For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
// Return true if you can finish all courses. Otherwise, return false.

// Approach:
// This problem can be modeled as a directed graph where courses are nodes and prerequisites are directed edges.
// If there is a cycle in the graph, it means there's a circular dependency, and it's impossible to finish all courses.
// We can use Kahn's algorithm (BFS-based topological sort) or DFS to detect cycles.

// Approach 1: Kahn's Algorithm (BFS)
// 1. Build an adjacency list (graph) where graph[i] contains all courses that have 'i' as a prerequisite.
// 2. Calculate the in-degree for each course (number of prerequisites for that course).
// 3. Initialize a queue and add all courses with an in-degree of 0 (no prerequisites).
// 4. While the queue is not empty:
//    a. Dequeue a course.
//    b. Increment a counter for finished courses.
//    c. For each neighbor (course that depends on the dequeued course):
//       i. Decrement its in-degree.
//       ii. If its in-degree becomes 0, enqueue it.
// 5. If the counter for finished courses equals numCourses, it means all courses can be finished, so return true. Otherwise, return false.

// Time Complexity:
// O(V + E) where V is numCourses and E is the number of prerequisites.
// Building the graph and in-degree array takes O(V + E).
// The BFS traversal visits each vertex and edge once.

// Space Complexity:
// O(V + E) for storing the adjacency list and in-degree array.

// Optimal Solution:
import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];
            graph.get(prerequisite).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int coursesFinished = 0;
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            coursesFinished++;

            for (int neighbor : graph.get(currentCourse)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return coursesFinished == numCourses;
    }
}