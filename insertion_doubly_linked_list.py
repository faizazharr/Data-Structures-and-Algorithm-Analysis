# Insertion in Doubly Linked List
# Implementasi 5 jenis insertion pada Doubly Linked List

class Node:
    def __init__(self, data):
        self.data = data
        self.prev = None
        self.next = None


class DoublyLinkedList:
    def __init__(self):
        self.head = None

    # Helper: tampilkan isi list
    def display(self):
        elements = []
        curr = self.head
        while curr:
            elements.append(str(curr.data))
            curr = curr.next
        print("List: " + " <-> ".join(elements) if elements else "List: (kosong)")

    # ---------------------------------------------------------------
    # 1. Insert a Node at Front/Beginning
    # ---------------------------------------------------------------
    def insert_at_beginning(self, data):
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
        else:
            new_node.next = self.head
            self.head.prev = new_node
            self.head = new_node
        print(f"Inserted at beginning: {data}")

    # ---------------------------------------------------------------
    # 2. Insertion after a given node
    # ---------------------------------------------------------------
    def insert_after_node(self, target_data, data):
        curr = self.head
        while curr and curr.data != target_data:
            curr = curr.next
        if curr is None:
            print(f"Node dengan data {target_data} tidak ditemukan.")
            return
        new_node = Node(data)
        new_node.next = curr.next
        new_node.prev = curr
        if curr.next:
            curr.next.prev = new_node
        curr.next = new_node
        print(f"Inserted {data} after node {target_data}")

    # ---------------------------------------------------------------
    # 3. Insertion before a given node
    # ---------------------------------------------------------------
    def insert_before_node(self, target_data, data):
        curr = self.head
        while curr and curr.data != target_data:
            curr = curr.next
        if curr is None:
            print(f"Node dengan data {target_data} tidak ditemukan.")
            return
        new_node = Node(data)
        new_node.next = curr
        new_node.prev = curr.prev
        if curr.prev:
            curr.prev.next = new_node
        else:
            self.head = new_node
        curr.prev = new_node
        print(f"Inserted {data} before node {target_data}")

    # ---------------------------------------------------------------
    # 4. Insertion at a specific position (1-based index)
    # ---------------------------------------------------------------
    def insert_at_position(self, position, data):
        if position < 1:
            print("Posisi tidak valid.")
            return
        if position == 1:
            self.insert_at_beginning(data)
            return
        curr = self.head
        for _ in range(position - 2):
            if curr is None:
                print(f"Posisi {position} melebihi panjang list.")
                return
            curr = curr.next
        if curr is None:
            print(f"Posisi {position} melebihi panjang list.")
            return
        new_node = Node(data)
        new_node.next = curr.next
        new_node.prev = curr
        if curr.next:
            curr.next.prev = new_node
        curr.next = new_node
        print(f"Inserted {data} at position {position}")

    # ---------------------------------------------------------------
    # 5. Insertion at the End
    # ---------------------------------------------------------------
    def insert_at_end(self, data):
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
            print(f"Inserted at end: {data}")
            return
        curr = self.head
        while curr.next:
            curr = curr.next
        curr.next = new_node
        new_node.prev = curr
        print(f"Inserted at end: {data}")


# ===================================================================
# DEMO
# ===================================================================
if __name__ == "__main__":
    print("=" * 55)
    print("   INSERTION IN DOUBLY LINKED LIST")
    print("=" * 55)

    # --- Setup awal: 10 <-> 20 <-> 30 <-> 40 ---
    def make_list():
        dll = DoublyLinkedList()
        for val in [10, 20, 30, 40]:
            dll.insert_at_end(val)
        return dll

    # 1. Insert at Front/Beginning
    print("\n1. Insert a Node at Front/Beginning")
    dll = make_list()
    dll.display()
    dll.insert_at_beginning(5)
    dll.display()

    # 2. Insertion after a given node
    print("\n2. Insertion after a given node (after 20)")
    dll = make_list()
    dll.display()
    dll.insert_after_node(20, 25)
    dll.display()

    # 3. Insertion before a given node
    print("\n3. Insertion before a given node (before 30)")
    dll = make_list()
    dll.display()
    dll.insert_before_node(30, 25)
    dll.display()

    # 4. Insertion at a specific position
    print("\n4. Insertion at a specific position (position 3)")
    dll = make_list()
    dll.display()
    dll.insert_at_position(3, 25)
    dll.display()

    # 5. Insertion at the End
    print("\n5. Insertion at the End")
    dll = make_list()
    dll.display()
    dll.insert_at_end(50)
    dll.display()

    print("\n" + "=" * 55)
