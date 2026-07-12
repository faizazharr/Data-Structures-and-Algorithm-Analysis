# Deletion in Doubly Linked List
# Implementasi 5 jenis deletion pada Doubly Linked List

class Node:
    def __init__(self, data):
        self.data = data
        self.prev = None
        self.next = None


class DoublyLinkedList:
    def __init__(self):
        self.head = None

    # Helper: tambah node di akhir (untuk setup awal)
    def append(self, data):
        new_node = Node(data)
        if self.head is None:
            self.head = new_node
            return
        curr = self.head
        while curr.next:
            curr = curr.next
        curr.next = new_node
        new_node.prev = curr

    # Helper: tampilkan isi list
    def display(self):
        elements = []
        curr = self.head
        while curr:
            elements.append(str(curr.data))
            curr = curr.next
        print("List: " + " <-> ".join(elements) if elements else "List: (kosong)")

    # ---------------------------------------------------------------
    # 1. Deletion at the Beginning
    # ---------------------------------------------------------------
    def delete_at_beginning(self):
        if self.head is None:
            print("List kosong, tidak ada yang dihapus.")
            return
        deleted = self.head.data
        if self.head.next is None:
            self.head = None
        else:
            self.head = self.head.next
            self.head.prev = None
        print(f"Deleted at beginning: {deleted}")

    # ---------------------------------------------------------------
    # 2. Deletion after a given node
    # ---------------------------------------------------------------
    def delete_after_node(self, target_data):
        curr = self.head
        while curr and curr.data != target_data:
            curr = curr.next
        if curr is None:
            print(f"Node dengan data {target_data} tidak ditemukan.")
            return
        if curr.next is None:
            print(f"Tidak ada node setelah {target_data}.")
            return
        node_to_delete = curr.next
        deleted = node_to_delete.data
        curr.next = node_to_delete.next
        if node_to_delete.next:
            node_to_delete.next.prev = curr
        print(f"Deleted after node {target_data}: {deleted}")

    # ---------------------------------------------------------------
    # 3. Deletion before a given node
    # ---------------------------------------------------------------
    def delete_before_node(self, target_data):
        curr = self.head
        while curr and curr.data != target_data:
            curr = curr.next
        if curr is None:
            print(f"Node dengan data {target_data} tidak ditemukan.")
            return
        if curr.prev is None:
            print(f"Tidak ada node sebelum {target_data}.")
            return
        node_to_delete = curr.prev
        deleted = node_to_delete.data
        if node_to_delete.prev:
            node_to_delete.prev.next = curr
            curr.prev = node_to_delete.prev
        else:
            self.head = curr
            curr.prev = None
        print(f"Deleted before node {target_data}: {deleted}")

    # ---------------------------------------------------------------
    # 4. Deletion at a specific position (1-based index)
    # ---------------------------------------------------------------
    def delete_at_position(self, position):
        if self.head is None:
            print("List kosong.")
            return
        if position < 1:
            print("Posisi tidak valid.")
            return
        curr = self.head
        for _ in range(position - 1):
            if curr is None:
                print(f"Posisi {position} melebihi panjang list.")
                return
            curr = curr.next
        if curr is None:
            print(f"Posisi {position} melebihi panjang list.")
            return
        deleted = curr.data
        if curr.prev:
            curr.prev.next = curr.next
        else:
            self.head = curr.next
        if curr.next:
            curr.next.prev = curr.prev
        print(f"Deleted at position {position}: {deleted}")

    # ---------------------------------------------------------------
    # 5. Deletion at the End
    # ---------------------------------------------------------------
    def delete_at_end(self):
        if self.head is None:
            print("List kosong, tidak ada yang dihapus.")
            return
        curr = self.head
        while curr.next:
            curr = curr.next
        deleted = curr.data
        if curr.prev:
            curr.prev.next = None
        else:
            self.head = None
        print(f"Deleted at end: {deleted}")


# ===================================================================
# DEMO
# ===================================================================
if __name__ == "__main__":
    print("=" * 55)
    print("   DELETION IN DOUBLY LINKED LIST")
    print("=" * 55)

    # --- Setup awal: 10 <-> 20 <-> 30 <-> 40 <-> 50 ---
    def make_list():
        dll = DoublyLinkedList()
        for val in [10, 20, 30, 40, 50]:
            dll.append(val)
        return dll

    # 1. Deletion at the Beginning
    print("\n1. Deletion at the Beginning")
    dll = make_list()
    dll.display()
    dll.delete_at_beginning()
    dll.display()

    # 2. Deletion after a given node
    print("\n2. Deletion after a given node (after 20)")
    dll = make_list()
    dll.display()
    dll.delete_after_node(20)
    dll.display()

    # 3. Deletion before a given node
    print("\n3. Deletion before a given node (before 40)")
    dll = make_list()
    dll.display()
    dll.delete_before_node(40)
    dll.display()

    # 4. Deletion at a specific position
    print("\n4. Deletion at a specific position (position 3)")
    dll = make_list()
    dll.display()
    dll.delete_at_position(3)
    dll.display()

    # 5. Deletion at the End
    print("\n5. Deletion at the End")
    dll = make_list()
    dll.display()
    dll.delete_at_end()
    dll.display()

    print("\n" + "=" * 55)
