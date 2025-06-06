import java.util.*;

public class SortedListToBSTUserInput {
    // ListNode definition
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    // TreeNode definition
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
    
    public static class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            return buildBST(head, null);
        }

        // Build BST from [head, tail)
        private TreeNode buildBST(ListNode head, ListNode tail) {
            if (head == tail) return null;

            ListNode slow = head;
            ListNode fast = head;
            while (fast != tail && fast.next != tail) {
                slow = slow.next;
                fast = fast.next.next;
            }

            TreeNode root = new TreeNode(slow.val);
            root.left = buildBST(head, slow);
            root.right = buildBST(slow.next, tail);

            return root;
        }
    }
    
    // Utility: print BST in level order (including nulls for clarity)
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<String> result = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                result.add("null");
            }
        }
        
        // Remove trailing "null"s
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i);
            i--;
        }
        
        System.out.println(result);
    }
    
    // Build linked list from array
    public static ListNode buildLinkedList(int[] nums) {
        if (nums.length == 0) return null;
        ListNode head = new ListNode(nums[0]);
        ListNode current = head;
        for (int i = 1; i < nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
        }
        return head;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of elements in the sorted linked list:");
        int n = sc.nextInt();
        
        int[] nums = new int[n];
        if (n > 0) {
            System.out.println("Enter the elements in ascending order:");
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
        }
        
        ListNode head = buildLinkedList(nums);
        Solution sol = new Solution();
        TreeNode bstRoot = sol.sortedListToBST(head);
        
        System.out.println("BST (level order):");
        printLevelOrder(bstRoot);
        
        sc.close();
    }
}

