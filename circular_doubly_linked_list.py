# Circular Doubly Linked List
# Node terakhir -> node pertama (next), node pertama -> node terakhir (prev)
# Mendukung traversal ke depan DAN ke belakang secara melingkar

class Node:
    def __init__(self, data):
        self.data = data
        self.prev = None
        self.next = None


class CircularDoublyLinkedList:
    def __init__(self):
        self.head = None

    # Helper: tampilkan list (forward)
    def display(self):
        if self.head is None:
            print("List: (kosong)")
            return
        elements = []
        curr = self.head
        while True:
            elements.append(str(curr.data))
            curr = curr.next
            if curr == self.head:
                break
        print("List: " + " <-> ".join(elements) + " (circular)")

    # Helper: tampilkan list (backward)
    def display_reverse(self):
        if self.head is None:
            print("List reverse: (kosong)")
            return
        tail = self.head.prev
        elements = []
        curr = tail
        while True:
            elements.append(str(curr.data))
            curr = curr.prev
            if curr == tail:
                break
        print("List reverse: " + " <-> ".join(elements) + " (circular)")

    # ---------------------------------------------------------------
    # INSERTION
    # ---------------------------------------------------------------

    # 1. Insert at Beginning
    def insert_at_beginning(self, data):
        new_node = Node(data)
        if self.head is None:
            new_node.next = new_node
            new_node.prev = new_node
            self.head = new_node
        else:
            tail = self.head.prev
            new_node.next = self.head
            new_node.prev = tail
            tail.next = new_node
            self.head.prev = new_node
            self.head = new_node
        print(f"Inserted at beginning: {data}")

    # 2. Insert after a given node
    def insert_after_node(self, target_data, data):
        if self.head is None:
            print("List kosong.")
            return
        curr = self.head
        while True:
            if curr.data == target_data:
                new_node = Node(data)
                new_node.next = curr.next
                new_node.prev = curr
                curr.next.prev = new_node
                curr.next = new_node
                print(f"Inserted {data} after node {target_data}")
                return
            curr = curr.next
            if curr == self.head:
                break
        print(f"Node dengan data {target_data} tidak ditemukan.")

    # 3. Insert before a given node
    def insert_before_node(self, target_data, data):
        if self.head is None:
            print("List kosong.")
            return
        curr = self.head
        while True:
            if curr.data == target_data:
                new_node = Node(data)
                new_node.next = curr
                new_node.prev = curr.prev
                curr.prev.next = new_node
                curr.prev = new_node
                if curr == self.head:
                    self.head = new_node
                print(f"Inserted {data} before node {target_data}")
                return
            curr = curr.next
            if curr == self.head:
                break
        print(f"Node dengan data {target_data} tidak ditemukan.")

    # 4. Insert at a specific position (1-based)
    def insert_at_position(self, position, data):
        if position < 1:
            print("Posisi tidak valid.")
            return
        if position == 1:
            self.insert_at_beginning(data)
            return
        curr = self.head
        for i in range(1, position - 1):
            curr = curr.next
            if curr == self.head:
                print(f"Posisi {position} melebihi panjang list.")
                return
        new_node = Node(data)
        new_node.next = curr.next
        new_node.prev = curr
        curr.next.prev = new_node
        curr.next = new_node
        print(f"Inserted {data} at position {position}")

    # 5. Insert at End
    def insert_at_end(self, data):
        new_node = Node(data)
        if self.head is None:
            new_node.next = new_node
            new_node.prev = new_node
            self.head = new_node
        else:
            tail = self.head.prev
            tail.next = new_node
            new_node.prev = tail
            new_node.next = self.head
            self.head.prev = new_node
        print(f"Inserted at end: {data}")

    # ---------------------------------------------------------------
    # DELETION
    # ---------------------------------------------------------------

    # 1. Delete at Beginning
    def delete_at_beginning(self):
        if self.head is None:
            print("List kosong.")
            return
        deleted = self.head.data
        if self.head.next == self.head:  # hanya 1 node
            self.head = None
        else:
            tail = self.head.prev
            self.head = self.head.next
            self.head.prev = tail
            tail.next = self.head
        print(f"Deleted at beginning: {deleted}")

    # 2. Delete at End
    def delete_at_end(self):
        if self.head is None:
            print("List kosong.")
            return
        tail = self.head.prev
        deleted = tail.data
        if tail == self.head:  # hanya 1 node
            self.head = None
        else:
            tail.prev.next = self.head
            self.head.prev = tail.prev
        print(f"Deleted at end: {deleted}")

    # 3. Delete a specific node (by value)
    def delete_node(self, target_data):
        if self.head is None:
            print("List kosong.")
            return
        curr = self.head
        while True:
            if curr.data == target_data:
                if curr.next == curr:  # hanya 1 node
                    self.head = None
                else:
                    curr.prev.next = curr.next
                    curr.next.prev = curr.prev
                    if curr == self.head:
                        self.head = curr.next
                print(f"Deleted node: {target_data}")
                return
            curr = curr.next
            if curr == self.head:
                break
        print(f"Node dengan data {target_data} tidak ditemukan.")


# ===================================================================
# DEMO
# ===================================================================
if __name__ == "__main__":
    print("=" * 60)
    print("   CIRCULAR DOUBLY LINKED LIST")
    print("=" * 60)

    def make_list():
        cdll = CircularDoublyLinkedList()
        for val in [10, 20, 30, 40]:
            cdll.insert_at_end(val)
        return cdll

    # --- INSERTION ---
    print("\n--- INSERTION ---")

    print("\n1. Insert at Beginning")
    cdll = make_list()
    cdll.display()
    cdll.insert_at_beginning(5)
    cdll.display()

    print("\n2. Insert after a given node (after 20)")
    cdll = make_list()
    cdll.display()
    cdll.insert_after_node(20, 25)
    cdll.display()

    print("\n3. Insert before a given node (before 30)")
    cdll = make_list()
    cdll.display()
    cdll.insert_before_node(30, 25)
    cdll.display()

    print("\n4. Insert at a specific position (position 3)")
    cdll = make_list()
    cdll.display()
    cdll.insert_at_position(3, 25)
    cdll.display()

    print("\n5. Insert at End")
    cdll = make_list()
    cdll.display()
    cdll.insert_at_end(50)
    cdll.display()

    # --- DELETION ---
    print("\n--- DELETION ---")

    print("\n1. Delete at Beginning")
    cdll = make_list()
    cdll.display()
    cdll.delete_at_beginning()
    cdll.display()

    print("\n2. Delete at End")
    cdll = make_list()
    cdll.display()
    cdll.delete_at_end()
    cdll.display()

    print("\n3. Delete a specific node (node 30)")
    cdll = make_list()
    cdll.display()
    cdll.delete_node(30)
    cdll.display()

    # --- Circular check: traversal balik ---
    print("\n--- CIRCULAR CHECK (reverse traversal) ---")
    cdll = make_list()
    cdll.display()
    cdll.display_reverse()

    print("\n" + "=" * 60)
