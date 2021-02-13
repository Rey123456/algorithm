package com.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @ClassName Code_100
 * @Description TODO
 * @Author rey
 * @Date 2021/2/13 上午10:26
 */
public class Code_100 {
    public class TreeNode {
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return preOrderTraverse(p, q);
        // return preOrderTraverse1(p, q);
        // return inOrderTraverse(p, q);
        // return inOrderTraverse1(p, q);
        // return postOrderTraverse(p, q);
        // return postOrderTraverse1(p, q);
        // return levelOrderTraverse(p, q);
    }

    //递归版
    public boolean preOrderTraverse(TreeNode p, TreeNode q){
        if(p!=null && q!=null){
            if(p.val != q.val) return false;
            return preOrderTraverse(p.left,q.left) && preOrderTraverse(p.right, q.right);
        }else if(p==null && q==null){
            return true;
        }else{
            return false;
        }
    }

    public boolean inOrderTraverse(TreeNode p, TreeNode q){
        if(p!=null && q!=null){
            if(!inOrderTraverse(p.left,q.left)) return false;
            if(p.val != q.val) return false;
            return inOrderTraverse(p.right, q.right);
        }else if(p==null && q==null){
            return true;
        }else{
            return false;
        }
    }

    public boolean postOrderTraverse(TreeNode p, TreeNode q){
        if(p!=null && q!=null){
            if(!postOrderTraverse(p.right,q.right)) return false;
            if(p.val != q.val) return false;
            return postOrderTraverse(p.left, q.left);
        }else if(p==null && q==null){
            return true;
        }else{
            return false;
        }
    }

    //非递归
    public boolean preOrderTraverse1(TreeNode p, TreeNode q){
        Stack<TreeNode> pList = new Stack<TreeNode>();
        Stack<TreeNode> qList = new Stack<TreeNode>();
        while ((p!=null||q!=null)|| (!pList.isEmpty() || !qList.isEmpty())){
            if(p!=null&&q!=null) {
                if (p.val != q.val) return false;
                pList.push(p);
                qList.push(q);
                p = p.left;
                q = q.left;
            }else if(p==null && q==null){
                p = pList.pop().right;
                q = qList.pop().right;
            }else{
                return false;
            }
        }
        return true;
    }

    public boolean inOrderTraverse1(TreeNode p, TreeNode q){
        Stack<TreeNode> pList = new Stack<TreeNode>();
        Stack<TreeNode> qList = new Stack<TreeNode>();
        while ((p!=null||q!=null)|| (!pList.isEmpty() || !qList.isEmpty())){
            if(p!=null&&q!=null) {
                pList.push(p);
                qList.push(q);
                p = p.left;
                q = q.left;
            }else if(p==null && q==null){
                p = pList.pop();
                q = qList.pop();
                if (p.val != q.val) return false;
                p = p.right;
                q = q.right;
            }else{
                return false;
            }
        }
        return true;
    }

    public boolean postOrderTraverse1(TreeNode p, TreeNode q){
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        Stack<TreeNode> pStack = new Stack<TreeNode>();
        Stack<TreeNode> qStack = new Stack<TreeNode>();
        TreeNode curp,prep = null;
        TreeNode curq,preq = null;
        pStack.push(p);
        qStack.push(q);
        while (!pStack.isEmpty() || !qStack.isEmpty()){
            curp = pStack.peek();
            curq = qStack.peek();
            if((curp.left==null&&curp.right==null&&curq.left==null&&curq.right==null) ||
                    (prep!=null&&(prep==curp.left || prep==curp.right)&&preq!=null&&(preq==curq.left||preq==curq.right))){
                prep = pStack.pop();
                preq = qStack.pop();
                if(prep.val != preq.val) return false;
            }else{
                if(curp.right!=null && curq.right!=null){
                    pStack.push(curp.right);
                    qStack.push(curq.right);
                }else if(curp.right==null && curq.right==null) ;
                else return false;
                if(curp.left!=null && curq.left!=null){
                    pStack.push(curp.left);
                    qStack.push(curq.left);
                }else if(curp.left==null && curq.left==null) ;
                else return false;
            }
        }
        return true;
    }

    public boolean levelOrderTraverse(TreeNode p, TreeNode q){
        if(p==null && q==null) return true;
        if(p==null || q==null) return false;
        LinkedList<TreeNode> pQueue = new LinkedList<TreeNode>();
        LinkedList<TreeNode> qQueue = new LinkedList<TreeNode>();

        pQueue.add(p);
        qQueue.add(q);
        while (!pQueue.isEmpty()||!qQueue.isEmpty()){
            p = pQueue.poll();
            q = qQueue.poll();
            if(p.val != q.val) return false;

            if(p.left!=null && q.left!=null){
                pQueue.add(p.left);
                qQueue.add(q.left);
            }else if(p.left==null && q.left==null) ;
            else return false;

            if(p.right!=null && q.right!=null){
                pQueue.add(p.right);
                qQueue.add(q.right);
            }else if(p.right==null && q.right==null) ;
            else return false;
        }
        return true;
    }

}