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

    for (num in nums) {
        if (num in unique) return num
        unique.add(num)
    }

    return -1
}

fun maxSubArray(nums: IntArray): Int {
    var currentsum = nums[0]
    var largestSum = nums[0]

    for (i in 1 until nums.size) {

        currentsum = maxOf(nums[i], currentsum + nums[i])
        largestSum = maxOf(largestSum, currentsum)
    }
    return largestSum
}

fun productExceptSelf(nums: IntArray): IntArray {
    val n = nums.size
    val result = IntArray(n) 

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

fun setZeroes(matrix: MutableList<MutableList<Int>>) {
    val rows = matrix.size
    val cols = matrix[0].size

    val rowSet = mutableSetOf<Int>()
    val colSet = mutableSetOf<Int>()

    // First pass: record all rows and columns that have a 0
    for (i in 0 until rows) {
        for (j in 0 until cols) {
            if (matrix[i][j] == 0) {
                rowSet.add(i)
                colSet.add(j)
            }
        }
    }

    // Second pass: set rows to zero
    for (i in rowSet) {
        for (j in 0 until cols) {
            matrix[i][j] = 0
        }
    }

    // Third pass: set columns to zero
    for (j in colSet) {
        for (i in 0 until rows) {
            matrix[i][j] = 0
        }
    }
}

fun spiralOrder(matrix: Array<IntArray>): List<Int> {
      
    val result = mutableListOf<Int>()
    if (matrix.isEmpty() || matrix[0].isEmpty()) return result

    var top = 0
    var bottom = matrix.size - 1
    var left = 0
    var right = matrix[0].size - 1

    while (left <= right && top <= bottom) {

        // Traverse top row
        for (i in left..right) {
            result.add(matrix[top][i])
        }
        top++

        // Traverse right column
        for (i in top..bottom) {
            result.add(matrix[i][right])
        }
        right--

        // Traverse bottom row
        if (top <= bottom) {
            for (i in right downTo left) {
                result.add(matrix[bottom][i])
            }
            bottom--
        }

        // Traverse left column
        if (left <= right) {
            for (i in bottom downTo top) {
                result.add(matrix[i][left])
            }
            left++
        }
        
        }
         return result

    }



    fun generate(numRows: Int): List<List<Int>> {
    val triangle = mutableListOf<List<Int>>()

    for (i in 0 until numRows) {
        val row = mutableListOf<Int>()

        for (j in 0..i) {
            if (j == 0 || j == i) {
                row.add(1)
            } else {
                val value = triangle[i - 1][j - 1] + triangle[i - 1][j]
                row.add(value)
            }
        }

        triangle.add(row)
    }

    return triangle
}

//To print a particular item of Pascal's Triangle at row i and column j, there are two main ways:
fun particularPascalValue(row: Int, col: Int): Long {
    if (row < 0 || col > row) return -1

    return factorial(row) / (factorial(col) * factorial(row - col))
}

fun factorial(n: Int): Long {
    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

fun nthRow(n: Int): List<Long> {
    val row = mutableListOf<Long>()
    for (j in 0..n) {
        row.add(particularPascalValue(n, j))
    }
    return row
}


// find all the permutation of array

fun permute(arr: List<Int>, start: Int, result: MutableList<List<Int>>) {
    if (start == arr.size) {
        result.add(arr.toList())
        return
    }
    for (i in start until arr.size) {
        val swapped = arr.toMutableList()
        val temp = swapped[start]
        swapped[start] = swapped[i]
        swapped[i] = temp

    
        permute(swapped, start + 1, result)
    }
}



fun isPalindrome(s: String): Boolean {
    var left = 0
    var right = s.length - 1

    while (left < right) {
        // Move left to next alphanumeric
        while (left < right && !s[left].isLetterOrDigit()) {
            left++
        }
        // Move right to previous alphanumeric
        while (left < right && !s[right].isLetterOrDigit()) {
            right--
        }

        if (s[left].lowercaseChar() != s[right].lowercaseChar()) {
            return false
        }

        left++
        right--
    }

    return true
}
