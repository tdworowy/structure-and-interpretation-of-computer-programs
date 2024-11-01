def pascal(row: int, col: int) -> int:
    if row == 1:
        return 1
    if col == 1 or col == row:
        return 1
    else:
        return pascal(row - 1, col - 1) + pascal(row - 1, col)


if __name__ == "__main__":
    for row in range(1, 10):
        res = []
        for col in range(1, row + 1):
            res.append(pascal(row, col))
        print(res)
