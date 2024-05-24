//给你两个正整数：n 和 target 。 
//
// 如果数组 nums 满足下述条件，则称其为 美丽数组 。 
//
// 
// nums.length == n. 
// nums 由两两互不相同的正整数组成。 
// 在范围 [0, n-1] 内，不存在 两个 不同 下标 i 和 j ，使得 nums[i] + nums[j] == target 。 
// 
//
// 返回符合条件的美丽数组所可能具备的 最小 和，并对结果进行取模 10⁹ + 7。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2, target = 3
//输出：4
//解释：nums = [1,3] 是美丽数组。
//- nums 的长度为 n = 2 。
//- nums 由两两互不相同的正整数组成。
//- 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
//可以证明 4 是符合条件的美丽数组所可能具备的最小和。 
//
// 示例 2： 
//
// 
//输入：n = 3, target = 3
//输出：8
//解释：
//nums = [1,3,4] 是美丽数组。 
//- nums 的长度为 n = 3 。 
//- nums 由两两互不相同的正整数组成。 
//- 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
//可以证明 8 是符合条件的美丽数组所可能具备的最小和。 
//
// 示例 3： 
//
// 
//输入：n = 1, target = 1
//输出：1
//解释：nums = [1] 是美丽数组。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁹ 
// 1 <= target <= 10⁹ 
// 
//
// Related Topics 贪心 数学 👍 47 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class FindTheMinimumPossibleSumOfABeautifulArray {
    public static void main(String[] args) {
        Solution solution = new FindTheMinimumPossibleSumOfABeautifulArray().new Solution();
        System.out.println(solution.minimumPossibleSum(1000000000, 999999999));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumPossibleSum(int n, int target) {
            //以target=8为例，有1+7=2+6=3+5=4+4 要尽量使和最小，应取前面的 1 2 3，而不能取 7 6 5
            //因此以target/2为界，先尽量将前面的取完
            // 如果前面是够n个的，就只需要返回美丽数组就是[1,2,3,4,5...n]
            // 如果前面不够n个，就再从target,target+1,target+2,...继续取，一直取够n个，设mid = target/2，即取到[1,2,...,mid,target,target+1,...,target+(n-mid-1)]
            int MOD = (int) 1e9 + 7;
            long mid = target/2;
            long ln = n;
            long res;
            if(n<=mid) {
                res =  (1+ln)*ln/2;
            } else {
                res = (1+mid)*mid/2+(target+target+(ln-mid-1))*(ln-mid)/2;
            }
            return (int) (res % (MOD));

        }


        //暴力哈希，超时
        private int minimumPossibleSum1(int n, int target) {
            if (n == 1) {
                return 1;
            }
            Set<Integer> set = new HashSet<>();
            int k = 1;
            for (int i = 0; i < n; i++) {
                while (set.contains(target - k)) {
                    k++;
                }
                set.add(k++);
            }
            long res = 0;
            for (Integer num : set) {
                res += num;
            }
            return (int) (res % (1e9 + 7));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}