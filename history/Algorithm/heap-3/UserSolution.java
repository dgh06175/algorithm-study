import java.util.*;

class Post {
    int pID;
    int uID;
    int timestamp;
    int likes;

    public Post(int pID, int uID, int timestamp) {
        this.pID = pID;
        this.uID = uID;
        this.timestamp = timestamp;
        this.likes = 0;
    }
}

class UserSolution {
    private int N;
    private Map<Integer, Set<Integer>> follows;
    private Map<Integer, Post> posts;
    private Map<Integer, List<Integer>> userPosts;

    public void init(int N) {
        this.N = N;
        follows = new HashMap<>();
        posts = new HashMap<>();
        userPosts = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            follows.put(i, new HashSet<>());
            userPosts.put(i, new ArrayList<>());
        }
    }

    public void follow(int uID1, int uID2, int timestamp) {
        follows.get(uID1).add(uID2);
    }

    public void makePost(int uID, int pID, int timestamp) {
        Post post = new Post(pID, uID, timestamp);
        posts.put(pID, post);
        userPosts.get(uID).add(pID);
    }

    public void like(int pID, int timestamp) {
        Post post = posts.get(pID);
        post.likes++;
    }

    public void getFeed(int uID, int timestamp, int pIDList[]) {
        List<Post> visiblePosts = new ArrayList<>();

        for (int pID : userPosts.get(uID)) {
            visiblePosts.add(posts.get(pID));
        }

        for (int followedID : follows.get(uID)) {
            for (int pID : userPosts.get(followedID)) {
                visiblePosts.add(posts.get(pID));
            }
        }

        Collections.sort(visiblePosts, (p1, p2) -> {
            boolean p1Recent = (timestamp - p1.timestamp) <= 1000;
            boolean p2Recent = (timestamp - p2.timestamp) <= 1000;

            if (p1Recent && !p2Recent) {
                return -1;
            }
            if (!p1Recent && p2Recent) {
                return 1;
            }

            if (p1Recent && p2Recent) {
                if (p1.likes != p2.likes) {
                    return p2.likes - p1.likes;
                }
                return p2.timestamp - p1.timestamp;
            } else {
                return p2.timestamp - p1.timestamp;
            }
        });

        int count = Math.min(10, visiblePosts.size());
        for (int i = 0; i < count; i++) {
            pIDList[i] = visiblePosts.get(i).pID;
        }
    }
}