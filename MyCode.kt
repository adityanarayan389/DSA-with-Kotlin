fun main() {

    val nums = intArrayOf(2, 7, 11, 15)
    val target = 9
    val result = twoSum(nums, target)
    print(maxProfit(nums))
}

fun twoSum(array: IntArray, target: Int): IntArray {

    val map = mutableMapOf<Int, Int>()

    for ((index, num) in array.withIndex()) {
        val key = target - num

        if (map.contains(key)) {
            return intArrayOf(map[key]!!, index)
        }
        map[num] = index
    }
    throw IllegalArgumentException("No two sum solution")
}

fun maxProfit(prices: IntArray): Int {
    var maxProfit = 0
    var minPrice = Int.MAX_VALUE
    for (currentPrice in prices) {

        if (currentPrice < minPrice) {
            minPrice = currentPrice
        } else {
            val profit = currentPrice - minPrice
            maxProfit = maxOf(maxProfit, profit)
        }
    }

    return maxProfit
}

fun moveZeroes(nums: IntArray): Unit {

    var count = 0

    for (n in nums) {
        if (n != 0) {
            nums[0] = n
            count++
        }
    }

    for (i in count until nums.size) {
        nums[i] = 0
    }
}

 fun findDuplicate(nums: IntArray): Int {
      
      val unique = mutableSetOf<Int>()

        for (num in nums){
            if(num in unique ) return num
            unique.add(num)
        }
        
        return -1
    }


    fun  maxSubArray(nums:IntArray) : Int{
      var currentsum = nums[0]
      var largestSum = nums[0]
      
      for(i in 1 until nums.size){
          
          currentsum = maxOf(nums[i], currentsum + nums[i])
          largestSum = maxOf(largestSum, currentsum)
      }
      return largestSum
      
  }

  fun productExceptSelf(nums: IntArray): IntArray {
    val n = nums.size
    val result = IntArray(n) { 1 }

    // Step 1: Left products
    var left = 1
    for (i in 0 until n) {
        result[i] = left
        left *= nums[i]
    }

    // Step 2: Right products (in-place)
    var right = 1
    for (i in n - 1 downTo 0) {
        result[i] *= right
        right *= nums[i]
    }

    return result
}


    
