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



    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums, 0, n - 1, n - k);
    }

    private int quickSelect(int[] arr, int left, int right, int targetIdx) {
        if (left == right) {
            return arr[left];
        }

        // Randomized pivot to avoid worst case
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);

        pivotIndex = partition(arr, left, right, pivotIndex);

        if (pivotIndex == targetIdx) {
            return arr[pivotIndex];
        } else if (pivotIndex < targetIdx) {
            return quickSelect(arr, pivotIndex + 1, right, targetIdx);
        } else {
            return quickSelect(arr, left, pivotIndex - 1, targetIdx);
        }
    }

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivot = arr[pivotIndex];

        // Move pivot to end
        swap(arr, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }

        // Move pivot to its final place
        swap(arr, storeIndex, right);

        return storeIndex;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



}