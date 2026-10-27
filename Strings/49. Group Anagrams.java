/*
 * LeetCode: 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 *
 * Topic: Arrays / Strings / HashMap / Counting
 *
 *
 * ============================================================
 * APPROACH 1: Character Frequency Signature
 * ============================================================
 *
 * Intuition:
 * - Anagrams are strings containing the same characters
 *   with the same frequencies, but possibly in different orders.
 *
 * - For example, "eat", "tea", and "ate" are anagrams because
 *   each contains e, a, and t exactly once.
 *
 * - We generate a common signature for every string using
 *   a frequency array of size 26.
 *
 * - For each character, increment its corresponding frequency:
 *
 *       count[ch - 'a']++
 *
 * - Traverse the frequency array and reconstruct a canonical
 *   string by appending each character according to its count.
 *
 * - All anagrams produce the same canonical string.
 *
 * - Use a HashMap where:
 *
 *       Key   -> Canonical frequency-based string
 *       Value -> List of strings belonging to that group
 *
 * - For each input string, generate its signature and add
 *   the string to the corresponding group.
 *
 * - Finally, return all grouped lists.
 *
 *
 * Example:
 *
 *      strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 *
 *      Signatures:
 *      "eat" -> "aet"
 *      "tea" -> "aet"
 *      "tan" -> "ant"
 *      "ate" -> "aet"
 *      "nat" -> "ant"
 *      "bat" -> "abt"
 *
 *      Answer:
 *      [
 *          ["eat", "tea", "ate"],
 *          ["tan", "nat"],
 *          ["bat"]
 *      ]
 *
 *
 * Time Complexity: O(N * L)
 * Space Complexity: O(N * L)
 *
 * Where:
 * - N = Number of strings
 * - L = Maximum length of a string
 *
 * Why O(N * L)?
 * - Generating the frequency signature takes O(L) per string.
 * - HashMap grouping takes O(1) average time per insertion,
 *   excluding the cost of generating the signature.
 *
 * Space:
 * - The signatures and grouped strings require O(N * L)
 *   space in total.
 * - The frequency array uses O(1) auxiliary space per call.
 */


class Solution {

    // Generate a canonical signature using character frequencies
    private String generate(String s) {

        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        StringBuilder newS = new StringBuilder();

        // Reconstruct the string in alphabetical order
        for (int i = 0; i < 26; i++) {

            if (count[i] > 0) {
                newS.append(
                    String.valueOf((char) (i + 'a')).repeat(count[i])
                );
            }
        }

        return newS.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {

            // Generate the common signature for anagrams
            String newS = generate(s);

            // Create a group if this signature is new
            if (!map.containsKey(newS)) {
                map.put(newS, new ArrayList<>());
            }

            // Add the original string to its group
            map.get(newS).add(s);
        }

        // Return all anagram groups
        return new ArrayList<>(map.values());
    }
}


/*
 * ============================================================
 * APPROACH 2: Sorting Each String
 * ============================================================
 *
 * Intuition:
 * - Two strings are anagrams if sorting their characters
 *   produces the same string.
 *
 * - Convert each string into a character array and sort it.
 *
 * - Use the sorted string as the HashMap key.
 *
 * - Add the original string to the list associated with
 *   that key.
 *
 * - Return all the groups stored in the map.
 *
 *
 * Example:
 *
 *      "eat" -> "aet"
 *      "tea" -> "aet"
 *      "ate" -> "aet"
 *
 *      All three strings belong to the same group.
 *
 *
 * Time Complexity: O(N * L log L)
 * Space Complexity: O(N * L)
 *
 * Why O(N * L log L)?
 * - Sorting each string of length L takes O(L log L).
 * - This operation is performed for all N strings.
 *
 * Space:
 * - The map stores the grouped strings and their keys.
 */


class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            String s = sortingString(str);

            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(str);
        }

        return new ArrayList<>(map.values());
    }

    String sortingString(String str) {

        char[] ch = str.toCharArray();
        Arrays.sort(ch);

        return new String(ch);
    }
}
