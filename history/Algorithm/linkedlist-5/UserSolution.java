import java.util.*;

class UserSolution {
    private List<List<Character>> content;
    private int H;
    private int W;
    private int cursorY;
    private int cursorX;
    private List<HashMap<Character, Integer>> alphaCounts;

    void init(int H, int W, char mStr[]) {
        this.H = H;
        this.W = W;
        this.cursorY = 0;
        this.cursorX = 0;
        content = new ArrayList<>(H);
        int index = 0;
        for (int i = 0; i < H; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < W; j++) {
                if (mStr[index] == '\0') {
                    break;
                }
                row.add(mStr[index++]);
            }
            content.add(row);
        }

        alphaCounts = new ArrayList<>(H);
        for (int i = 0; i < H; i++) {
            alphaCounts.add(new HashMap<Character, Integer>());
        }

        for (int i = 0; i < content.size(); i++) {
            for (int j = 0; j < content.get(i).size(); j++) {
                Character value = content.get(i).get(j);
                Map<Character, Integer> row = alphaCounts.get(i);
                row.put(value, row.getOrDefault(value, 0) + 1);
            }
        }
    }

    void insert(char mChar) {
        content.get(cursorY).add(cursorX, mChar);

        HashMap<Character, Integer> rowCount = alphaCounts.get(cursorY);
        rowCount.put(mChar, rowCount.getOrDefault(mChar, 0) + 1);

        for (int i = cursorY; i < H - 1; i++) {
            List<Character> row = content.get(i);
            if (row.size() > W && i + 1 < H) {
                char moveValue = row.get(row.size() - 1);
                row.remove(row.size() - 1);
                content.get(i + 1).add(0, moveValue);

                HashMap<Character, Integer> rowAlphaCount = alphaCounts.get(i);
                rowAlphaCount.put(moveValue, rowAlphaCount.get(moveValue) - 1);
                HashMap<Character, Integer> nextRowAlphaCount = alphaCounts.get(i + 1);
                nextRowAlphaCount.put(moveValue, nextRowAlphaCount.getOrDefault(moveValue, 0) + 1);
            }
        }
        cursorX += 1;
        if (cursorX == W) {
            cursorX = 0;
            cursorY += 1;
        }
    }

    char moveCursor(int mRow, int mCol) {
        cursorY = mRow - 1;
        cursorX = mCol - 1;
        if (cursorX == W) {
            cursorX = 0;
            cursorY += 1;
        }
        if (content.get(cursorY).size() <= cursorX) {
            while (cursorY > 0 && content.get(cursorY).isEmpty()) {
                cursorY--;
            }
            cursorX = content.get(cursorY).size();
            return '$';
        }
        return content.get(cursorY).get(cursorX);
    }

    int countCharacter(char mChar) {
        List<Character> subList = content.get(cursorY).subList(cursorX, content.get(cursorY).size());
        int count = Collections.frequency(subList, mChar);
        for (int i = cursorY + 1; i < H; i++) {
            count += alphaCounts.get(i).getOrDefault(mChar, 0);
        }
        return count;
    }
}