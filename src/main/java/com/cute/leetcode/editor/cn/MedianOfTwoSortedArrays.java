//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3725 👎 0

package com.cute.leetcode.editor.cn;
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 两个有序数组的中位数
     *
     * @author chengxuanbin
     */
    class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //return findMedianSortedArrays_minK(nums1,nums2);
        return findMedianSortedArrays_binary(nums1,nums2);
    }

    //二分法
    double findMedianSortedArrays_binary(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        //我们保证第一个数组的长度小于第二个数组
        if(m > n) {
            return findMedianSortedArrays_binary(nums2,nums1);
        }

        //1 将两个数组分别划分为左半部分和右半部分，使得两个数组的左半部分的元素数量之和=右半部分的元素数量之和(或+1);
        //2 设i,j分别为两数组右侧部分的起始索引 (i,j的含义同时也可分别表示两数组左侧元素的数量)
        // 若此时满足nums1[i-1]<=nums2[j] && nums2[j-1]<=nums1[i]，则满足中位数的定义：两边元素相等(或+1),且左边元素均小于等于右边元素
        //3.设左半部分的元素数量之和为totalLeft，则i+j = totalLeft(因为i,j分别表示两数组左侧元素的数量);
        //3.1 如果数组总长度为偶数，则等价于(m+n)/2，左半边元素==右半边元素，则中位数为(max(left)+min(right))/2
        //3.2 如果数组总长度为奇数，通过+1向上取整，使得左边元素==右边元素+1，则中位数为max(left);
        int totalLeft = (m+n+1)/2;

        int left = 0,right = m;

        //要寻找满足nums1[i-1]<=nums2[j] && nums2[j-1]<=nums1[i]的i，j，可等价为寻找满足nums1[i-1]<=nums2[j]的最大i,
        //因为若nums1[i-1]<=nums2[j]的i已为最大值，则说明nums1[i]>nums2[j-1]，已满足条件nums2[j-1]<=nums1[i]
        //同时若取第二大的i(即取i-2)，nums1[i-2]<=nums2[j+1]虽然成立，但由于nums1[i-1]<=nums2[j]，则nums2[j]<nums1[i-1]不成立，不满足左边值均小于右边值的定义
        //因此问题可转换为寻找最大的i，使满足nums1[i-1]<=nums2[j]

        //二分查找，初始搜索范围[0,m]，寻找满足nums1[i-1]<=nums2[j]的最大值i
        //循环退出条件left==right，左右指针重合，指针i前面(包括自己)满足nums1[i-1]<=nums2[j]，指针i后面不满足nums1[i-1]<=nums2[j]
        //即找到了最大的i=left=right，使得nums1[i-1]<=nums[j]
        while(left < right) {
            //+1是为了向上取整，防止left+1 == right时 i一直等于left导致无法退出循环,同时可保证i-1>=0，
            int i = left + (right-left+1)/2;
            int j = totalLeft - i;


            if(nums1[i-1] > nums2[j]) {
                //若nums1[i-1] > nums2[j]，说明i的值取大了，收缩右侧边界，搜索范围为[left,i-1]
                right = i-1;
            } else {
                left = i;//否则i的取值满足nums1[i-1]<=nums[j]，需继续收缩左侧边界，以找到最大的i,搜索范围为[i,right]
            }
        }

        int i = left,j=totalLeft-i;

        int maxLeftA = i == 0?Integer.MIN_VALUE:nums1[i-1];
        int maxLeftB = j == 0?Integer.MIN_VALUE:nums2[j-1];
        int minRightA = i == m ? Integer.MAX_VALUE:nums1[i];
        int minRightB = j == n ? Integer.MAX_VALUE:nums2[j];

        if((m+n)%2 == 1) {
            return Math.max(maxLeftA,maxLeftB);
        }
        return (Math.max(maxLeftA,maxLeftB) + Math.min(minRightA,minRightB))/2.0;

    }

    //两个有序数组的第K位算法求中位数-折半删除
    double findMedianSortedArrays_minK(int[] nums1, int[] nums2) {
        int length = nums1.length+nums2.length;
        if(length%2==1) {
            return getMinKOfTwoSortedArrays(nums1,nums2,0,0,length/2+1);
        } else {
            return (getMinKOfTwoSortedArrays(nums1,nums2,0,0,length/2)
                    + getMinKOfTwoSortedArrays(nums1,nums2,0,0,length/2+1))/2.0;
        }
    }

    //获取两个有序数组的第K位数
    int getMinKOfTwoSortedArrays(int[] nums1, int[] nums2, int index1, int index2, int K) {
        int len1 = nums1.length-index1;
        int len2 = nums2.length-index2;

        if(len1 > len2) {
            return getMinKOfTwoSortedArrays(nums2, nums1,index2,index1, K);
        }

        if(len1 == 0) {
            return nums2[index2+K-1];
        }

        if(K == 1) {
            return Math.min(nums1[index1],nums2[index2]);
        }

        //
        int i = index1 + Math.min(len1,K/2)-1;
        int j = index2 + Math.min(len2,K/2)-1;

        if(nums1[i] < nums2[j]) {
            return getMinKOfTwoSortedArrays(nums1,nums2,i+1,index2,K-(i-index1+1));
        } else {
            return getMinKOfTwoSortedArrays(nums1,nums2,index1,j+1,K-(j-index2+1));
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}