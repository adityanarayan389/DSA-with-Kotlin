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

    public static int lengthOfLongestSubstring(String s) {

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
    

     public  static int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap();  
         map.put(0, 1);
         int count = 0;
         int currentSum = 0;

         for(int num : nums) {
            currentSum += num;

            if(map.containsKey(currentSum - k)){
                count += map.get(currentSum - k);
            }

            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);

        
         } 

         return count;    
    }


    public  static int[] productExceptSelf(int[] nums) {

   

    int n = nums.length;
    int[] output = new int[n];
    
    // Left pass: calculate the product of all elements to the left of each element
    int leftProduct = 1;
    for (int i = 0; i < n; i++) {
        output[i] = leftProduct;
        leftProduct *= nums[i];
    }
    System.out.println(Arrays.toString(output));
    
    // Right pass: calculate the product of all elements to the right of each element
    int rightProduct = 1;
    for (int i = n - 1; i >= 0; i--) {
        output[i] *= rightProduct;
        rightProduct *= nums[i];
    }
   
    
    return output;
        
}

 public boolean containsDuplicate(int[] nums) {
        HashMap<Integer,Integer> keymap = new HashMap<>();
        for(int i=0; i< nums.length; i++){
            if (keymap.containsKey(nums[i])) {
                return true;
        
            }

            keymap.put(nums[i], i);
        }
        return false;

    }
      


}
