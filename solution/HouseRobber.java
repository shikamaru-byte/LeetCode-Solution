public class HouseRobber {

    public int rob(int[] nums) {
        return get(nums, 0, nums.length - 1);
    }

    public int rob2(int[] nums) {
        return Math.max(get(nums, 0, Math.max(0, nums.length - 2)), get(nums, 1, nums.length - 1));
    }

    private int get(int[] nums, int begin, int end) {
        int a = 0, b = 0;
        for (int i = begin;i <= end;i ++) {
            int tmp = b;
            b = Math.max(b, a + nums[i]);
            a = tmp;
        }
        return Math.max(a, b);
    }

    public int rob(TreeNode root) {
        int[] res = help(root);
        return Math.max(res[0], res[1]);
    }

    private int[] help(TreeNode node) {
        if (node == null) return new int[]{0, 0};
        int[] left = help(node.left), right = help(node.right);
        return new int[]{Math.max(left[0], left[1]) + Math.max(right[0], right[1]), node.val + left[0] + right[0],};
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}
