import tkinter as tk
from tree import BinarySearchTree


class GUIApplication:
    def __init__(self, root):
        self.root = root
        self.root.title("Árvore Binária de Busca")

        self.tree = BinarySearchTree()

        self.label = tk.Label(root, text="Digite um nome:")
        self.label.pack()

        self.entry = tk.Entry(root)
        self.entry.pack()

        self.insert_button = tk.Button(root, text="Inserir", command=self.insert_name)
        self.insert_button.pack()

        self.info_frame = tk.Frame(root)
        self.info_frame.pack(side="left")

        self.info_label = tk.Label(self.info_frame, text="Informações:")
        self.info_label.pack()

        self.size_label = tk.Label(self.info_frame, text="Tamanho:")
        self.size_label.pack()

        self.height_label = tk.Label(self.info_frame, text="Altura:")
        self.height_label.pack()

        self.min_max_label = tk.Label(self.info_frame, text="Menor/Maior:")
        self.min_max_label.pack()

        self.ipl_label = tk.Label(self.info_frame, text="Comprimento Interno:")
        self.ipl_label.pack()

        self.balance_label = tk.Label(self.info_frame, text="Balanceamento:")
        self.balance_label.pack()

        self.in_order_label = tk.Label(self.info_frame, text="Em Ordem:")
        self.in_order_label.pack()

        self.post_order_label = tk.Label(self.info_frame, text="Pós-Ordem:")
        self.post_order_label.pack()

        self.pre_order_label = tk.Label(self.info_frame, text="Pré-Ordem:")
        self.pre_order_label.pack()

        self.breadth_first_label = tk.Label(self.info_frame, text="Busca em Largura:")
        self.breadth_first_label.pack()

        self.canvas = tk.Canvas(root, width=1920, height=600)
        self.canvas.pack()

    def insert_name(self):
        name = self.entry.get()
        if name:
            self.tree.insert(name)
            self.display_tree()
            self.update_info()
            self.update_traversals()

    def update_info(self):
        size = self.tree.size(self.tree.root)
        height = self.tree.height(self.tree.root)
        min_value = self.tree.min_value(self.tree.root)
        max_value = self.tree.max_value(self.tree.root)
        ipl = self.tree.internal_path_length(self.tree.root)
        is_balanced = self.tree.is_balanced(self.tree.root)

        self.size_label.config(text=f"Tamanho: {size}")
        self.height_label.config(text=f"Altura: {height}")
        self.min_max_label.config(text=f"Menor/Maior: {min_value} / {max_value}")
        self.ipl_label.config(text=f"Comprimento Interno: {ipl}")
        self.balance_label.config(
            text=f"Balanceamento: {'Sim' if is_balanced else 'Não'}"
        )

    def update_traversals(self):
        in_order_result = []
        post_order_result = []
        pre_order_result = []
        breadth_first_result = []

        self.tree.in_order(self.tree.root, in_order_result)
        self.tree.post_order(self.tree.root, post_order_result)
        self.tree.pre_order(self.tree.root, pre_order_result)
        self.tree.level_order(self.tree.root, breadth_first_result)

        self.in_order_label.config(text=f"Em Ordem: {' '.join(in_order_result)}")
        self.post_order_label.config(text=f"Pós-Ordem: {' '.join(post_order_result)}")
        self.pre_order_label.config(text=f"Pré-Ordem: {' '.join(pre_order_result)}")
        self.breadth_first_label.config(
            text=f"Busca em Largura: {' '.join(breadth_first_result)}"
        )

    def display_tree(self):
        self.canvas.delete("all")
        canvas_width = self.canvas.winfo_width()
        self._display_tree_recursive(self.tree.root, canvas_width // 2, 50, 100, 80)

    def _display_tree_recursive(self, node, x, y, x_offset, y_offset):
        if node is not None:
            self.canvas.create_oval(x - 30, y - 30, x + 30, y + 30, fill="black")
            self.canvas.create_text(x, y, text=node.key, fill="white")

            if node.left is not None:
                x_left = x - x_offset
                y_left = y + y_offset
                self.canvas.create_line(x, y, x_left, y_left)
                self._display_tree_recursive(
                    node.left, x_left, y_left, x_offset * 1.2, y_offset * 1.2
                )

            if node.right is not None:
                x_right = x + x_offset
                y_right = y + y_offset
                self.canvas.create_line(x, y, x_right, y_right)
                self._display_tree_recursive(
                    node.right, x_right, y_right, x_offset * 1.2, y_offset * 1.2
                )
