from node import Node
from collections import deque


class BinarySearchTree:
    def __init__(self):
        self.root = None

    def insert(self, key):
        self.root = self._insert_recursive(self.root, key)

    def _insert_recursive(self, node, key):
        if node is None:
            return Node(key)
        if key < node.key:
            node.left = self._insert_recursive(node.left, key)
        elif key > node.key:
            node.right = self._insert_recursive(node.right, key)
        return node

    def size(self, node):
        if node is None:
            return 0
        return 1 + self.size(node.left) + self.size(node.right)

    def height(self, node):
        if node is None:
            return -1
        left_height = self.height(node.left)
        right_height = self.height(node.right)
        return max(left_height, right_height) + 1

    def min_value(self, node):
        while node.left is not None:
            node = node.left
        return node.key

    def max_value(self, node):
        while node.right is not None:
            node = node.right
        return node.key

    def internal_path_length(self, node, depth=0):
        if node is None:
            return 0
        return (
            depth
            + self.internal_path_length(node.left, depth + 1)
            + self.internal_path_length(node.right, depth + 1)
        )

    def is_balanced(self, node):
        if node is None:
            return True
        left_height = self.height(node.left)
        right_height = self.height(node.right)
        if (
            abs(left_height - right_height) <= 1
            and self.is_balanced(node.left)
            and self.is_balanced(node.right)
        ):
            return True
        return False

    def in_order(self, node, result):
        if node is None:
            return
        self.in_order(node.left, result)
        result.append(node.key)
        self.in_order(node.right, result)

    def post_order(self, node, result):
        if node is None:
            return
        self.post_order(node.left, result)
        self.post_order(node.right, result)
        result.append(node.key)

    def pre_order(self, node, result):
        if node is None:
            return
        result.append(node.key)
        self.pre_order(node.left, result)
        self.pre_order(node.right, result)

    def level_order(self, node, result):
        if node is None:
            return
        queue = deque()
        queue.append(node)
        while queue:
            current_node = queue.popleft()
            result.append(current_node.key)
            if current_node.left:
                queue.append(current_node.left)
            if current_node.right:
                queue.append(current_node.right)
