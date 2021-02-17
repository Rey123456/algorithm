package com.algorithm.leetcode;

import java.util.LinkedList;

/**
 * @ClassName Code_968
 * @Description Binary Tree Cameras
 * @Author rey
 * @Date 2021/2/16 上午11:38
 */
public class Code_968 {

    public  static  TreeNode makeTree( Integer [] src)
    {
        LinkedList <TreeNode>queue  =  new LinkedList();
        TreeNode tree = new TreeNode(src[0]);
        int level = 1;
        queue.add(tree);

        while (level < src.length)
        {
            TreeNode node = queue.poll();
            Integer value =  src[level++];
            TreeNode left = null;
            if(null != value)
            {
                left = new TreeNode(value);
                node.left = left;
                queue.add(left);
            }

            if(level ==  src.length)
            {
                break;
            }
            value =  src[level++];
            TreeNode right = null;
            if(value != null)
            {
                right = new TreeNode(value);
                node.right = right;
                queue.add(right);
            }

        }

        return tree;
    }

    public static void main(String[] args){
        Integer []src =  {0,0,null,0,null,0,null,null,0};
        TreeNode root = makeTree(src);
        System.out.print(new Code_968().minCameraCover(root));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }
    }

    /**
     *  https://leetcode-cn.com/problems/binary-tree-cameras/solution/968-jian-kong-er-cha-shu-di-gui-shang-de-zhuang-ta/
     * 需要确定遍历方式：后序遍历（尽量让父节点安装摄像头）
     * 需要状态转移的方程
     * 0：该节点无覆盖
     * 1：本节点有摄像头
     * 2：本节点有覆盖
     * */
    int result;
    public int traversal(TreeNode curr){
        // 空节点，该节点有覆盖
        if(curr == null) return 2;
        int left = traversal(curr.left);
        int right = traversal(curr.right);
        // 情况1
        // 左右节点都有覆盖
        if(left==2 && right==2) return 0;
        // 情况2
        // left == 0 && right == 0 左右节点无覆盖
        // left == 1 && right == 0 左节点有摄像头，右节点无覆盖
        // left == 0 && right == 1 左节点有无覆盖，右节点摄像头
        // left == 0 && right == 2 左节点无覆盖，右节点覆盖
        // left == 2 && right == 0 左节点覆盖，右节点无覆盖
        else if(left == 0 || right == 0) {
            result ++;
            return 1;
        }
        // 情况3
        // left == 1 && right == 2 左节点有摄像头，右节点有覆盖
        // left == 2 && right == 1 左节点有覆盖，右节点有摄像头
        // left == 1 && right == 1 左右节点都有摄像头
        // 其他情况前段代码均已覆盖
        //if(left==1 || right==1)
        else  return 2;

    }
    public int minCameraCover(TreeNode root) {
        result = 0;
        // 情况4
        if (traversal(root) == 0) { // root 无覆盖
            result++;
        }
        return result;
    }
}