import java.util.Random;

public class QuickSortAlgo {


    public static void main(String[] args) {
        
    }

    public void quickSort(int[] arr, int start, int end){
       
        if (start< end) {
            int pidx = partition(arr, start, end);

            quickSort(arr, start, pidx -1);
            quickSort(arr, pidx+1, end);
        }



    }

    private int partition(int[] arr, int start, int end) {
       int pivot = arr[end];
       int idx = start-1;
       for(int j = start; j<end;j++){

        if (arr[j] < pivot) {
            idx++;
            int temp = arr[j];
            arr[j]= arr[idx];
            arr[idx] = temp;
            
        }
    }

        idx++;
        int temp = arr[idx];
        arr[idx] = pivot;
        arr[end]= temp;

        return idx;



       
    }




//kth largest element


 public static int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums, 0, n - 1, n - k);
    }

    private static int quickSelect(int[] nums, int left, int right, int kSmallest) {
        if (left == right) return nums[left];

        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);

        pivotIndex = partition(nums, left, right, pivotIndex);

        if (pivotIndex == kSmallest) {
            return nums[pivotIndex];
        } else if (pivotIndex < kSmallest) {
            return quickSelect(nums, pivotIndex + 1, right, kSmallest);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, kSmallest);
        }
    }

    private static int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
