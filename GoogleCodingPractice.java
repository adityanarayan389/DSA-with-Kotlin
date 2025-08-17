import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleCodingPractice {
    public static void main(String[] args) {

    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap();

        for (String str : strs) {

            // char[] chars = str.toCharArray();
            // Arrays.sort(chars); // Sort characters
            // String key = new String(chars);

            int[] freq = new int[26];
            for (char c : str.toCharArray()) {
                freq[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int f : freq) {
                sb.append(f).append('#');
            }

            String key = sb.toString();

            if (!map.containsKey(key)) {

                map.put(key, new ArrayList());
            }
            map.get(key).add(str);

        }

        return new ArrayList<>(map.values());

    }

    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }

        for (int i : count) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }

    public static int lengthOfLongestSubstringWithoutRepeating(String s) {

        Map<Character, Integer> map = new HashMap<>();

        int maxlen = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (map.containsKey(ch)) {

                left = Math.max(left, map.get(ch) + 1);
            }

            map.put(ch, right);
            maxlen = Math.max(maxlen, right - left + 1);
        }
        return maxlen;

    }

    public static int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);
        int count = 0;
        int currentSum = 0;

        for (int num : nums) {
            currentSum += num;

            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }

            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);

        }

        return count;
    }

    public static int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] output = new int[n];

        // Left pass: calculate the product of all elements to the left of each element
        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            output[i] = leftProduct;
            leftProduct *= nums[i];
        }
        System.out.println(Arrays.toString(output));

        // Right pass: calculate the product of all elements to the right of each
        // element
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            output[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return output;

    }

    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> keymap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (keymap.containsKey(nums[i])) {
                return true;

            }

            keymap.put(nums[i], i);
        }
        return false;

    }

    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length())
            return false;

        int[] need = new int[26];
        int[] window = new int[26];

        for (char c : s1.toCharArray()) {
            need[c - 'a']++;
        }

        int left = 0, matchCount = 0;

        for (int right = 0; right < s2.length(); right++) {

            int idx = s2.charAt(right) - 'a';
            window[idx]++;

            if (window[idx] == need[idx]) {
                matchCount++;
            }

            while (window[idx] > need[idx] && left <= right) {
                int leftIdx = s2.charAt(left) - 'a';
                if (window[leftIdx] == need[leftIdx]) {
                    matchCount--;
                }
                window[leftIdx]--;
                left++;

            }

            int needcount = 0;
            for (int n : need) {
                if (n != 0) {
                    needcount++;
                }
            }

            if (matchCount == needcount) {
                return true;
            }

        }
        return false;

    }

    public boolean checbkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        int[] need = new int[26];
        int[] window = new int[26];

        // Count chars in s1
        for (char c : s1.toCharArray()) {
            need[c - 'a']++;
        }

        int left = 0, right = 0;
        while (right < s2.length()) {
            // Add new char into the window
            window[s2.charAt(right) - 'a']++;
            right++;

            // Keep window size equal to s1.length()
            if (right - left > s1.length()) {
                window[s2.charAt(left) - 'a']--;
                left++;
            }

            // Compare arrays
            if (matches(need, window)) {
                return true;
            }
        }
        return false;
    }

    private boolean matches(int[] need, int[] window) {
        for (int i = 0; i < 26; i++) {
            if (need[i] != window[i])
                return false;
        }
        return true;
    }

    // Find All Anagrams in a String

    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList();

        if (p.length() > s.length())
            return result;

        int[] need = new int[26];
        int[] window = new int[26];

        for (char c : p.toCharArray()) {
            need[c - 'a']++;
        }

        int right = 0;
        int left = 0;
        int matchcount = 0;

        while (right < s.length()) {
            int idx = s.charAt(right) - 'a';
            window[idx]++;
            right++;

            if (window[idx] == need[idx]) {
                matchcount++;
            }

            while (window[idx] > need[idx] && left <= right) {
                int leftIdx = s.charAt(left) - 'a';
                if (window[leftIdx] == need[leftIdx]) {
                    matchcount--;
                }
                window[leftIdx]--;
                left++;
            }

            int needcount = 0;
            for (int i : need) {
                if (i != 0) {
                    needcount++;
                }
            }

            if (matchcount == needcount) {
                result.add(left);
            }

        }

        return result;

    }

    // Maximum Average Subarray I

    public double findMaxAverage(int[] nums, int k) {
        int mawindowsum = 0;
        for (int i = 0; i < k; i++) {
            mawindowsum += nums[i];
        }
        double averagesum = mawindowsum;

        for (int i = k; i < nums.length; i++) {

            mawindowsum = mawindowsum + nums[i] - nums[i - k];
            averagesum = Math.max(averagesum, mawindowsum);
        }

        return averagesum / k;
    }

    // Longest Repeating Character Replacement

    public int characterReplacement(String s, int k) {

        int[] count = new int[26];
        int maxfreq = 0;
        int result = 0;
        int right = 0;
        int left = 0;

        while (right < s.length()) {
            count[s.charAt(right) - 'A']++;
            maxfreq = Math.max(maxfreq, count[s.charAt(right) - 'A']);

            while ((right - left + 1) - maxfreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            result = Math.max(result, right - left + 1);

            right++;

        }
        return result;

    }

    // Fruit Into Baskets

    public int totalFruit(int[] fruits) {

        HashMap<Integer, Integer> countmap = new HashMap();
        int left = 0;
        int right = 0;
        int maxlen = 0;

        while (right < fruits.length) {
            countmap.put(fruits[right], countmap.getOrDefault(fruits[right], 0) + 1);
            while (countmap.size() > 2) {
                countmap.put(fruits[left], countmap.get(fruits[left]) - 1);

                if (countmap.get(fruits[left]) == 0) {
                    countmap.remove(fruits[left]);
                }
                left++;

            }

            maxlen = Math.max(maxlen, right - left + 1);
            right++;

        }

        return maxlen;

    }

    // Max Consecutive Ones III

    public int longestOnes(int[] nums, int k) {

        int left = 0, right = 0;
        int zeroCount = 0;
        int maxLen = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;

    }
    // Minimum Window Substring

    public String minWindow(String s, String t) {

        if (s.length() < t.length())
            return "";

        int[] need = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        int[] window = new int[128];
        int required = 0;
        for (int val : need) {
            if (val > 0)
                required++;
        }

        int left = 0, right = 0, matchCount = 0;
        int minLen = Integer.MAX_VALUE, startIdx = 0;

        while (right < s.length()) {
            char cRight = s.charAt(right);
            window[cRight]++;

            if (window[cRight] == need[cRight]) {
                matchCount++;
            }

            while (matchCount == required) {
                // Update answer
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIdx = left;
                }

                char cLeft = s.charAt(left);
                window[cLeft]--;
                if (window[cLeft] < need[cLeft]) {
                    matchCount--;
                }
                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLen);

    }
    // Trapping Rain Water

    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }

    // binarySearch algo

    public int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid; // found
            } else if (nums[mid] < target) {
                low = mid + 1; // search right half
            } else {
                high = mid - 1; // search left half
            }
        }
        return -1; // not found
    }

    // . Search in Rotated Sorted Array
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid; // found
            }

            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1; // target in left half
                } else {
                    low = mid + 1; // target in right half
                }
            }
            // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1; // target in right half
                } else {
                    high = mid - 1; // target in left half
                }
            }
        }
        return -1; // not found
    }

    // Find Minimum in Rotated Sorted Array

    public int findMin(int[] nums) {

        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            }

            else {
                high = mid;
            }
        }
        return nums[low];

    }

    // Peak Index in a Mountain Array

    public int peakIndexInMountainArray(int[] arr) {

        int low = 0, high = arr.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] < arr[mid + 1]) {
                // Increasing slope → move right
                low = mid + 1;
            } else {
                // Decreasing slope → move left (mid can still be peak)
                high = mid;
            }

        }
        return low;

    }

}
