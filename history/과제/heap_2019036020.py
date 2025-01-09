# 사용자가 최소 힙에서 다음 작업을 수행할 수 있도록 하는 사용자 친화적인 메뉴 구동 프로그램을 작성하십시오.
# (a) 최소 힙 생성
# (b) 값이 가장 낮은 키를 제거합니다.
# (c) 임의 요소의 우선 순위 변경
# (d) 힙에 요소 삽입


def print_menu():
    print("\n")
    print("#" * 50)
    print("# " + "%-46s" % "1. print heap" + " #")
    print("# " + "%-46s" % "2. insert an element into the heap" + " #")
    print("# " + "%-46s" % "3. change the priority of an arbitrary element" + " #")
    print("# " + "%-46s" % "4. remove the key with the lowest value" + " #")
    print("# " + "%-46s" % "5. exit program" + " #")
    print("#" * 50)


def heap_push(heap, item):
    """리스트 heap 을 받아서 최소 힙 형태로 item을 리스트에 삽입한다."""
    heap.append(item)
    index = len(heap) - 1
    parent_index = (index - 1) // 2
    while parent_index >= 0:
        if heap[parent_index] <= heap[index]:
            break

        # print(str(parent_index) + "와" + str(index) + "를 교환합니다.")
        tmp = heap[parent_index]
        heap[parent_index] = heap[index]
        heap[index] = tmp

        index = parent_index
        parent_index = (index - 1) // 2


def heap_pop(heap):
    """최소 힙에서 가장 작은 값인 루트 노드를 제거하고 그 값을 반환한다."""
    if not heap:
        return False
    elif len(heap) == 1:
        return heap.pop()

    root = heap[0]
    heap[0] = heap.pop()
    index = 0
    child = index * 2 + 1
    if child + 1 < len(heap) and heap[child] > heap[child + 1]:
        child = child + 1

    while child < len(heap):
        if heap[child] <= heap[index]:
            tmp = heap[child]
            heap[child] = heap[index]
            heap[index] = tmp

            index = child
            child = index * 2 + 1
            if child + 1 < len(heap) and heap[child] > heap[child + 1]:
                child = child + 1
        else:
            break

    return root


def heap_update(heap, index, item):
    """힙 리스트의 index 위치에 있는 값을 업데이트 하여 다시 힙으로 만든다. 힙 특성상 중간에 있는 값을 변경하는데에 적절한 자료구조가 아니기 때문에 힙을 초기화하고 다시 생성하는 방식을 사용하였다."""
    if index > len(heap) - 1:
        print("인덱스 오류")
        return heap
    heap[index] = item
    tmp = []
    for v in heap:
        heap_push(tmp, v)
    return tmp


def heap_print(heap):
    """힙을 출력한다."""
    print("index   : ", end="")
    for i in range(len(heap)):
        print(i, end=" ")
    print("\nelement : ", end="")
    for v in heap:
        print(v, end=" ")
    print()
    # print()
    # num = 0
    # count = 1
    # for i in range(len(heap)):
    #     print(f"{heap[i]}({i})", end=" ")
    #     if i == num:
    #         print()
    #         num += 2**count
    #         count += 1


h = []
userinput = -1

while userinput != 5:
    print_menu()
    userinput = int(input("실행할 기능의 숫자를 입력하세요 : "))
    if userinput == 1:
        heap_print(h)
    elif userinput == 2:
        item = int(input("삽입할 요소의 값을 입력하세요 : "))
        heap_push(h, item)
    elif userinput == 3:
        index, item = map(int, input("요소의 인덱스와 바꿀 요소의 값을 입력하세요 (띄어쓰기로 구분) : ").split())
        h = heap_update(h, index, item)
    elif userinput == 4:
        popdata = heap_pop(h)
        print(popdata, "를 제거했습니다.")
    else:
        break
