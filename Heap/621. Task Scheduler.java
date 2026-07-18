/*
 * LeetCode: 621. Task Scheduler
 * https://leetcode.com/problems/task-scheduler/
 *
 * ------------------------------------------------------------
 * Approach: Max Heap + Greedy Simulation
 * ------------------------------------------------------------
 * Intuition:
 * - Count the frequency of each task.
 * - Store the frequencies in a max heap so the most frequent task
 *   is always scheduled first.
 * - In each cycle of length (n + 1), execute up to (n + 1) different
 *   tasks with the highest remaining frequencies.
 * - After executing a task, decrease its remaining count and insert it
 *   back into the heap if it still has pending occurrences.
 * - If the heap becomes empty before completing the cycle, the remaining
 *   time slots are idle unless all tasks have already been processed.
 *
 * Time Complexity: O(T log 26)
 * Space Complexity: O(26)
 *
 * where:
 * T = Number of tasks.
 *
 * Explanation:
 * - Building the frequency array takes O(T).
 * - The heap contains at most 26 task types.
 * - Each heap operation costs O(log 26), which is effectively constant.
 * - Every task is inserted into and removed from the heap at most once
 *   per occurrence.
 *
 */
class Solution {
    int[] freq = new int[26];
    public int leastInterval(char[] tasks, int n) {
        for(char c: tasks) freq[c-'A']++;

        int time = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);

        for(int i = 0;i<26;i++){
            if(freq[i]>0) pq.offer(freq[i]);
        }

        while(!pq.isEmpty()){
            int count=0;
            List<Integer> temp = new ArrayList<>();
            for(int i = 0;i<=n && !pq.isEmpty();i++){
                int freq = pq.poll();
                count++;
                if(freq>1) temp.add(freq-1);
            }

            for(int f:temp) if(f>0) pq.offer(f);

            time += pq.isEmpty()?count:n+1;
        }

        return time;
    }
}
/*
 * Alternative Approach:
 * 1. Greedy Mathematical Formula
 *    - Let maxFreq be the highest task frequency and maxCount be the
 *      number of tasks having that frequency.
 *    - The minimum intervals required are:
 *
 *      max(T, (maxFreq - 1) * (n + 1) + maxCount)
 *
 *    - Time Complexity: O(T)
 *    - Space Complexity: O(26)
 */
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] frequencies = new int[26];

        for (char task : tasks) {
            frequencies[task - 'A']++;
        }

        int maxFrequency = 0;

        for (int frequency : frequencies) {
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        int maxFrequencyCount = 0;

        for (int frequency : frequencies) {
            if (frequency == maxFrequency) {
                maxFrequencyCount++;
            }
        }

        int frameLength =
                (maxFrequency - 1) * (n + 1)
                + maxFrequencyCount;

        return Math.max(tasks.length, frameLength);
    }
}
