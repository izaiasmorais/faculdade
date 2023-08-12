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

        self.canvas = tk.Canvas(root, width=1920, height=600)
        self.canvas.pack()

    def insert_name(self):
        name = self.entry.get()
        if name:
            self.tree.insert(name)
            self.display_tree()
            self.update_info()

    def remove_name(self):
        name_to_remove = self.entry.get()
        if name_to_remove:
            removed = self.tree.remove(name_to_remove)
            if removed:
                self.update_info()
                self.display_tree()

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
            text=f"Balanceamento: {'Balanceada' if is_balanced else 'Não Balanceada'}"
        )

    def display_tree(self):
        self.canvas.delete("all")
        tree_height = self.tree.height(self.tree.root)
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


